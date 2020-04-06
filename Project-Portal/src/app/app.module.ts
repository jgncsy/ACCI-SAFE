import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomepageComponent } from './homepage/homepage.component';
import {UsMapModule} from 'angular-us-map'

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    UsMapModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}

