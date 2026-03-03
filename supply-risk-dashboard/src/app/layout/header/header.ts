import { Component } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';


@Component({
  selector: 'app-header',
  imports: [],
  templateUrl: './header.html',
  styleUrl: './header.css',
})
export class Header {
  pageTitle: string = 'Dashboard';
  pageDescription: string = 'Supplier Performance Risk Overview';

  private descriptions: { [key: string]: string } = {
    'dashboard': 'Supplier Performance Risk Overview',
    'suppliers': 'Manage and monitor supplier performance',
    'risk-assessment': 'Evaluate and score supplier risk factors',
    'performance': 'Monitor supplier KPIs and trends',
    'alerts': 'Monitor and manage supplier risk alertss',
    'reports': 'Generate and export supplier performance reports'
  };

  constructor(private router: Router) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        const url = event.urlAfterRedirects.split('/')[1];
        this.pageTitle = this.formatTitle(url);
        this.pageDescription = this.descriptions[url] || this.descriptions['dashboard'];
      }
    });
  }
  formatTitle(title: string): string {
    if (!title) return 'Dashboard';
    return title.replace('-', ' ').replace(/\b\w/g, l => l.toUpperCase());
  }
}
