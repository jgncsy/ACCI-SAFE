import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AxiosInstance} from 'axios';

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

// Import FusionCharts library and chart modules
import * as FusionCharts from 'fusioncharts';
import * as charts from 'fusioncharts/fusioncharts.charts';
import * as FusionTheme from 'fusioncharts/themes/fusioncharts.theme.fusion';
import * as Maps from 'fusioncharts/fusioncharts.maps';
import * as USA from 'fusioncharts/maps/fusioncharts.usa';


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
    ForgetpasswordPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    UsMapModule,
    ReactiveFormsModule,
    HttpClientModule,
    FusionChartsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}

