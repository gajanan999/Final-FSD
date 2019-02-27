import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders,HttpErrorResponse } from '@angular/common/http';
import { Observable} from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';

import { Task } from '../shared/model/task';


@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http:HttpClient) {  }
  
  httpOptions = {
	  headers: new HttpHeaders({
		  'Content-Type':'application/json'
	  })
  };
  private task = new Task();
  
  baseURL: string  = "http://localhost:8080/taskmanager";

	getAllTasks():Observable<Task[]> {
		console.log("inside get all task");
		return this.http.get<Task[]>(this.baseURL+'/alltasks',this.httpOptions);
	}	
	getTaskById(taskId: string) {
		return this.http.get<Task>(this.baseURL+'/alltasks/'+taskId,this.httpOptions);
	}
	getTaskByName(taskName: string) {
		console.log('getTaskbyName'+taskName);
		return this.http.get<Task>(this.baseURL+'/alltasks/'+taskName,this.httpOptions);
	}
	addTask(task: Task):Observable<Task> {
		console.log("AddTask method :"+task);
		return this.http.post<Task>(this.baseURL+'/alltasks/',task,this.httpOptions);
	} 
	updateTask(task: Task):Observable<Task> {
		console.log("UpdateTask method :"+task);
		return this.http.put<Task>(this.baseURL+'/alltasks/'+task.taskId,task,this.httpOptions)
	}
	finishTask(taskId: number) {
		return this.http.delete(this.baseURL+'/alltasks/'+taskId,this.httpOptions);
	}
	setter(task:Task) {
		this.task = task;
	}
	getter() {
		return this.task;
	}
}
