import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders,HttpErrorResponse } from '@angular/common/http';
import { Observable} from 'rxjs';
import { of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { interval } from 'rxjs';
import { Users } from '../shared/model/users';
import { switchMap} from 'rxjs/operators';
import { Project } from '../shared/model/project';


@Injectable({
  providedIn: 'root'
})
export class ProjectService {
result:any;
  constructor(private http: HttpClient) { }
   httpOptions = {
	  headers: new HttpHeaders({
		  'Content-Type':'application/json'
	  })
  };
  private project = new Project();

  url:String;
  baseURL: string ="http://localhost:8080/projectmanager";

   getAllProjects():Observable<Project[]> {
		console.log("inside get all projects");

this.result=interval(1000).pipe(switchMap(() => {
        return this.http.get<Project[]>(this.baseURL+'/allprojects',this.httpOptions);
        }));
      return this.result;

		
	}	
	findProjectByProjectName(projectName: string) {
		console.log('findProjectByProjectName'+projectName);
		return this.http.get<Project>(this.baseURL+'/allprojects/'+projectName,this.httpOptions);
	}
	createProject(project: Project):Observable<Project> {
		console.log("AddProject method :"+project);
		return this.http.post<Project>(this.baseURL+'/allprojects/',project,this.httpOptions);
	} 
	editProject(project: Project):Observable<Project> {
		console.log("editProject method :"+project);
		return this.http.put<Project>(this.baseURL+'/allprojects/'+project.projectId,project,this.httpOptions)
	}
	suspendProject(projectId: number) {
		return this.http.delete(this.baseURL+'/allprojects/'+projectId,this.httpOptions);
	}
	
	setter(project:Project) {
		this.project = project;
	}
	getter() {
		return this.project;
	}
 	getManagerByfirstName(userFirstName: String){
		console.log("userfirstname",userFirstName);
  	 return this.http.get("http://localhost:8080/alluserss/find/"+userFirstName,this.httpOptions);
  }
}
