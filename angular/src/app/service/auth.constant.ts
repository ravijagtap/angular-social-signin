import { Pipe, PipeTransform } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

export const GOOGLE_CLIENT_ID = "994696499436-ug11f4ochhig1nl8tq9k34mp1jtevikc.apps.googleusercontent.com";
export const FACEBOOK_CLIENT_ID = "712474362979502";


@Pipe({ name: 'safeHtml' })
export class SafeHtmlPipe implements PipeTransform {
  constructor(private sanitized: DomSanitizer) { }
  transform(value) {
    return this.sanitized.bypassSecurityTrustHtml(value);
  }
}