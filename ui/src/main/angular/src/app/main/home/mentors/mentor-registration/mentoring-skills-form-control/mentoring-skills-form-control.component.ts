import { Component, OnInit, Input, forwardRef } from '@angular/core';
import { Skill } from '../../../../mentorsearch/skill.model';
import { ControlValueAccessor, NG_VALUE_ACCESSOR, FormControl, NG_VALIDATORS } from '@angular/forms';


/**
 * Custom skills slector from control validator
 * @param formControl 
 * return null if there is at least one skill selected
 */
export function validateSelectedSkills(formControl: FormControl): { [key: string]: any;} {
   console.log('validating skill selection');
   let skills: Array<Skill> = formControl.value;
   
   if (skills.filter(skill=>skill.selected).length>0) {
     return null;
   };

   return { not_selectd: {} }
}


@Component({
  selector: 'app-mentoring-skills-form-control',
  templateUrl: './mentoring-skills-form-control.component.html',
  styleUrls: ['./mentoring-skills-form-control.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => MentoringSkillsFormControlComponent),
      multi: true
    },
    { 
      provide: NG_VALIDATORS,
      useValue: validateSelectedSkills,
      multi: true
    }
  ]
})
export class MentoringSkillsFormControlComponent implements OnInit, ControlValueAccessor {

  @Input() skills: Array<Skill>;

  onChange: any;
  onTouch: any;

  constructor() { }

  ngOnInit() {
  }

  selectSkillForMentoring(skill: Skill){
    //notify angular the control values had been chaged
    //the selection in stored in the skill onject
    this.onChange(this.skills);
    //tell angular the control was touched
    this.onTouch(true);
  }

  //this is called when the view is updating the model
  writeValue(obj: any): void {
    console.log('writing value ' + JSON.stringify(obj));
  }

  registerOnChange(fn: any): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: any): void {
    this.onTouch = fn;
  }

  //we are not instrested in disabled 
  setDisabledState?(isDisabled: boolean): void {
  }


}
