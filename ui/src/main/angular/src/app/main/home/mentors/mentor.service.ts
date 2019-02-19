import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Skill } from '../../mentorsearch/skill.model';
import { User } from '../../mentorsearch/user.model';
import { Calendar } from '../../mentorsearch/calendar.model';
import { Mentor } from '../../mentorsearch/mentor.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MentorService {

  constructor(private httpClient: HttpClient) { }

  registerAsMentor(args: {userId: number, linkedInUrl: string, yearsOfExperience: number, skills: Array <Skill>}) {
    return this.httpClient.post('api/mentors/register', args);
  }

  getMentoringData(userId: number){
    return this.httpClient.get(`api/mentors/byUserId/${userId}`)
  }

  calncelMentoring(userId){
    return this.httpClient.post('api/mentors/cance/{userId}',{});
  }
r
  updateCallendar(mentorId: number, calendar: Calendar[]) : Observable<any> {
    return this.httpClient.post(`api/mentors/${mentorId}/calendar/save`, calendar);
  }

  getAvailableMentorsBySkillId(skillId: number){
    return this.httpClient.get(`api/trainings/search/skill/${skillId}`);
  }

  bookTraining(userId: number, skillId: number, calendarId: number){
    return this.httpClient.post(`api/trainings/book/${skillId}/${userId}/${calendarId}`, {});
  }

}
