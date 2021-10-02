import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './views/login/login.component';
import { RegisterComponent } from './views/register/register.component';
import { DashboardComponent } from './views/dashboard/dashboard.component';
import { AuthGuardService } from './views/auth/auth.guard';
import { ViewFlightComponent } from './views/view-flight/view-flight.component';
import { AddFlightComponent } from './views/add-flight/add-flight.component';
import { UserDashboardComponent } from './views/user-dashboard/user-dashboard.component';
import { UserHistoryComponent } from './views/user-history/user-history.component';
import { UserBookComponent } from './views/user-book/user-book.component';
const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },
  {
    path: 'login',
    component: LoginComponent,
    data: {
      title: 'Login Page'
    }
  },
  {
    path: 'register',
    component: RegisterComponent,
    data: {
      title: 'Register Page'
    }
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    // canActivate: [AuthGuardService],
    data: {
      title: 'Dashboard'
    }

  },
  {
    path: 'dashboard/add-flight',
    component: AddFlightComponent,
    data: {
      title: 'Add Flight'
    }

  },
  {
    path: 'dashboard/view-flight/:id',
    component: ViewFlightComponent,
    data: {
      title: 'View Flight'
    }
  },
  {
    path: 'user',
    component: UserDashboardComponent,
    data: {
      title: 'User Dashboard'
    }
  },
  {
    path: 'user/history',
    component: UserHistoryComponent,
    data: {
      title: 'User History'
    }
  },
  {
    path: 'user/book-flight/:flightId/:date/:scheduleId',
    component: UserBookComponent,
    data: {
      title: 'User Book Flight'
    }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
