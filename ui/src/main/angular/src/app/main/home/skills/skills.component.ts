import { Component, OnInit } from '@angular/core';
import { Skill } from '../../mentorsearch/skill.model';
import { SkillsService } from './skills.service';
import { MessagingService } from '../../../core/messaging/messaging.service';
import { LoadingService } from '../../../core/loading/loading.service';

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.scss']
})
export class SkillsComponent implements OnInit {

  skills: Array<Skill> = [];
  display: boolean = false;
  edistedSkill;

  constructor(private skillsService: SkillsService, 
    private messagingService: MessagingService,
    private loadingService: LoadingService) { }

  ngOnInit() {

    this.loadingService.block();
    this.skillsService.fetchSkills().subscribe((skills:Skill[])=> {
      this.skills= skills;
      this.loadingService.unblock();
    }, error => {
      this.loadingService.unblock();
      this.messagingService.error(error.message);
    });
  
  }

  skillActivationChange(event, skill:Skill){
    console.log(event);
    skill.active = event.checked;
    this.loadingService.block();
    this.skillsService.save(skill).subscribe(()=>{ this.loadingService.unblock();} ,
      error=> {
        this.loadingService.unblock();
        this.messagingService.error(error.message);
        skill.active = !skill.active;
      });   
    
  }

  addSkill(){
    this.display = true;
    this.edistedSkill = new Skill();    
  }

  skillClick(skill){
    console.log(skill);
  }

}
