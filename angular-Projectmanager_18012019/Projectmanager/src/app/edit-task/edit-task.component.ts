import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { TaskService } from '../services/task.service';
import { Task } from '../shared/model/task';


@Component({
  selector: 'app-edit-task',
  templateUrl: './edit-task.component.html',
  styleUrls: ['./edit-task.component.css']
})
export class EditTaskComponent implements OnInit {
  
  private task: Task;

  constructor(private taskService:TaskService,private router:Router) { }

  ngOnInit() {
    this.task = this.taskService.getter();
  }

   updateTask(){
     this.taskService.updateTask(this.task)
		  .subscribe((data)=> {
			  this.router.navigate(['list-task']);
		  });
  }
  cancelUpdate(){
     this.router.navigate(['list-task']);
  }
}
