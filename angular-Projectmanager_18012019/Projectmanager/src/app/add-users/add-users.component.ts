import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {NgForm} from '@angular/forms';
import { Observable,of } from 'rxjs';

import { UsersService } from '../services/users.service';
import { Users } from '../shared/model/users';

@Component({
  selector: 'app-add-users',
  templateUrl: './add-users.component.html',
  styleUrls: ['./add-users.component.css']
})
export class AddUsersComponent implements OnInit {
  users: Users = new Users();
  private userss: Users[];
  private show: boolean = false;
  private usersStatusValue: boolean = false;

  constructor(private usersService:UsersService,private router:Router) { }

  ngOnInit() {
     this.users = this.usersService.getter();
     this.usersService.getAllUsers().subscribe((userss)=>{
      		  this.userss = userss;
      });
  }
  processForm(){
    console.log("Inside ProcessForm: "+this.users);
    if(this.users.usersId == undefined){
     this.usersService.createUsers(this.users)
		  .subscribe((data)=> {
			  this.router.navigate(['add-users']);
		  });
    }
  }

  editUsers(users){
	  this.usersService.setter(users);
	  this.router.navigate(['edit-users']);
  }
  createUsers(){
     let users = new Users();
	   this.usersService.setter(users);
     this.router.navigate(['add-users']);
  }
  findUserByFirstName(firstName){
    console.log(firstName);
    this.usersService.findUserByFirstName(firstName).subscribe((data)=>{     
      this.users.firstName=JSON.stringify(data);
		  this.router.navigate(['add-users']);    
	  });
  }
  deleteUser(users){   
     console.log('inside finsh task angular');   
     this.usersService.deleteUser(users.usersId).subscribe((data)=>{
		 this.ngOnInit();      
	  });
  } 

}
