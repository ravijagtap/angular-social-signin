import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { TopBarComponent } from './top-bar/top-bar.component';

import {
  GoogleLoginProvider,
  FacebookLoginProvider,
  SocialLoginModule,
  SocialAuthServiceConfig
} from 'angularx-social-login';

import { FACEBOOK_CLIENT_ID, GOOGLE_CLIENT_ID, SafeHtmlPipe } from './service/auth.constant';
import { HttpService } from './http/http.service';
import { HttpClientModule } from '@angular/common/http';
import {MatCardModule} from '@angular/material/card';
import { AuthComponent } from './auth/auth.component';
import { TokenService } from './http/token.service';
import { AuthGuard } from './auth/auth-guard.service';
import { UserComponent } from './user/user.component';

/*export function getAuthServiceConfigs() {

  let config = new AuthServiceConfig(
    [ 
      {
        id: GoogleLoginProvider.PROVIDER_ID,
        provider: new GoogleLoginProvider(GOOGLE_CLIENT_ID)
      },
      {
        id: FacebookLoginProvider.PROVIDER_ID,
        provider: new FacebookLoginProvider(FACEBOOK_CLIENT_ID)
      }
    ]);
  return config;
}*/

@NgModule({
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatCardModule,
    SocialLoginModule,
    RouterModule.forRoot([
      { path: '', component: AuthComponent },
      { path: 'user', component: UserComponent, canActivate: [AuthGuard] },
    ])
  ],
  declarations: [
    AppComponent,
    TopBarComponent,
    SafeHtmlPipe,
    UserComponent,
    AuthComponent
  ],
  providers: [ HttpService, TokenService, AuthGuard, {
    provide: 'SocialAuthServiceConfig',
      useValue: {
        autoLogin: false,
        providers: [
          {
            id: GoogleLoginProvider.PROVIDER_ID,
            provider: new GoogleLoginProvider(
              GOOGLE_CLIENT_ID
            )
          },
          {
            id: FacebookLoginProvider.PROVIDER_ID,
            provider: new FacebookLoginProvider(
              FACEBOOK_CLIENT_ID
            )
          }
        ]
      } as SocialAuthServiceConfig,
  }],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
