import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Skill } from '../../mentorsearch/skill.model';

@Injectable({
  providedIn: 'root'
})
export class SkillsService {

  constructor(private httpClient: HttpClient) { }

  fetchSkills(){
    return this.httpClient.get('api/skills/all');
  }

  save(skill: Skill){
    return this.httpClient.post('api/skills/save', skill);
  }
}
