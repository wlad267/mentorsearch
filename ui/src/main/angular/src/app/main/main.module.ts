import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import { UserService } from './services/user.service';
import { RouterModule } from '@angular/router';
import {TabMenuModule} from 'primeng/tabmenu';
import { SkillsComponent } from './home/skills/skills.component';
import { MentorsComponent } from './home/mentors/mentors.component';
import { MainRoutesModule } from './main-routes.module';


@NgModule({
  declarations: [HomeComponent, SkillsComponent, MentorsComponent],
  imports: [
    CommonModule,
    RouterModule,
    TabMenuModule,
    MainRoutesModule
  ],
  exports: [HomeComponent],
  providers: [UserService]
})
export class MainModule { }
