import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { Router } from '@angular/router';
import { User } from '../../mentorsearch/user.model';
import { MentorService } from './mentor.service';
import { Calendar } from '../../mentorsearch/calendar.model';
import { Skill } from '../../mentorsearch/skill.model';
import { Mentor } from '../../mentorsearch/mentor.model';

@Component({
  selector: 'app-mentors',
  templateUrl: './mentors.component.html',
  styleUrls: ['./mentors.component.scss']
})
export class MentorsComponent implements OnInit {

  skillManager: any;
  display = false;
  user: User;
  calendar : Array<Calendar>;
  skills: Array<Skill>;
  rating: number;
  
  constructor( private router:Router, private mentorService: MentorService) {}
  
  ngOnInit() {

    this.user= JSON.parse(localStorage.getItem('currentUser'));
    console.log(this.user.mentor);

    if (this.user.mentor){
        this.mentorService.getMentoringData(this.user.id).subscribe((data: Mentor) => {
            window.sessionStorage.setItem('mentor', JSON.stringify(data));
            this.calendar = data.calendar;    
            this.skills = data.skills;
            //TODO: update this from the service
            this.rating = 3;
            console.log('received mentoring info '+ JSON.stringify(data));
          });        
    }

  }

  updateLocalMentor(mentor: Mentor){
      this.skills= mentor.skills;
      this.calendar = mentor.calendar;
  }

  register(){
    console.log("register");
    this.display = true;
  }

  private updateMenringCalendar(calendar: Calendar[]) {
    let mentor = JSON.parse(window.sessionStorage.getItem('mentor'));
    this.mentorService.updateCallendar(mentor.id, calendar).subscribe(mentor => {
        console.log('saved mentor' + JSON.stringify(mentor));
    });
}

}
