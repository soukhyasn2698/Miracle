import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Performance } from '../models/performance.model';

@Injectable({
    providedIn: 'root'
})
export class PerformanceService {
    private apiUrl = 'http://localhost:8083/api/performance';

    constructor(private http: HttpClient) { }

    getPerformanceMetrics(): Observable<Performance[]> {
        return this.http.get<Performance[]>(this.apiUrl);
    }

    getPerformanceById(id: number): Observable<Performance> {
        return this.http.get<Performance>(`${this.apiUrl}/${id}`);
    }
}
