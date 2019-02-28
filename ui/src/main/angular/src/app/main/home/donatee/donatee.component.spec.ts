import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DonateeComponent } from './donatee.component';

describe('DonateeComponent', () => {
  let component: DonateeComponent;
  let fixture: ComponentFixture<DonateeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DonateeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DonateeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
