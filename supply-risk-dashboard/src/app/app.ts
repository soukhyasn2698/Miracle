import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { provideRouter } from '@angular/router';

import { Sidebar } from './layout/sidebar/sidebar';
import { Header } from './layout/header/header';

import { Dashboard } from './pages/dashboard/dashboard';
import { Suppliers } from './pages/suppliers/suppliers';
import { RiskAssessment } from './pages/risk-assessment/risk-assessment';
import { Performance } from './pages/performance/performance';
import { Alerts } from './pages/alerts/alerts';
import { Reports } from './pages/reports/reports';

// Define routes
export const routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: Dashboard },
  { path: 'suppliers', component: Suppliers },
  { path: 'risk-assessment', component: RiskAssessment },
  { path: 'performance', component: Performance },
  { path: 'alerts', component: Alerts },
  { path: 'reports', component: Reports }
];

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet, 
    Sidebar,
    Header
  ],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class App {}