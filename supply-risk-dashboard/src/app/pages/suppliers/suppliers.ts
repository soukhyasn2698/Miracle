import { Component, OnInit, ChangeDetectorRef, PLATFORM_ID, Inject } from '@angular/core';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { LucideAngularModule, MapPin, User, Mail } from 'lucide-angular';
import { SupplierService } from '../../services/supplier.service';
import { PerformanceService } from '../../services/performance.service';
import { Supplier } from '../../models/supplier.model';
import { Performance } from '../../models/performance.model';


@Component({
  selector: 'app-suppliers',
  imports: [CommonModule, LucideAngularModule],
  templateUrl: './suppliers.html',
  styleUrl: './suppliers.css',
})
export class Suppliers implements OnInit {
  suppliers: Supplier[] = [];
  loading: boolean = true;
  selectedSupplier: Supplier | null = null;
  performanceMetrics: Performance[] = [];
  currentPerformance: Performance | null = null;

  // Lucide icons
  readonly MapPin = MapPin;
  readonly User = User;
  readonly Mail = Mail;

  constructor(
    private supplierService: SupplierService,
    private performanceService: PerformanceService,
    private cdr: ChangeDetectorRef,
    @Inject(PLATFORM_ID) private platformId: Object
  ) { }

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.loadSuppliers();
      this.loadPerformanceMetrics();
    }
  }

  loadSuppliers(): void {
    this.supplierService.getSuppliers().subscribe({
      next: (data) => {
        this.suppliers = [...data];
        this.loading = false;
        this.cdr.detectChanges();
      },
      error: (error) => {
        console.error('Error fetching suppliers:', error);
        this.loading = false;
        this.cdr.detectChanges();
      }
    });
  }

  loadPerformanceMetrics(): void {
    this.performanceService.getPerformanceMetrics().subscribe({
      next: (data) => {
        this.performanceMetrics = data;
        this.cdr.detectChanges();
      },
      error: (error) => {
        console.error('Error fetching performance metrics:', error);
      }
    });
  }

  selectSupplier(supplier: Supplier): void {
    this.selectedSupplier = supplier;
    this.loadPerformanceForSupplier(supplier.id);
  }

  loadPerformanceForSupplier(supplierId: number): void {
    this.performanceService.getPerformanceById(supplierId).subscribe({
      next: (data) => {
        this.currentPerformance = data;
        this.cdr.detectChanges();
      },
      error: (error) => {
        console.error('Error fetching performance for supplier:', error);
        this.currentPerformance = null;
      }
    });
  }

  closeDetails(): void {
    this.selectedSupplier = null;
  }

  getRiskScoreClass(score: number): string {
    if (score < 30) return 'green';
    if (score < 60) return 'orange';
    return 'red';
  }

  getStatusClass(status: string): string {
    return status.toLowerCase() === 'active' ? 'green' : 'orange';
  }

  getDeliveryScore(): number {
    return this.currentPerformance?.deliveryScore || 0;
  }

  getQualityScore(): number {
    return this.currentPerformance?.qualityScore || 0;
  }

  getComplianceScore(): number {
    return this.currentPerformance?.complianceScore || 0;
  }

  getCostScore(): number {
    return this.currentPerformance?.costScore || 0;
  }
}
