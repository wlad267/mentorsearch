import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../core/authetication/authentication.service';
import {MenuItem} from 'primeng/api';
import { User } from '../mentorsearch/user.model';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  items: MenuItem[];
  private user: User
  private that: any;

  onSkills: any;
  onMentors: any;

  constructor(private authenticationSevice: AuthenticationService,
             private router:Router, 
             private route: ActivatedRoute) { 

              this.onSkills = function(event) {
                router.navigate(['/home/skills'] );
              };

              this.onMentors = function(event) {
                router.navigate(['/home/mentors'] );
              };

             }

  ngOnInit() {
    
    this.user = JSON.parse(localStorage.getItem('currentUser'));

    console.log('current user ' + JSON.stringify(this.user));

    this.items = [
      {label: 'Skills', icon: 'fa fa-fw fa-bar-chart', command: this.onSkills},
      {label: 'Mentors', icon: 'fa fa-fw fa-calendar', command: this.onMentors },
      {label: 'Users', icon: 'fa fa-fw fa-book', command: this.onSkills},
      {label: 'Payments', icon: 'fa fa-fw fa-support', command: this.onSkills},
      {label: 'Statistics', icon: 'fa fa-fw fa-twitter', command: this.onSkills}
  ];
  }

  logout(){
    this.authenticationSevice.logout();
  }

}
