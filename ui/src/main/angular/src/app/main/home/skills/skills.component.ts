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
  display: boolean = false;
  edistedSkill;
  skillFromGroup: FormGroup;

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

    this.skillFromGroup = new FormGroup({
      name: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required]),
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
    this.skillFromGroup = new FormGroup({
      name: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required]),
    });  
  }

  skillClick(skill){
    console.log(skill);
    this.skillFromGroup = new FormGroup({
      name: new FormControl(skill.name, [Validators.required]),
      description: new FormControl(skill.description, [Validators.required]),
    });
    this.display = true;  
  }

  submitSkill(){
    console.log("disply none");
    this.display = false;
    this.loadingService.block();
    this.skillsService.save(this.skillFromGroup.value).subscribe(()=>{ this.loadingService.unblock();
      this.ngOnInit();
    } ,
      error=> {
        this.loadingService.unblock();
        this.messagingService.error(error.message);
      });   
  }

}
