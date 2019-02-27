import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';

import { User } from '../shared/model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }
  
  subjectURL: string ="http://localhost:8080/library";
  
	getAll() {
        return this.http.get<User[]>(`/users`);
    }

    getById(userId: number) {
        return this.http.get(`/users/` + userId);
    }

    register(user: User) {
        return this.http.post(`/users/register`, user);
    }

    update(user: User) {
        return this.http.put(`/users/` + user.userId, user);
    }

    delete(userId: number) {
        return this.http.delete(`/users/` + userId);
    }

  
  
  }
