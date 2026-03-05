import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from '../models/customer.model';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private apiUrl = 'http://localhost:8084/api/customers';

  constructor(private http: HttpClient) { }

  getCustomerBySupplierId(supplierId: number): Observable<Customer> {
    return this.http.get<Customer>(`${this.apiUrl}/supplier/${supplierId}`);
  }
}
