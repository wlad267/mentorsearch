import { Component, OnInit } from '@angular/core';
import { SkillsService } from '../../skills/skills.service';
import { Skill } from '../../../mentorsearch/skill.model';
import { LoadingService } from '../../../../core/loading/loading.service';
import { FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';
import { MentorService } from '../mentor.service';


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

  display = false;
  skills: Array<Skill>; 
  selectedSkills: Array<Skill>; 
  years = 1;
  linkedInUrl: string;
  mentorRegistrationForm: FormGroup;
  private user;

  constructor(private skillsService: SkillsService, 
              private loaadingSevice: LoadingService, 
              private mentorService: MentorService) { 
              }

  ngOnInit() {
    this.loaadingSevice.block();
    this.skillsService.getSkills().subscribe((skills: Array<Skill>)=> {
      this.skills = skills.filter(skill=> skill.active);
      console.log('received active skills ' + skills);
      this.loaadingSevice.unblock();
    });

    this.mentorRegistrationForm = new FormGroup({
      mentoringSkills: new FormControl(new Array(), [Validators.required]),
      yearsOfExperience: new FormControl(2),
      linkedInUrl: new FormControl(''),
      agreement: new FormControl(false, Validators.requiredTrue)
    });

    this.user = JSON.parse(localStorage.getItem('currentUser'));
  }

  register(){
    console.log("mentoring registeration request " + JSON.stringify(this.mentorRegistrationForm.value));       
    this.mentorService.registerAsMentor(this.user, this.mentorRegistrationForm.value)
      .subscribe(response=>{
        console.log(response);
      })
    this.display = false;
  }

 
}
