import { Routes } from '@angular/router';

import { Dashboard } from './pages/dashboard/dashboard';
import { Suppliers } from './pages/suppliers/suppliers';
import { RiskAssessment } from './pages/risk-assessment/risk-assessment';
import { Performance } from './pages/performance/performance';
import { Alerts } from './pages/alerts/alerts';
import { Reports } from './pages/reports/reports';

export const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: Dashboard },
  { path: 'suppliers', component: Suppliers },
  { path: 'risk-assessment', component: RiskAssessment },
  { path: 'performance', component: Performance },
  { path: 'alerts', component: Alerts},
  { path: 'reports', component: Reports}
];

