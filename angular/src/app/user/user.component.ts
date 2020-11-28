import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpService } from '../http/http.service';
import { TokenService } from '../http/token.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {  

  model: any = {};

  constructor(private tokenService: TokenService, private router: Router, private http: HttpService) {
    this.http.executePostRequest("fetchUser", {"email": tokenService.getUser()}).subscribe(
      res => {
        this.model = res;        
        console.log("-*-*-*-*-*-*-*-*-*-*");
        console.log(this.model);
      },
      err => {
      }
    ); 
    this.model.response = [
      { firstName: "Ravi" },
      { firstName: "Jagtap" }
    ];
  }

  logout() {
    this.tokenService.clearStorage();
    this.router.navigate(['/']);
  }
}
