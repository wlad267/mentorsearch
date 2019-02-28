import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './main/home/home.component';
import { LoginComponent } from './core/authetication/login/login.component';
import { RegisterComponent } from './core/authetication/register/register.component';
import { AuthGuard } from './core/authetication/auth.guard';
import { SkillsComponent } from './main/home/skills/skills.component';
import { MentorsComponent } from './main/home/mentors/mentors.component';
import { UsersComponent } from './main/home/users/users.component';
import { TrainingsComponent } from './main/home/trainings/trainings.component';
import { StatisticsComponent } from './main/home/statistics/statistics.component';
import { DonateeComponent } from './main/home/donatee/donatee.component';

const appRoutes: Routes = [
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard],
  children: [
    { path: 'skills', component: SkillsComponent },
    { path: 'mentors', component: MentorsComponent },
    { path: 'users', component: UsersComponent },
    { path: 'trainings', component: TrainingsComponent },
    { path: 'statistics', component: StatisticsComponent },
    { path: 'donate', component: DonateeComponent },
    { path: '**', redirectTo: 'skills' }
    ] 
  },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
 
  { path: '**', redirectTo: 'home' }
];

export const routing = RouterModule.forRoot(appRoutes);

@NgModule({
  imports: [RouterModule.forRoot(appRoutes, { enableTracing: true })],
 // imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
