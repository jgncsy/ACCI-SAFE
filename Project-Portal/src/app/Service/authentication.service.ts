import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {Users} from '../Models/users';
import {environment} from '../../environments/environment';
import {Router} from '@angular/router';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private currentUserSubject: BehaviorSubject<Users>;
  private currentUser: Observable<Users>;

  constructor(private http: HttpClient,
              private router: Router) {
    this.currentUserSubject = new BehaviorSubject<Users>(JSON.parse(sessionStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): Users {
    return this.currentUserSubject.value;
  }

  login(username: string, password: string, isAdminLogin: boolean) {
    if (!isAdminLogin) {
      return this.http.post<any>(`${environment.PostgresApi}/user/login`, {username, password})
        .pipe(map(user => {
          sessionStorage.setItem('currentUser', JSON.stringify(user));
          this.currentUserSubject.next(user);
          return user;
        }));
    } else {
      return this.http.post<any>(`${environment.PostgresApi}/admin/login`, {username, password})
        .pipe(map(admin => {
          sessionStorage.setItem('currentUser', JSON.stringify(admin));
          this.currentUserSubject.next(admin);
          return admin;
        }));
    }

  }

  register(username: string, password: string, email: string, phonenumber: string, city: string, state: string) {
    return this.http.post<any>(`${environment.PostgresApi}/user/signup`, {username, password, email, phonenumber, city, state})
      .pipe(map(user => {
        sessionStorage.setItem('currentUser', JSON.stringify(user));
        this.currentUserSubject.next(user);
        return user;
      }));
  }


  logout() {
    sessionStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
    this.router.navigate(['login']);
  }
}
