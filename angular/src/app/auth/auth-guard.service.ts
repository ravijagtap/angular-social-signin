import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { tap } from 'rxjs/operators';
import { TokenService } from '../http/token.service';

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(public tokenService: TokenService, public router: Router) {}
    canActivate(): boolean {
        if (!this.tokenService.isAuthenticated()) {
            this.router.navigate(['/']);
            return false;
        }
        return true;
    }
}