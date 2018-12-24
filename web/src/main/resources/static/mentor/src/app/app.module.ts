import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import {CardModule} from 'primeng/card';
import { MentorModule } from './mentor/mentor.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CardModule,
    MentorModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
