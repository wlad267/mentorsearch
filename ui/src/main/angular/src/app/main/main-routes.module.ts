import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { SkillsComponent } from './home/skills/skills.component';
import { MentorsComponent } from './home/mentors/mentors.component';
import { HomeComponent } from './home/home.component';

const mainRoutes: Routes = [
  { path: 'home',
    component: HomeComponent,
    children: [
      { path: 'skills', component: SkillsComponent },
      { path: 'mentors', component: MentorsComponent }
  ]
  }  
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(mainRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class MainRoutesModule { }
