import { Injectable } from '@angular/core';


@Injectable() 
export class TokenService {


    storeToken(userData) { 
        if("FACEBOOK" === userData.provider) {
            localStorage.setItem("token", userData.authToken);
        } else {
            localStorage.setItem("token", userData.idToken);
        }
        localStorage.setItem("email", userData.email);
        localStorage.setItem("provider", userData.provider);
    }

    clearStorage() {
        localStorage.clear();
    }

    getUser() {
        return localStorage.getItem("email");
    }

    getProvider() {
        return localStorage.getItem("provider");
    }

    public isAuthenticated(): boolean {
        return !!localStorage.getItem("token");        
    }
}