import { Skill } from './skill.model';
import { Calendar } from './calendar.model';

export class Mentor {
    id: number;
    skills: Array<Skill>;
    calendar: Array<Calendar>;
}