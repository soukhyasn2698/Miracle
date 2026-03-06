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
  performanceTrends?: { month: string; quality: number; delivery: number; overall: number }[];
  highestRiskSuppliers?: { name: string; riskScore: number; category?: string }[];
  recentAlerts?: { message: string; date: string; supplier?: string; severity?: string }[];
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
    alertsCount: 0
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
    if (this.metrics.performanceTrends && this.metrics.performanceTrends.length > 0) {
      const reversed = [...this.metrics.performanceTrends].reverse();
      this.performanceLineData = {
        labels: reversed.map(d => d.month),
        datasets: [
          {
            data: reversed.map(d => d.quality),
            label: 'Quality',
            borderColor: '#4CAF50',
            backgroundColor: 'rgba(76, 175, 80, 0.1)',
            tension: 0.4
          },
          {
            data: reversed.map(d => d.delivery),
            label: 'Delivery',
            borderColor: '#2196F3',
            backgroundColor: 'rgba(33, 150, 243, 0.1)',
            tension: 0.4
          },
          {
            data: reversed.map(d => d.overall),
            label: 'Overall',
            borderColor: '#FF9800',
            backgroundColor: 'rgba(255, 152, 0, 0.1)',
            tension: 0.4
          }
        ]
      };
    }
  }
}