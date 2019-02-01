import { Component, OnInit } from '@angular/core';
import { Skill } from '../../mentorsearch/skill.model';
import { SkillsService } from './skills.service';
import { MessagingService } from '../../../core/messaging/messaging.service';
import { LoadingService } from '../../../core/loading/loading.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.scss']
})
export class SkillsComponent implements OnInit {

  skills: Array<Skill> = [];
  editedSkill: Skill;

  constructor(private skillsService: SkillsService,
    private messagingService: MessagingService,
    private loadingService: LoadingService) { }

  ngOnInit() {
    this.loadingService.block();
    this.skillsService.fetchSkills().subscribe((skills: Skill[]) => {
      this.skills = skills;
      this.loadingService.unblock();
    }, error => {
      this.loadingService.unblock();
      this.messagingService.error(error.message);
    });
  }

  editSkill(skill: Skill) {
    if (!skill){
      console.log('add a new skill');
      skill = new Skill();
    }    
    console.log('editing skill' + JSON.stringify(skill));
    this.editedSkill = skill;
  }

  activateSkill(skill: Skill){
    console.log('saving activated skill ' + JSON.stringify(skill));
    this.saveSkill(skill);
  }

  saveSkill(skill: Skill) {
    console.log('saving the skill ' + JSON.stringify(skill));

    this.loadingService.block();
    this.skillsService.save(skill).subscribe(() => {
      this.loadingService.unblock();
      this.ngOnInit();
    },
      error => {
        this.loadingService.unblock();
        this.messagingService.error(error.message);
      });

  }



}
