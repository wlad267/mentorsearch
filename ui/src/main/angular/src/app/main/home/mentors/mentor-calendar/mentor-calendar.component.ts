import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { FullCalendar } from 'primeng/fullcalendar';
import { MentorService } from '../mentor.service';
import { Calendar } from '../../../mentorsearch/calendar.model';

@Component({
    selector: 'app-mentor-calendar',
    templateUrl: './mentor-calendar.component.html',
    styleUrls: ['./mentor-calendar.component.scss']
})
export class MentorCalendarComponent implements OnInit {

    @ViewChild('calendar') fl: FullCalendar;

    @Input('calendar') set calendarSetter(calendar: Calendar[]) {
        if (calendar) {
            for (let cal of calendar) {
                this.fl.calendar.addEvent({start: cal.startTime, end: cal.endTime, title: cal.title});
            }
        }

    }

    events: any[];

    options: any;

    constructor(private mentorService: MentorService) { }

    ngOnInit() {

        this.options = {
            defaultDate: Date(),
            selectable: true,

            header: {
                left: 'prev,next',
                center: 'title',
                right: 'month,agendaWeek,agendaDay'
            },
            editable: true,

            dateClick: (e) => {
                console.log('calendar click:' + e);
                switch (e.view.type) {
                    case "month":
                        this.fl.calendar.changeView('agendaDay', e.date);
                        break;
                    case "agendaWeek":
                        break;
                    case "agendaDay":
                        e.title = 'Mentoring availability added';
                        this.fl.calendar.addEvent(e);
                        this.updateMenringCalendar(this.parseCalendarEvents(e.view.calendar.getEvents()));
                        break;
                }
            },
            eventClick: (e) => {
                if (e.view.type === 'agendaDay') {
                    console.log('removing envent ' + e.id + ' - ' + e.title);
                    e.event.remove();
                    this.updateMenringCalendar(this.parseCalendarEvents(e.view.calendar.getEvents()));
                    return;
                }

                this.fl.calendar.changeView('agendaDay', e.date);

            }
        };

    }

    private parseCalendarEvents(events: any[]): Calendar[] {
        let calendar = [];
        for (let event of events) {
            let c = new Calendar();
            c.startTime = new Date(event.instance.range.start);
            c.endTime = new Date(event.instance.range.end);
            c.title = event.title;
            calendar.push(c);
        }

        console.log('calendar events:' + JSON.stringify(calendar));

        return calendar;
    }

    private updateMenringCalendar(calendar: Calendar[]) {
        let mentor = JSON.parse(window.sessionStorage.getItem('mentor'));
        this.mentorService.updateCallendar(mentor.id, calendar).subscribe(mentor => {
            console.log('saved mentor' + JSON.stringify(mentor));
        });
    }

}
