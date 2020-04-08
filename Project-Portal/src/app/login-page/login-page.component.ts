import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {first} from 'rxjs/operators';
import {AuthenticationService} from '../Service/authentication.service';
import {AlertServiceService} from '../Service/alert-service.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  loginForm: FormGroup;
  loading: boolean;
  submitted: boolean;
  returnUrl: string;


  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private alertService: AlertServiceService,
              private authentication: AuthenticationService) {
    if (this.authentication.currentUserValue) {
      this.router.navigate(['/home']);
    }
  }

  get f() {
    return this.loginForm.controls;
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });

    this.returnUrl =  '/';
  }

  onSubmit() {
    console.log(this.f.username.value, this.f.password.value);
    this.submitted = true;

    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    this.authentication.login(this.f.username.value, this.f.password.value, false).pipe(first()).subscribe(
      data => {
      this.router.navigate(['/home']);
    },
error => {
      this.alertService.error(error.error.message);
      this.loading = false;
    });
  }

}
