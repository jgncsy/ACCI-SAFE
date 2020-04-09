import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {HttpClientModule} from '@angular/common/http';


import {AppComponent} from './app.component';
import {HomepageComponent} from './home-page/homepage.component';
import {UsMapModule} from 'angular-us-map';
import {LoginPageComponent} from './login-page/login-page.component';
import {ReactiveFormsModule} from '@angular/forms';
import { AlertComponent } from './alert/alert.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import {MyaccountPageComponent} from './myaccount-page/myaccount-page.component';
import { ForgetpasswordPageComponent } from './forgetpassword-page/forgetpassword-page.component';

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
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}

