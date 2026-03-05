import { Component, OnInit, ChangeDetectorRef, PLATFORM_ID, Inject } from '@angular/core';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { LucideAngularModule, MapPin, User, Mail } from 'lucide-angular';
import { SupplierService } from '../../services/supplier.service';
import { PerformanceService } from '../../services/performance.service';
import { CustomerService } from '../../services/customer.service';
import { Supplier } from '../../models/supplier.model';
import { Performance } from '../../models/performance.model';
import { Customer } from '../../models/customer.model';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-suppliers',
  imports: [CommonModule, LucideAngularModule,FormsModule],
  templateUrl: './suppliers.html',
  styleUrl: './suppliers.css',
})
export class Suppliers implements OnInit {
  suppliers: Supplier[] = [];
  filteredSuppliers: Supplier[] = [];
  loading: boolean = true;
  selectedSupplier: Supplier | null = null;
  performanceMetrics: Performance[] = [];
  currentPerformance: Performance | null = null;
  currentCustomer: Customer | null = null;

  
  // Filter properties
  searchText: string = '';
  selectedCategory: string = '';
  selectedRiskLevel: string = '';
  selectedStatus: string = '';
  uniqueCategories: string[] = [];
  uniqueStatuses: string[] = [];

  // Lucide icons
  readonly MapPin = MapPin;
  readonly User = User;
  readonly Mail = Mail;

  constructor(
    private supplierService: SupplierService,
    private performanceService: PerformanceService,
    private customerService: CustomerService,
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
        this.filteredSuppliers = [...data];
        this.extractUniqueValues();
        this.applyFilters();
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

  refreshSuppliers(): void {
    this.loadSuppliers();
  }

  extractUniqueValues(): void {
    // Extract unique categories
    this.uniqueCategories = [...new Set(this.suppliers.map(s => s.category))].sort();
    
    // Extract unique statuses
    this.uniqueStatuses = [...new Set(this.suppliers.map(s => s.status))].sort();
  }

  applyFilters(): void {
    this.filteredSuppliers = this.suppliers.filter(supplier => {
      // Search text filter
      const matchesSearch = !this.searchText || 
        supplier.name.toLowerCase().includes(this.searchText.toLowerCase()) ||
        supplier.code.toLowerCase().includes(this.searchText.toLowerCase()) ||
        supplier.location.toLowerCase().includes(this.searchText.toLowerCase());

      // Category filter
      const matchesCategory = !this.selectedCategory || supplier.category === this.selectedCategory;

      // Risk level filter
      const matchesRiskLevel = !this.selectedRiskLevel || this.getRiskLevel(supplier.riskScore) === this.selectedRiskLevel;

      // Status filter
      const matchesStatus = !this.selectedStatus || supplier.status === this.selectedStatus;

      return matchesSearch && matchesCategory && matchesRiskLevel && matchesStatus;
    });

    this.cdr.detectChanges();
  }

  getRiskLevel(score: number): string {
    if (score < 30) return 'low';
    if (score < 60) return 'medium';
    if (score < 80) return 'high';
    return 'critical';
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
    this.loadCustomerForSupplier(supplier.id);
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

  loadCustomerForSupplier(supplierId: number): void {
    this.customerService.getCustomerBySupplierId(supplierId).subscribe({
      next: (data) => {
        this.currentCustomer = data;
        this.cdr.detectChanges();
      },
      error: (error) => {
        console.error('Error fetching customer for supplier:', error);
        this.currentCustomer = null;
      }
    });
  }

  closeDetails(): void {
    this.selectedSupplier = null;
  }

  getRiskScoreClass(score: number): string {
    if (score < 30) return 'green';
    if (score < 60) return 'orange';
    if (score < 80) return 'red';
    return 'darkRed';
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
