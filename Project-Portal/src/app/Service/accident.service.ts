import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class AccidentService {

  constructor(private http: HttpClient) {
  }

  getRoadInfo(state: string, city: string, road: string) {
    return this.http.get<Marker[]>(`${environment.PostgresApi}/accident/accidentsByRoad/${state}/${city}/${road}`);
  }
}

interface Marker {
  latitude: number;
  longitude: number;
}
