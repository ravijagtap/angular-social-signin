import { Component, OnInit } from '@angular/core';
import { FacebookLoginProvider, GoogleLoginProvider, SocialAuthService } from 'angularx-social-login';
import { HttpService } from '../http/http.service';


@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css']
})
export class TopBarComponent implements OnInit {

  model: any = {};

  constructor(private socialAuthService: SocialAuthService, private http: HttpService) { }

  ngOnInit() {
  }

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
        console.log("****************************************");
        console.log(this.model);
      },
      err => {
        alert("User Not Registered.");
      }
    );
  }

}
