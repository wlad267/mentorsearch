import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { SkillsService } from '../skills/skills.service';
import { Skill } from '../../mentorsearch/skill.model';
import { Calendar } from '../../mentorsearch/calendar.model';

@Component({
  selector: 'app-trainings',
  templateUrl: './trainings.component.html',
  styleUrls: ['./trainings.component.scss']
})
export class TrainingsComponent implements OnInit {

  steps: MenuItem[];
  activeStep=1;
  skills: Skill[];
  selectedSkill: Skill;
  availabaleMentorsCalendar: Calendar[];

  constructor(private skillsService: SkillsService) { }

  ngOnInit() {
    this.steps = [
      {label: 'Select a technology'},
      {label: 'Select an awailabale mentor'},
      {label: 'Confirm the setion'}
  ];

  this.skillsService.getActiveSkills().subscribe(skills => this.skills = skills);

  }

  selectSkillForTrainig(skill: Skill){
    console.log('you selected' + skill.name);
    this.selectedSkill = skill;
    this.activeStep++;
  }
  

}