import { Component, OnInit,ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import {NgForm} from '@angular/forms';

import { TaskService } from '../services/task.service';
import { Task } from '../shared/model/task';
import { ParentTask } from '../shared/model/parent-task';
import { ParentTaskService } from '../services/parent-task.service';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent implements OnInit {
  
  private task: Task;

  constructor(private taskService:TaskService,private parentTaskService:ParentTaskService,private router:Router) { }
 
  //@ViewChild(NgForm) myForm: NgForm;
 
  ngOnInit() {
    this.task = this.taskService.getter();
  }
  processForm(){
    console.log("Inside ProcessForm: "+this.task);
    if(this.task.taskId == undefined){
     this.taskService.addTask(this.task)
		  .subscribe((data)=> {
			  this.router.navigate(['list-task']);
		  });
    }
  }
  /*resetForm(){
   this.myForm.reset();
   // this.myForm.resetForm();
   // this.router.navigate(['add-task']);
  }*/
}
