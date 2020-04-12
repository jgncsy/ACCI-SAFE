import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomepageComponent} from './home-page/homepage.component';
import {LoginPageComponent} from './login-page/login-page.component';
import {RegisterPageComponent} from './register-page/register-page.component';
import {MyaccountPageComponent} from './myaccount-page/myaccount-page.component';
import {ForgetpasswordPageComponent} from './forgetpassword-page/forgetpassword-page.component';
import {VirtualizationPageComponent} from './virtualization-page/virtualization-page.component';
import {SearchingPageComponent} from './searching-page/searching-page.component';
import {AdminDashboardPageComponent} from './admin-dashboard-page/admin-dashboard-page.component';
import {AdminLoginComponent} from './admin-login/admin-login.component';


const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: HomepageComponent},
  {path: 'user/login', component: LoginPageComponent},
  {path: 'user/register', component: RegisterPageComponent},
  {path: 'user/myaccount', component: MyaccountPageComponent},
  {path: 'user/forgetpassword', component: ForgetpasswordPageComponent},
  {path: 'virsualization', component: VirtualizationPageComponent},
  {path: 'searching', component: SearchingPageComponent},
  {path: 'admin/dashboard', component: AdminDashboardPageComponent},
  {path: 'admin/login', component: AdminLoginComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
