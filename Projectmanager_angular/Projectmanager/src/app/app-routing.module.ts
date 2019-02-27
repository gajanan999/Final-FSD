import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes,RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { LoginUserComponent } from './login-user/login-user.component';
import { RegisterUserComponent } from './register-user/register-user.component';
import { AddTaskComponent } from './add-task/add-task.component';
import { EditTaskComponent } from './edit-task/edit-task.component';
import { ListTaskComponent } from './list-task/list-task.component';
import { AddProjectComponent } from './add-project/add-project.component';
import { AddUsersComponent } from './add-users/add-users.component';

const routes: Routes =[ 
  {path: 'add-task',component:AddTaskComponent}, 
  {path: 'list-task',component:ListTaskComponent}, 
  {path: 'edit-task',component:EditTaskComponent},
  {path: 'login-user',component:LoginUserComponent},
  {path: 'register-user',component:RegisterUserComponent},
  {path: 'add-project',component:AddProjectComponent},
   {path: 'add-users',component:AddUsersComponent},
  {path: '',component:LoginUserComponent} 
  ];
  
@NgModule({
	imports:[RouterModule.forRoot(routes)],
	exports:[RouterModule]
})

export class AppRoutingModule{}
export const routingComponents = [LoginUserComponent,ListTaskComponent,AddTaskComponent,EditTaskComponent,RegisterUserComponent,AddProjectComponent,AddUsersComponent];
