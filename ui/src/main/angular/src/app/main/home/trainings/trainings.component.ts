import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { SkillsService } from '../skills/skills.service';
import { Skill } from '../../mentorsearch/skill.model';
import { Calendar } from '../../mentorsearch/calendar.model';
import { MentorService } from '../mentors/mentor.service';
import { User } from '../../mentorsearch/user.model';

@Component({
  selector: 'app-trainings',
  templateUrl: './trainings.component.html',
  styleUrls: ['./trainings.component.scss']
})
export class TrainingsComponent implements OnInit {

  steps: MenuItem[];
  activeStep = 0;
  skills: Skill[];

  selectedSkill: Skill;
  availabaleMentorsCalendar: Calendar[];
  selectedCalendar: Calendar;
  user: User;
  show = 'user_calendar';

  constructor(private skillsService: SkillsService, private mentorService: MentorService) { }

  ngOnInit() {
    this.steps = [
      {label: 'Select a technology'},
      {label: 'Select an awailabale mentor'},
      {label: 'Consider making a donation'}
    ];

    this.user = JSON.parse(localStorage.getItem('currentUser'));
   
    this.availabaleMentorsCalendar = [];

    this.skillsService.getActiveSkills().subscribe(skills => this.skills = skills);
  }

  /**
   * Step 1: 
   * Upon Skill selection let's fetch availabale trainings.
   * 
   * @param skill 
   */
  selectSkillForTrainig(skill: Skill){
    console.log('1. selected skill for training' + skill.name);
    this.mentorService.getAvailableMentorsBySkillId(skill.id)
      .subscribe((trainings: Array<any>) => this.handleTrainings(trainings), console.error);   
    this.selectedSkill = skill;
    this.activeStep++;
  }

  /**
   * Step 2:
   * 
   * Called upon traing selection.
   * @param calendar 
   */
  selecteTraining(calendar: Calendar){
    console.log('you have selectd the following trainign ' + JSON.stringify(calendar));
    
    //TODO: book this traing;
    this.mentorService.bookTraining(this.user.id, this.selectedSkill.id, calendar.id)
        .subscribe(() => {
          this.activeStep++;
        }, console.error);

  }

  /**
   * Step 3: 
   * 
   * Dnations - optional.
   * After making a 1$ donation return to trainigs new training selection.
   */
  restartTrainingPiker() {  
    this.show = 'user_calendar';
    this.activeStep = 0;  
  }


  
  private handleTrainings(trainings: Array<{mentorName: string, skillName: string, calendar: Calendar}>) {
    //we need create a new objecte otherwise angular will detect the change
    this.availabaleMentorsCalendar = new Array();
    //set training calendar availability
    for (let training of trainings){
      training.calendar.title = training.skillName + ' ' + training.mentorName;
      this.availabaleMentorsCalendar.push(training.calendar);
    }
   
  }

}
