import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders,HttpErrorResponse } from '@angular/common/http';
import { Observable} from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';

import { ParentTask } from '../shared/model/parent-task';


@Injectable({
  providedIn: 'root'
})
export class ParentTaskService {

  constructor(private http:HttpClient) {  }
  
  httpOptions = {
	  headers: new HttpHeaders({
		  'Content-Type':'application/json'
	  })
  };
  private parentTask = new ParentTask();
  
  baseURL: string  = "http://localhost:8080/parenttaskmanager";

	getAllParentTasks():Observable<ParentTask[]> {
		return this.http.get<ParentTask[]>(this.baseURL+'/allparenttasks',this.httpOptions);
	}	
	getParentTaskById(id: string) {
		return this.http.get<ParentTask>(this.baseURL+'/allparenttasks/'+id,this.httpOptions);
	}	
	addParentTask(parentTask: ParentTask):Observable<ParentTask> {
		console.log("AddParentTask method :"+parentTask);
		return this.http.post<ParentTask>(this.baseURL+'/allparenttasks/',parentTask,this.httpOptions);
	} 
	updateParentTask(parentTask: ParentTask):Observable<ParentTask> {
		console.log("UpdateParentTask method :"+parentTask);
		return this.http.put<ParentTask>(this.baseURL+'/allparenttasks/'+parentTask.parentTaskId,parentTask,this.httpOptions)
	}
	deleteParentTask(id: string) {
		return this.http.delete(this.baseURL+'/allparenttasks/'+id,this.httpOptions);
	}
	setter(parentTask:ParentTask) {
		this.parentTask = parentTask;
	}
	getter() {
		return this.parentTask;
	}
}
