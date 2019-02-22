import { Component, OnInit, ViewChild, Input, Output, EventEmitter } from '@angular/core';
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

    /**
     * Caching the input change via a setter adn coverne to FullCallendar model
     */
    @Input('calendar') set calendarSetter(calendar: Calendar[]) {
        if (calendar) {           
            for (let cal of calendar) {
                this.fl.calendar.addEvent(
                    { start: cal.startTime, 
                        end: cal.endTime, 
                        title: cal.title, 
                        calendar_id: cal.id,
                        color : this.getEventColor(cal)
                    });
            }
        }

    }

    /**
     * Calendar component has diffrent behaviors:
     * 
     * 1. mentor-mode
     *     Used for mentoring editing his availability. 
     *     In his mode the cladar will allow the mentor to navigate to day view and add/delete slices 
     * 
     * 2. trainee-mode 
     *     Used for trainee selecting desired mentoring sessions. 
     *     Is is not allowing the day nor weekly view. 
     *     Allows only secting future mentoring trainings.
     * 
     * 3. view-mode 
     *    Just show callendar montly view.
     *    No selections.
     */
    @Input() componentMode: string;

    /**
     * Whenever calendar hour is selected the component will propagate the change to any parent listening for the event
     */
    @Output()
    addCaledar: EventEmitter<Calendar> = new EventEmitter();

    /**
     * Whenevr a calendar entry is selected the component will propagate the change to any parent listening for the selection
     */
    @Output()
    selectedCalendar: EventEmitter<Calendar> = new EventEmitter();

    /**
     * Whenver there are changes in the events propagate changed collection
     */
    @Output()
    calendarEvents: EventEmitter<Calendar[]> = new EventEmitter<Array<Calendar>>();

    events: any[];

    options: any;

    constructor() { }

    ngOnInit() {

        this.options = {
            defaultDate: Date(),
            selectable: this.componentMode !== 'view-mode',
            header: {
                left: 'prev,next',
                center: 'title',
                right: this.componentMode === 'mentor-mode' ? 'month,agendaWeek,agendaDay' : 
                            this.componentMode === 'view-mode' ? undefined : 'month',
            },
            editable: this.componentMode !== 'view-mode',

            dateClick: (e) => {
                if (this.componentMode === 'mentor-mode') {
                    switch (e.view.type) {
                        case "month":
                            this.fl.calendar.changeView('agendaDay', e.date);
                            break;
                        case "agendaWeek":
                            break;
                        case "agendaDay":

                            e.title = 'Mentoring availability added';
                            this.fl.calendar.addEvent(e);
                          
                            //propagate the entire calendar selection 
                            this.calendarEvents.emit(this.parseCalendarEvents(e.view.calendar.getEvents()));

                            let calendar = new Calendar();
                            calendar.startTime = new Date(e.dateStr);
                            calendar.endTime = new Date(e.dateStr)
                            calendar.endTime.setHours(calendar.endTime.getHours() + 1);
                             
                            e.color = this.getEventColor(calendar);

                            //propagate just the new added calendar
                            this.addCaledar.emit(calendar);


                            break;
                    }
                }
            },

            eventClick: (e) => {
                switch (this.componentMode) {
                    case 'mentor-mode':
                        if (e.view.type === 'agendaDay') {
                            console.log('removing envent ' + e.id + ' - ' + e.title);
                            e.event.remove();
                            this.calendarEvents.emit(this.parseCalendarEvents(e.view.calendar.getEvents()));
                            return;
                        } else {
                            this.fl.calendar.changeView('agendaDay', e.date);
                        }
                    break;

                    case 'trainee-mode':
                    break;

                }

                let calendar = this.parseClendarEvent(e.event);
                this.selectedCalendar.emit(calendar);

            }
        };

    }

    private parseCalendarEvents(events: any[]): Calendar[] {
        let calendar = [];

        for (let event of events) {
            let calendarEntry = this.parseClendarEvent(event);
            calendar.push(calendarEntry);
        }

        console.log('calendar events:' + JSON.stringify(calendar));

        return calendar;
    }

    private parseClendarEvent(event: any): Calendar {
        let calendar = new Calendar();
        calendar.id = event.extendedProps.calendar_id;
        calendar.startTime = new Date(event.instance.range.start);
        calendar.endTime = new Date(event.instance.range.end);
        calendar.title = event.title;
        return calendar;
    }

    private colos = {RED: '#ff8282', BLUE:'#82beff', GREEN:'#82ffad', PAST_EVENT_COLOR : '#96a392'};

    private getEventColor(event: any){
        const  now = new Date();
        if (new Date(event.endTime)< now){
            return this.colos.RED;   
       }else if (new Date(event.startTime)< now){
           return  this.colos.BLUE;   
       }
       return this.colos.GREEN;
    }

}
