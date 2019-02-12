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

  fetchSkills() {
    return this.httpClient.get('api/skills/all')
      .pipe(map((skills: Array<Skill>) => {
          window.sessionStorage.setItem('skills', JSON.stringify(skills));
          return skills;
      }));
  }

  save(skill: Skill){
    return this.httpClient.post('api/skills/save', skill);
  }

  /**
   * If skills are already fetched and stored in the ssession return them from the session
   * Otherwise fetch them
   */   
  getSkills(): Observable<Skill[]> {
    let skills = JSON.parse(window.sessionStorage.getItem('skills'));
    if (skills){
      return of(skills);  
    }
    return this.fetchSkills();
  }

  getActiveSkills(){
    return this.getSkills().pipe(map((skills: Skill[]) => {
      return skills.filter(s=>s.active);
    }));
  }
}
