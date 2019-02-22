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

  /**
   * controls the side menu 
   */
  displayMenu = false;
  /**
   * TabMenu items
   */
  tabMenuItems: MenuItem[];
  activTabMenuItem: MenuItem;

  private user: User
  
  /**
   * Handlers for the tab menu
   */
  onSkills: any;
  onMentors: any;
  onUsers: any;
  onTrainings: any;
  onStatistics: any;
  

  constructor(private authenticationSevice: AuthenticationService,
             private router:Router, 
             private route: ActivatedRoute) { 

              this.onSkills = function(event) {
                router.navigate(['/home/skills'] );
              };

              this.onMentors = function(event) {
                router.navigate(['/home/mentors'] );
              };

              this.onUsers = function(event) {
                router.navigate(['/home/users'] );
              };

              this.onTrainings = function(event) {
                router.navigate(['/home/trainings'] );
              };

              this.onStatistics = function(event) {
                router.navigate(['/home/statistics'] );
              };

             }

  ngOnInit() {
    
    this.user = JSON.parse(localStorage.getItem('currentUser'));

    console.log('current user ' + JSON.stringify(this.user));
    
    this.tabMenuItems = [
      {label: 'Technologies', icon: 'fa fa-fw fa-bar-chart', command: this.onSkills},
      {label: 'Mentoring', icon: 'fa fa-fw fa-calendar', command: this.onMentors},
      //only for admins
      {label: 'Users', icon: 'fa fa-fw fa-book', command: this.onUsers},
      {label: 'Your Trainings', icon: 'fa fa-fw fa-book', command: this.onTrainings},
      {label: 'Statistics', icon: 'fa fa-fw fa-twitter', command: this.onStatistics}
    ];

    this.activTabMenuItem = this.tabMenuItems[0];
  }

  logout(){
    this.authenticationSevice.logout();
  }
 
  takeAction(action: string){
    console.log('tacking action ' + action);
    this.router.navigate([action], {relativeTo: this.route});
    this.displayMenu = false;    
    switch (action){
      case 'skills':
         this.activTabMenuItem = this.tabMenuItems[0];
      break;
      case 'trainings':
        this.activTabMenuItem = this.tabMenuItems[3];
      break;
      default:
        this.activTabMenuItem = this.tabMenuItems[0];
      break;  
    }
  }

  

}
