import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes } from '@angular/router';
import { SkillsComponent } from './home/skills/skills.component';
import { MentorsComponent } from './home/mentors/mentors.component';

const mainRoutes: Routes = [
  { path: 'skills',  component: SkillsComponent },
  { path: 'mentors', component: MentorsComponent }
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class MainRoutesModule { }
