import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { StorageService } from './auth/service/storage/storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'car_rent';
  rt:boolean=false;

  isCustomerLoggedIn: boolean = false;
isAdminLoggedIn: boolean = false;

constructor(private router: Router) { }

ngOnInit() {
  this.router.events.subscribe(event => {
    if (event.constructor.name === "NavigationEnd") {
      this.rt=(this.router.url === '/login' || this.router.url === '/register');
      this.isAdminLoggedIn = StorageService.isAdminLoggedIn();
      this.isCustomerLoggedIn = StorageService.isCustomerLoggedIn();
    }
  })
}

logout() {
  StorageService.logout();
  this.router.navigateByUrl("/login");
}

}



