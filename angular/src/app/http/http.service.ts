import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable() 
export class HttpService {

    accessToken: string;
    homeworld: Observable<{}>;
    API_URL = "http://localhost:8088/";
    TOKEN_NAME = 'X-AUTH-TOKEN';
    PROVIDER = 'PROVIDER';



    constructor(private http: HttpClient) {
            
    }

    getHeader(): HttpHeaders {
        let headers = new HttpHeaders();                     
        headers = headers.append(this.PROVIDER, localStorage.getItem("provider"));
        headers = headers.append(this.TOKEN_NAME, localStorage.getItem("token"));        
        return headers;
    }

    executePostRequest(url, body) {
        let headers = this.getHeader();
        return this.http.post(this.API_URL + url, body, { headers }).pipe ( 
            map((res: Response) => res),
            catchError((err: any) => {                                
                this.showErrorDialog("Error connecting to backend services, please contact system administrator");
                throw err;
            }));
    }

    executeGetRequest(url) {
        let headers = this.getHeader();
        return this.http.get(this.API_URL + url, { headers }).pipe (
            map((res: Response) => res),
            catchError((err: any) => {                
                this.showErrorDialog("Error connecting to backend services, please contact system administrator");
                throw err;
            }));
    }

    executeCorePostRequest(url, body) {
        let headers = new HttpHeaders();
        if("FACEBOOK" == body.provider) {
            headers = headers.append(this.TOKEN_NAME, body.authToken);            
        } else {
            headers = headers.append(this.TOKEN_NAME, body.idToken);            
        }
        headers = headers.append(this.PROVIDER, body.provider);
        return this.http.post(this.API_URL + url, body, { headers }).pipe (
            map((res: Response) => res),
            catchError((err: any) => {                                
                throw err;
            }));
    }

    showErrorDialog(msg) {
        alert(msg);
    }
}