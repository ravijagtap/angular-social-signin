import { Injectable } from '@angular/core';


@Injectable() 
export class TokenService {


    storeToken(token, email, provider) { 
        localStorage.setItem("token", token);
        localStorage.setItem("email", email);
        localStorage.setItem("provider", provider);
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