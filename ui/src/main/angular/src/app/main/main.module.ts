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
import { DialogModule } from 'primeng/dialog';
import { TieredMenuModule } from 'primeng/tieredmenu';
import { SidebarModule } from 'primeng/sidebar';
import { DynamicDialogModule } from 'primeng/dynamicdialog';
import { TrainingsComponent } from './home/trainings/trainings.component';
import { UsersComponent } from './home/users/users.component';
import { MentorRegistrationComponent } from './home/mentors/mentor-registration/mentor-registration.component';
import {EditorModule} from 'primeng/editor';
import { SkillListComponent } from './home/skills/skill-list/skill-list.component';
import { EditSkillComponent } from './home/skills/edit-skill/edit-skill.component';
import {CheckboxModule} from 'primeng/checkbox';
import { MentorService } from './home/mentors/mentor.service';
import {RadioButtonModule} from 'primeng/radiobutton';
import {PaginatorModule} from 'primeng/paginator';
import { MentoringSkillsFormControlComponent } from './home/mentors/mentor-registration/mentoring-skills-form-control/mentoring-skills-form-control.component';
import { MentorCalendarComponent } from './home/mentors/mentor-calendar/mentor-calendar.component';
import {FullCalendarModule} from 'primeng/fullcalendar';

import {ScrollPanelModule} from 'primeng/scrollpanel';
import {AccordionModule} from 'primeng/accordion';
import {RatingModule} from 'primeng/rating';
import {StepsModule} from 'primeng/steps';


@NgModule({
  declarations: [HomeComponent, SkillsComponent, MentorsComponent, TrainingsComponent, UsersComponent, MentorRegistrationComponent, SkillListComponent, EditSkillComponent, MentoringSkillsFormControlComponent, MentorCalendarComponent],
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
    DialogModule,
    TieredMenuModule,
    SidebarModule,
    DynamicDialogModule,
    EditorModule,
    CheckboxModule,
    RadioButtonModule,
    PaginatorModule,
    FullCalendarModule,
    ScrollPanelModule,
    AccordionModule,
    RatingModule,
    StepsModule
  ],
  exports: [HomeComponent, RouterModule],
  providers: [UserService, SkillsService, MentorService]
})
export class MainModule { }
