import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { UserService } from '../services/user.service'

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {

  registerForm: FormGroup;
  loading = false;
  submitted = false;
 
  constructor(  
		private formBuilder: FormBuilder,
        private router: Router,
        private userService: UserService) { }

   ngOnInit() {
        this.registerForm = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', [Validators.required, Validators.minLength(6)]],
            email: ['', Validators.required],
			category: ['', Validators.required],            
        });
    }
	// convenience getter for easy access to form fields
    get f() { return this.registerForm.controls; }
	
	onSubmit() {
        this.submitted = true;
        // stop here if form is invalid
        if (this.registerForm.invalid) {
            return;
        }

        this.loading = true;
        this.userService.register(this.registerForm.value)
            .pipe(first())
            .subscribe(
                data => {
                  //  this.alertService.success('Registration successful', true);
                    this.router.navigate(['/login-user']);
                },
                error => {
                   // this.alertService.error(error);
                    this.loading = false;
                });
    }
}
