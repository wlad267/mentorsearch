import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Skill } from '../../mentorsearch/skill.model';
import { User } from '../../mentorsearch/user.model';

@Injectable({
  providedIn: 'root'
})
export class MentorService {

  constructor(private httpClient: HttpClient) { }

  registerAsMentor(user: User, args: {userId: number, linkedInUrl: string, yearsOfExperience: number, skills: Array <Skill>}) {
    args.userId = user.id;
    return this.httpClient.post('api/mentors/register', args);
  }

  calncelMentoring(userId){
    return this.httpClient.post('api/mentors/cance/{userId}',{});
  }

}
