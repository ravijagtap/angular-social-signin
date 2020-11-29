import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FacebookLoginProvider, GoogleLoginProvider, SocialAuthService } from 'angularx-social-login';
import { HttpService } from '../http/http.service';
import { TokenService } from '../http/token.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent {

  model: any = {};

  panel = "none";

  constructor(private socialAuthService: SocialAuthService, 
      private http: HttpService, 
      private tokenService: TokenService,
      private router: Router) { }

  socialSignIn(socialPlatform: string) {
    let socialPlatformProvider;
    if (socialPlatform === "google") {
      socialPlatformProvider = GoogleLoginProvider.PROVIDER_ID;
    } else if (socialPlatform === "facebook") {
      socialPlatformProvider = FacebookLoginProvider.PROVIDER_ID;
    }

    this.socialAuthService.signIn(socialPlatformProvider).then(
      (userData) => {
        console.log(userData);
        this.validateTokenAndLogin(userData);
    });
  }

  validateTokenAndLogin(userData) {
    this.http.executeCorePostRequest("v1/validateTokenAndGetUserDetails", userData).subscribe(
      res => {
        this.model = res;
        console.log("******************START**********************");
        console.log(userData);
        console.log(this.model);
        console.log("***********************END*****************");
        if(this.model.errors) {
          alert(this.model.errors.details + ", please Sign-Up");
        } else {
          this.tokenService.storeToken(userData);
          this.navigate();
        }
        
      },
      err => {
        alert("User Not Registered, please sign-up");
        this.panel = "signup";
      }
    );
  }

  socialSignUp(socialPlatform: string) {
    let socialPlatformProvider;
    if (socialPlatform === "google") {
      socialPlatformProvider = GoogleLoginProvider.PROVIDER_ID;
    } else if (socialPlatform === "facebook") {
      socialPlatformProvider = FacebookLoginProvider.PROVIDER_ID;
    }

    this.socialAuthService.signIn(socialPlatformProvider).then(
      (userData) => {
        console.log(userData);
        this.validateTokenAndSignUp(userData);
    });
  }

  validateTokenAndSignUp(userData) {
    this.http.executeCorePostRequest("v1/validateTokenAndSignUp", userData).subscribe(
      res => {
        this.model = res;
        console.log("****************************************");
        console.log(this.model);
        if(this.model.errors) {
          alert(this.model.errors.details + ", please Sign-In");
        } else {
          this.tokenService.storeToken(userData);
          this.navigate();
        }
      },
      err => {
        alert("User Already Registered, please sign-in");
        this.panel = "signin";
      }
    );
  }

  navigate() {
    this.router.navigate(['/user']);
  }
}
