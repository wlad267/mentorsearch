import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Skill } from '../../../mentorsearch/skill.model';
import { ControlValueAccessor } from '@angular/forms';


@Component({
  selector: 'app-skill-list',
  templateUrl: './skill-list.component.html',
  styleUrls: ['./skill-list.component.scss']
})
export class SkillListComponent implements OnInit {
 
  disabled: boolean = true;
  /**
   * List of thechonlogies the component will me handling
   */
  @Input() skills: Array<Skill>;

  /**
   * Component might also disale enebling/disabling skills.
   * 
   * Due to the fact that the inpus is string and the prime ng input requires a boolean 
   * we need to conver the attribute before using it in the templete.
   */
  @Input('disabled') set setDisabled (value: String) {
    console.log('setting disabled value ' + value);
    this.disabled = value=='true';
  } 
 
  /**
   * The second parametrisation is 'componentMode' which will define two diffrent behavours:
   *    1. 'edit' mode where a skill can be editted and enbled/disabled
   *    2. 'view' mode where a skill can only be selected
   */
  @Input() componentMode;
 
  /**
   * Output Skill emitter for editing or skill.
   * Whenever a skill is selected it will be emmited to the parent component.
   * (the parent component will handle edditing by passing it to the edit skill component) 
   */ 
   @Output() selectedSkill: EventEmitter<Skill> =  new EventEmitter<Skill>();
  
   /**
   * Output skill activation. 
   * The parent component will handle necessary business to inactivate the skill by calling skill services.
   */
  @Output() skillActivation: EventEmitter<Skill> =  new EventEmitter<Skill>();

  constructor() { }

  ngOnInit() {
  }

  selectSkill(skill){
    console.log('emiting selected skill' + JSON.stringify(skill));
    this.selectedSkill.emit(skill); 
  }

  skillActivationChange(event, skill: Skill) {
    console.log('emiting activation for skill skill' + JSON.stringify(skill));
    skill.active = event.checked;
    skill.selected = event.checked;
    this.skillActivation.emit(skill); 
  }
  
}
