import { bootstrapApplication } from '@angular/platform-browser';
import {ApplicationConfig} from '@angular/core';
import {provideRouter} from '@angular/router';
import { routes } from './app/app.routes';
import { App } from './app/app';

bootstrapApplication(App, {
  providers: [
    provideRouter(routes) // ✅ Only place to provide router
  ]
});