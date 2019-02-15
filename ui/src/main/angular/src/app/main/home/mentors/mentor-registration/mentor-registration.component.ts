import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { SkillsService } from '../../skills/skills.service';
import { Skill } from '../../../mentorsearch/skill.model';
import { LoadingService } from '../../../../core/loading/loading.service';
import { FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';
import { MentorService } from '../mentor.service';
import { Mentor } from '../../../mentorsearch/mentor.model';



export function ValidateUrl(control: AbstractControl) {
  if (!control.value.startsWith('https') || !control.value.includes('.io')) {
    return { validUrl: true };
  }
  return null;
}

@Component({
  selector: 'app-mentor-registration',
  templateUrl: './mentor-registration.component.html',
  styleUrls: ['./mentor-registration.component.scss']
})
export class MentorRegistrationComponent implements OnInit {

  @Input() componentMode: string;
  @Input('selectedSkills') set selectedSkillsSetter(value: Array<Skill>){
    this.selectedSkills = value;
    if (this.skills){
      this.updateMentorFrom();
    }
  }

  @Output() mentorEvent: EventEmitter<Mentor> = new EventEmitter<Mentor>();

  selectedSkills: Array<Skill>;
  display = false;
  skills: Array<Skill>;
  years = 1;
  linkedInUrl: string;
  mentorRegistrationForm: FormGroup;
  
  private user;
  private Mentor;

  constructor(private skillsService: SkillsService, 
              private loaadingSevice: LoadingService, 
              private mentorService: MentorService) { 
              }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
    this.mentorRegistrationForm = new FormGroup({
      mentoringSkills: new FormControl(new Array(), [Validators.required]),
      yearsOfExperience: new FormControl(2),
      linkedInUrl: new FormControl(''),
      agreement: new FormControl(false, Validators.requiredTrue)
    });

    this.loaadingSevice.block();
    this.skillsService.getActiveSkills().subscribe((skills: Array<Skill>)=> {      
      this.skills = skills.filter(skill=> skill.active);
      console.log('received active skills ' + skills);      
      if (this.selectedSkills){
        this.updateMentorFrom();
      }
     
      this.loaadingSevice.unblock();
    });

  }

  private updateMentorFrom():void {
      let skillIds = [];
      for (let skill of this.selectedSkills){
            skillIds.push(skill.id);
      }

     for (let skill of this.skills){
          if (skillIds.indexOf(skill.id)>=0){
              skill.selected = true;
            }
      }

  }


  register(){
    console.log("mentoring registeration request " + JSON.stringify(this.mentorRegistrationForm.value));       
    let selectedSkill = [];
    for (let skill of this.mentorRegistrationForm.value.mentoringSkills){
      if (skill.selected){
        selectedSkill.push(skill.id)
      }
    }
    this.mentorService.registerAsMentor(
      {userId: this.user.id,
        skills: selectedSkill,
        yearsOfExperience: this.mentorRegistrationForm.controls.yearsOfExperience.value,
        linkedInUrl: this.mentorRegistrationForm.controls.linkedInUrl.value}
    ).subscribe((response: Mentor)=>{
        console.log(response);
        window.sessionStorage.setItem('mentor', JSON.stringify(response));
        this.mentorEvent.emit(response);
      })
    this.display = false;
  }

 
}
