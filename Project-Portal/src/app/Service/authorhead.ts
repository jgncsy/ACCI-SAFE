import { Injectable } from '@angular/core';

import { BehaviorSubject } from 'rxjs';

export function authorhead() {

  const currentUserSubject = new BehaviorSubject(JSON.parse(sessionStorage.getItem('currentUser')));
  currentUserSubject.asObservable();
  const user = currentUserSubject.value;

  if (user && user.token) {
    return { Authorization: 'Bearer ' + user.token };
  } else {
    return {};
  }
}
