import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import { UserService } from './services/user.service';
import { RouterModule } from '@angular/router';
import {TabMenuModule} from 'primeng/tabmenu';
import {TableModule} from 'primeng/table';
import {InputSwitchModule} from 'primeng/inputswitch';
import { SkillsComponent } from './home/skills/skills.component';
import { MentorsComponent } from './home/mentors/mentors.component';
import { MainRoutesModule } from './main-routes.module';
import { SkillsService } from './home/skills/skills.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {DialogModule} from 'primeng/dialog';



@NgModule({
  declarations: [HomeComponent, SkillsComponent, MentorsComponent],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule, 
    ReactiveFormsModule,
    TabMenuModule,
    TableModule,
    MainRoutesModule, 
    InputSwitchModule,
    FormsModule,
    DialogModule
  ],
  exports: [HomeComponent, RouterModule],
  providers: [UserService, SkillsService]
})
export class MainModule { }
