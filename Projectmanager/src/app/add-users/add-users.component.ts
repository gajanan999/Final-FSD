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
  editUser:boolean =false;

  constructor(private usersService:UsersService,private router:Router) { }

  ngOnInit() {
     this.users = this.usersService.getter();
     this.usersService.getAllUsers().subscribe((userss)=>{
      		  this.userss = userss;
      });
 this.editUser = true;
this.users.editUser=true;

  }
  processForm(form){
    console.log("Inside ProcessForm: "+this.users);
    if(this.users.usersId == undefined){
     this.usersService.createUsers(this.users)
		  .subscribe((data)=> {
        form.reset();
			  //this.router.navigate(['add-users']);
		  });
    }
  }

  editUsers(users){
	  //this.usersService.setter(users);
    users.editUser =true;
let edit = JSON.stringify(users.editUser)
    console.log( users.editUser,"edittt",edit)
     	//  this.router.navigate(['edit-users']);
  }
  saveUsers(users){
    //users.editUser=false;
   this.usersService.createUsers(users);
  }
  createUsers(form){
 console.log("users",this.users)
     this.usersService.createUsers(this.users);
	   this.usersService.setter(this.users);
    // this.router.navigate(['add-users']);
     form.reset();
  }
  findUserByFirstName(firstName){
    console.log(firstName);
    this.usersService.findUserByFirstName(firstName).subscribe((data)=>{     
      this.users.firstName=JSON.stringify(data);
		  this.router.navigate(['add-users']);    
	  });
  }
  deleteUser(users){   
  let s:any= JSON.stringify(users.userId);
     console.log('inside finsh task angular',users.usersId,s);   
     this.usersService.deleteUser(s).subscribe((data)=>{
	 this.ngOnInit();      
  
	  });
  } 

}
