import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {HttpClientModule} from '@angular/common/http';


import {AppComponent} from './app.component';
import {HomepageComponent} from './home-page/homepage.component';
import {UsMapModule} from 'angular-us-map';
import {LoginPageComponent} from './login-page/login-page.component';
import {ReactiveFormsModule} from '@angular/forms';
import {AlertComponent} from './alert/alert.component';
import {RegisterPageComponent} from './register-page/register-page.component';
import {MyaccountPageComponent} from './myaccount-page/myaccount-page.component';
import {ForgetpasswordPageComponent} from './forgetpassword-page/forgetpassword-page.component';
import {FusionChartsModule} from 'angular-fusioncharts';
import {VirtualizationPageComponent} from './virtualization-page/virtualization-page.component';
import {AgmCoreModule} from '@agm/core';
import {GoogleMapsAPIWrapper} from '@agm/core';
import { MarkerManager } from '@agm/core';
// Import FusionCharts library and chart modules
import * as FusionCharts from 'fusioncharts';
import * as charts from 'fusioncharts/fusioncharts.charts';
import * as FusionTheme from 'fusioncharts/themes/fusioncharts.theme.fusion';
import * as Maps from 'fusioncharts/fusioncharts.maps';
import * as USA from 'fusioncharts/maps/fusioncharts.usa';
import {SearchingPageComponent} from './searching-page/searching-page.component';
import {environment} from '../environments/environment';


// Pass the fusioncharts library and chart modules
FusionChartsModule.fcRoot(FusionCharts, charts, Maps, FusionTheme, USA);

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    LoginPageComponent,
    AlertComponent,
    RegisterPageComponent,
    MyaccountPageComponent,
    ForgetpasswordPageComponent,
    VirtualizationPageComponent,
    SearchingPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    UsMapModule,
    ReactiveFormsModule,
    HttpClientModule,
    FusionChartsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCunSDzUB8irdZU5KLBLsY7a87Iulf_br8',
      libraries: ['places', 'geometry']
    })
  ],
  providers: [MarkerManager],
  bootstrap: [AppComponent]
})
export class AppModule {
}

