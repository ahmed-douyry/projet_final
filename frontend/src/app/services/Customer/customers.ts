import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from '../../model/customer.model';

@Injectable({
  providedIn: 'root',
})
export class CustomerService{
  constructor(private http:HttpClient) {}
  customers: any;
  getCustomers() : Observable<Array<Customer>> {
    return this.http.get<Array<Customer>>('http://localhost:8080/custmers');
  }
}
