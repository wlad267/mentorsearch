import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../core/authetication/authentication.service';
import {MenuItem} from 'primeng/api';
import { User } from '../mentorsearch/user.model';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  items: MenuItem[];
  private user: User

  constructor(private authenticationSevice: AuthenticationService) { }

  ngOnInit() {
    
    this.user = JSON.parse(localStorage.getItem('currentUser'));

    console.log('current user ' + JSON.stringify(this.user));
    //TODO: dependeing on user roles form user menu
    this.items = [
      {label: 'Skills', icon: 'fa fa-fw fa-bar-chart', command: this.onSkills},
      {label: 'Mentors', icon: 'fa fa-fw fa-calendar', command: this.onMentors},
      {label: 'Users', icon: 'fa fa-fw fa-book'},
      {label: 'Payments', icon: 'fa fa-fw fa-support'},
      {label: 'Statistics', icon: 'fa fa-fw fa-twitter'}
  ];
  }

  logout(){
    this.authenticationSevice.logout();
  }

  onSkills(){
    console.log('skills');
  }

  onMentors(){
    console.log('mentors');
  }
}
