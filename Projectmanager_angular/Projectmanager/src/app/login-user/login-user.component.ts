import { Component, OnInit } from '@angular/core';
import {FormBuilder,FormGroup,Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {first} from 'rxjs/operators';

@Component({
  selector: 'app-login-user',
  templateUrl: './login-user.component.html',
  styleUrls: ['./login-user.component.css']
})
export class LoginUserComponent implements OnInit {
  
  loginForm: FormGroup;
  submitted: boolean = false;
  invalidLogin: boolean = false;
  errorMessage: string = '';

  constructor(private formBuilder: FormBuilder,private router:Router) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
		username:['',Validators.required],
		password:['',Validators.required]
	});
  }
  onSubmit() {
  	this.submitted = true;
  	if(this.loginForm.invalid) {
  		return;
  	}
  	if(this.loginForm.controls.username.value == 'admin@gmail.com' && this.loginForm.controls.password.value == 'admin@123'){
  		this.router.navigate(['list-book']);
  	}
  	else{
  		this.invalidLogin = true;
  	}
   }//onSubmit

}
