import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserRequest, UserRequestLogin, UserResponse, UserResponseLogin, ResponseModel } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8081/api/user';

  constructor(private http: HttpClient) {}

  register(user: UserRequest): Observable<ResponseModel<UserResponse>> {
    return this.http.post<ResponseModel<UserResponse>>(`${this.apiUrl}/register`, user);
  }

  login(credentials: UserRequestLogin): Observable<ResponseModel<UserResponseLogin>> {
    return this.http.post<ResponseModel<UserResponseLogin>>(`${this.apiUrl}/login`, credentials);
  }

  getAllUsers(): Observable<ResponseModel<UserResponse[]>> {
    return this.http.get<ResponseModel<UserResponse[]>>(`${this.apiUrl}/all`);
  }
}

