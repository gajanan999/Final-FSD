import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders,HttpErrorResponse } from '@angular/common/http';
import { Observable} from 'rxjs';
import { of } from 'rxjs';

import { Users } from '../shared/model/users';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(private http: HttpClient) { }
   httpOptions = {
	  headers: new HttpHeaders({
		  'Content-Type':'application/json'
	  })
  };

private users = new Users();

  url:String;
  baseURL: string ="http://localhost:8080";

   getAllUsers():Observable<Users[]> {
		console.log("inside get all Users");
		return this.http.get<Users[]>(this.baseURL+'/allusers',this.httpOptions);
	}	
	findUserByFirstName(firstName: string) {
		console.log('findUserByFirstName'+firstName);
		return this.http.get<Users>(this.baseURL+'/allusers/find/'+firstName,this.httpOptions);
	}
	createUsers(users: Users):Observable<Users> {
		console.log("AddUsers method :"+users);
		return this.http.post<Users>(this.baseURL+'/allusers',users,this.httpOptions);
	} 
	editUsers(users: Users):Observable<Users> {
		console.log("editUsers method :"+users);
		return this.http.put<Users>(this.baseURL+'/allusers/'+users.usersId,users,this.httpOptions)
	}
	deleteUser(usersId: number) {
		return this.http.delete(this.baseURL+'/allusers/delete/'+usersId,this.httpOptions);
	}

	setter(users:Users) {
		this.users = users;
	}
	getter() {
		return this.users;
	}
}