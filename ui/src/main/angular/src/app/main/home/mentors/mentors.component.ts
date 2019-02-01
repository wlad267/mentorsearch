import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { Router } from '@angular/router';
import { User } from '../../mentorsearch/user.model';

@Component({
  selector: 'app-mentors',
  templateUrl: './mentors.component.html',
  styleUrls: ['./mentors.component.scss']
})
export class MentorsComponent implements OnInit {

  
  items: MenuItem[];
  skillManager: any;
  display = false;
  user: User;
  
  constructor( private router:Router) { }
  ngOnInit() {

    this.user= JSON.parse(localStorage.getItem('currentUser'));
    console.log(this.user.mentor);

    this.items = [
      {
          label: 'Profile',
          icon: 'pi pi-fw pi-cog',
          items: [
              { label: 'Manage you Skills', icon: 'pi pi-fw pi-cog' ,  command: this.skillManager},
          ]
      },
      {
          label: 'Trainings',
          icon: 'pi pi-fw pi-pencil',
          items: [
              {label: 'Training Requests', icon: 'pi pi-fw pi-trash' , command: this.skillManager},
              {label: 'Confirmations & Schedule', icon: 'pi pi-fw pi-refresh' , command: this.skillManager},
              {label: 'History', icon: 'pi pi-fw pi-trash',  command: this.skillManager}

          ]
      }
  ];

  }


  register(){
    console.log("register");
    this.display = true;
  }

}
