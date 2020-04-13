import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {Users} from '../Models/users';
import {Router} from '@angular/router';
import {authorhead} from './authorhead';
import {environment} from '../../environments/environment';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private currentUserNameSubject: BehaviorSubject<string>;
  private currentUserName: Observable<string>;
  private currentUserSubject: BehaviorSubject<Users>;
  private currentUser: Observable<Users>;

  constructor(private http: HttpClient,
              private router: Router) {
    this.currentUserNameSubject = new BehaviorSubject<string>(JSON.parse(sessionStorage.getItem('currentUserName')));
    this.currentUserName = this.currentUserNameSubject.asObservable();
    this.currentUserSubject = new BehaviorSubject<Users>(JSON.parse(sessionStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserNameValue(): string {
    return this.currentUserNameSubject.value;
  }

  public get currentUserValue(): Users {
    return this.currentUserSubject.value;
  }


  checkUserInfo(username: string, email: string, phonenumber: string, city: string, state: string) {

    return this.http.post<any>(`${environment.PostgresApi}/user/infoCheck`, {username, email, phonenumber, city, state})
      .pipe(map(user => {
        sessionStorage.setItem('currentUserName', JSON.stringify(user.username));
        this.currentUserNameSubject.next(user.username);
        return user;
      }));

  }
}
