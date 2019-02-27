import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';

import { TaskService } from '../services/task.service';
import { Task } from '../shared/model/task';


@Component({
  selector: 'app-list-task',
  templateUrl: './list-task.component.html',
  styleUrls: ['./list-task.component.css']
})
export class ListTaskComponent implements OnInit {
  
  private tasks: Task[];
  private task: Task;
  private show: boolean = false;
 private taskStatusValue: boolean = false;
  constructor(private taskService: TaskService,private router: Router,private location: Location) { }

  ngOnInit() {
      this.taskService.getAllTasks().subscribe((tasks)=>{
      		  this.tasks = tasks;
         //   console.log(this.tasks);
      });
  }
  searchTask(form){
     this.taskService.setter(this.task);    
     console.log('inside searchtask '+this.task.taskName);
     //this.task = this.taskService.getter();
     this.show = true;
     console.log(this.task)
     this.taskService.getTaskByName(this.task.taskName).subscribe((data)=>{
		   //this.ngOnInit();  
       //this.router.navigate(['list-task']);    
	  });

  }
  editTask(task){
	  this.taskService.setter(task);
	  this.router.navigate(['edit-task']);
  }
  newTask(){
     let task = new Task();
	   this.taskService.setter(task);
     this.router.navigate(['add-task']);
  }
  finishTask(task){   
     console.log('inside finsh task angular');   
     this.taskService.finishTask(task.taskId).subscribe((data)=>{
		 this.ngOnInit();      
	  });
  } 
}
