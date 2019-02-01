import { Injectable } from '@angular/core';
import { User } from '../mentorsearch/user.model';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }

  register(user: User): Observable<any> {
    return this.httpClient.post('api/registration', user);
  }

  fetchAllUserts(){
    return this.httpClient.get('api/users/all');
  }
  
}
