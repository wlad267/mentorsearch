import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DonateeComponent } from './donatee.component';
import { NgxPayPalModule } from 'ngx-paypal';

describe('DonateeComponent', () => {
  let component: DonateeComponent;
  let fixture: ComponentFixture<DonateeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DonateeComponent ],
      imports: [NgxPayPalModule]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DonateeComponent);
    component = fixture.componentInstance;

    setTimeout(function () {
      fixture.detectChanges();
    }, 2000);
    
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
