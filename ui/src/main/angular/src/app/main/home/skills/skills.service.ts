import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Skill } from '../../mentorsearch/skill.model';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';



@Injectable({
  providedIn: 'root'
})
export class SkillsService {

  constructor(private httpClient: HttpClient) { }

  fetchSkills(){
    return this.httpClient.get('api/skills/all')
      .pipe(map(skills => {

          window.sessionStorage.setItem('skills', JSON.stringify(skills));

          return skills;
      }));
  }

  save(skill: Skill){
    return this.httpClient.post('api/skills/save', skill);
  }

  getSkills() {
    let skills = JSON.parse(window.sessionStorage.getItem('skills'));
    if (skills){
      return of(skills);  
    }
    return this.fetchSkills();

  }
}
