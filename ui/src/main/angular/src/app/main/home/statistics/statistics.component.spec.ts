import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StatisticsComponent } from './statistics.component';
import { ChartModule } from 'primeng/chart';

describe('StatisticsComponent', () => {
  let component: StatisticsComponent;
  let fixture: ComponentFixture<StatisticsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StatisticsComponent ],
      imports: [ChartModule]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StatisticsComponent);
    component = fixture.componentInstance;
    setTimeout(function () {
      fixture.detectChanges();
    }, 2000);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
