import { Component, OnInit, PLATFORM_ID, inject, ChangeDetectorRef } from '@angular/core';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { NgChartsModule } from 'ng2-charts';
import { ChartData, ChartOptions } from 'chart.js';

export interface DashboardMetrics {
  totalSuppliers: number;
  activeRisks: number;
  avgRiskScore: number;
  alertsCount: number;
  riskDistribution?: { low: number; medium: number; high: number; critical: number };
  performanceTrends?: { quality: number; delivery: number; overall: number }[];
  highestRiskSuppliers?: { name: string; riskScore: number; category?: string }[];
  recentAlerts?: { message: string; date: string; supplier?: string }[];
}

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, NgChartsModule],
  templateUrl: './dashboard.html',
  styleUrls: ['./dashboard.css']
})
export class Dashboard implements OnInit {
  private apiUrl = '/api/dashboard';

  metrics: DashboardMetrics = {
    totalSuppliers: 0,
    activeRisks: 0,
    avgRiskScore: 0,
    alertsCount: 0,
    
    performanceTrends: [
      { quality: 40, delivery: 75, overall: 72 },
      { quality: 60, delivery: 52, overall: 64 },
      { quality: 80, delivery: 35, overall: 52 },
      { quality: 48, delivery: 62, overall: 70 }
    ],
    
    recentAlerts: [
      { message: 'Defect Rate Exceeded Threshold', supplier: 'Dragon Manufacturing Ltd.', date: 'Sep 29, 04:12 AM' },
      { message: 'Consecutive Late Shipments', supplier: 'Global Logistics Inc.', date: 'Sep 28, 11:45 PM' },
      { message: 'IATF 16949 Certification Expiring', supplier: 'Quality Parts Co.', date: 'Sep 27, 02:30 PM' }
    ]
  };

  // ChartData
  riskPieData: ChartData<'doughnut'> = {
    labels: [],
    datasets: [{ data: [] }]
  };

  performanceLineData: ChartData<'line'> = {
    labels: [],
    datasets: []
  };

  pieChartOptions: ChartOptions<'doughnut'> = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: {
        position: 'bottom',
        labels: {
          color: '#8b92a7',
          padding: 15,
          font: {
            size: 12
          }
        }
      }
    }
  };

  lineChartOptions: ChartOptions<'line'> = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: {
        position: 'bottom',
        labels: {
          color: '#8b92a7',
          padding: 15,
          font: {
            size: 12
          }
        }
      }
    },
    scales: {
      y: {
        beginAtZero: true,
        grid: {
          color: '#2a3142'
        },
        ticks: {
          color: '#8b92a7'
        }
      },
      x: {
        grid: {
          color: '#2a3142'
        },
        ticks: {
          color: '#8b92a7'
        }
      }
    }
  };

  metricsLoaded = false;
  private platformId = inject(PLATFORM_ID);
  private cdr = inject(ChangeDetectorRef);

  constructor(private http: HttpClient) { }

  ngOnInit() {
    if (isPlatformBrowser(this.platformId)) {
      this.loadMetrics();
    } else {
      // During SSR, show loading state
      this.metricsLoaded = false;
    }
  }

  loadMetrics(): void {
    console.log('loadMetrics called, metricsLoaded before:', this.metricsLoaded);
    this.http.get<DashboardMetrics>(this.apiUrl).subscribe({
      next: (data) => {
        console.log('API response received:', data);
        this.metrics = { ...this.metrics, ...data };
        this.setupCharts();
        this.metricsLoaded = true;
        this.cdr.detectChanges();
        console.log('metricsLoaded set to:', this.metricsLoaded);
        console.log('Metrics loaded:', this.metrics);
      },
      error: (err) => {
        console.error('Error loading metrics, using mock data:', err);
        this.setupCharts();
        this.metricsLoaded = true;
        this.cdr.detectChanges();
        console.log('metricsLoaded set to true after error');
      }
    });
  }

  setupCharts(): void {
    // Prepare Doughnut chart data for risk distribution
    if (this.metrics.riskDistribution) {
      this.riskPieData = {
        labels: ['Low Risk', 'Medium Risk', 'High Risk', 'Critical'],
        datasets: [{
          data: [
            this.metrics.riskDistribution.low,
            this.metrics.riskDistribution.medium,
            this.metrics.riskDistribution.high,
            this.metrics.riskDistribution.critical
          ],
          backgroundColor: [
            '#10b981',
            '#f59e0b',
            '#ef4444',
            '#dc2626'
          ],
          borderWidth: 0
        }]
      };
    }

    // Prepare Line chart data for performance trends
    if (this.metrics.performanceTrends) {
      this.performanceLineData = {
        labels: this.metrics.performanceTrends.map((_, idx) => `Oct Mon${idx + 1}`),
        datasets: [
          {
            data: this.metrics.performanceTrends.map(t => t.quality),
            label: 'Quality',
            borderColor: '#3b82f6',
            backgroundColor: 'rgba(59, 130, 246, 0.1)',
            tension: 0.4
          },
          {
            data: this.metrics.performanceTrends.map(t => t.delivery),
            label: 'Delivery',
            borderColor: '#10b981',
            backgroundColor: 'rgba(16, 185, 129, 0.1)',
            tension: 0.4
          },
          {
            data: this.metrics.performanceTrends.map(t => t.overall),
            label: 'Overall',
            borderColor: '#8b5cf6',
            backgroundColor: 'rgba(139, 92, 246, 0.1)',
            tension: 0.4
          }
        ]
      };
    }
  }
}