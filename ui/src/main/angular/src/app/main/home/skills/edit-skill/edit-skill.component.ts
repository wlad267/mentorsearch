import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Skill } from '../../../mentorsearch/skill.model';
import { MessagingService } from '../../../../core/messaging/messaging.service';
import { LoadingService } from '../../../../core/loading/loading.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-edit-skill',
  templateUrl: './edit-skill.component.html',
  styleUrls: ['./edit-skill.component.scss']
})
export class EditSkillComponent implements OnInit {

  skillFromGroup: FormGroup;
  skill: Skill;

  @Input('skill') set setSkill(skill: Skill){
    console.log('setting the input skill to be edited ' + skill);    
    this.skill = skill;
    this.skillFromGroup = new FormGroup({
      id: new FormControl(skill.id),
      name: new FormControl(skill.name, [Validators.required]),
      description: new FormControl(skill.description, [Validators.required]),
    });
  }
  @Output() saveSkillEvent: EventEmitter<Skill> = new EventEmitter();
  
  constructor(private messagingService: MessagingService,
    private loadingService: LoadingService) { }

  ngOnInit() {
  }

  submitSkill(){
    console.log("emitting edited skill: " + JSON.stringify(this.skillFromGroup.value));    
    this.saveSkillEvent.emit(this.skillFromGroup.value);
    this.skill = undefined; 
  }

  close($event){
    console.log('closeing the dialog' + $event);
  }
}
