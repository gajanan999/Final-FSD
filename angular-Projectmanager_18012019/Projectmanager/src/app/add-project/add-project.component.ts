import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {NgForm} from '@angular/forms';
import { Observable,of } from 'rxjs';

import { ProjectService } from '../services/project.service';
import { Project } from '../shared/model/project';



@Component({
  selector: 'app-add-project',
  templateUrl: './add-project.component.html',
  styleUrls: ['./add-project.component.css']
})
export class AddProjectComponent implements OnInit {

  project: Project = new Project();
  private projects: Project[];
  private show: boolean = false;
  private projectStatusValue: boolean = false;

  constructor(private projectService:ProjectService,private router:Router) { }

  ngOnInit() {
     this.project = this.projectService.getter();
     this.projectService.getAllProjects().subscribe((projects)=>{
      		  this.projects = projects;
      });
  }
  processForm(){
    console.log("Inside ProcessForm: "+this.project);
    if(this.project.projectId == undefined){
     this.projectService.createProject(this.project)
		  .subscribe((data)=> {
			  this.router.navigate(['add-project']);
		  });
    }
  }

   findProjectByProjectName(form){
     this.projectService.setter(this.project);    
     console.log('inside searchproject '+this.project.projectName);
     this.show = true;
     console.log(this.project)
     this.projectService.findProjectByProjectName(this.project.projectName).subscribe((data)=>{
	  });

  }
  editProject(project){
	  this.projectService.setter(project);
	  this.router.navigate(['edit-project']);
  }
  createProject(){
     let project = new Project();
	   this.projectService.setter(project);
     this.router.navigate(['add-project']);
  }
  searchManager(managerFirstName){
    console.log(managerFirstName);
    this.projectService.getManagerByfirstName(managerFirstName).subscribe((data)=>{     
      this.project.firstName=JSON.stringify(data);
		  this.router.navigate(['add-project']);    
	  });
  }
  suspendProject(project){   
     console.log('inside finsh task angular');   
     this.projectService.suspendProject(project.projectId).subscribe((data)=>{
		 this.ngOnInit();      
	  });
  } 

}
