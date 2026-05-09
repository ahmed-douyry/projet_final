import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../services/Customer/customers';
import { catchError, finalize, Observable, throwError } from 'rxjs';
import { Customer } from '../model/customer.model';

@Component({
  selector: 'app-customers',
  imports: [CommonModule],
  templateUrl: './customers.html',
  styleUrl: './customers.css',
})
export class Customers implements OnInit {
  customers$!: Observable<Array<Customer>>;
  errorMessage: any = null;
  loading = false;
  constructor(private customerService: CustomerService) {

  }
  ngOnInit(): void {
    this.loading = true;
    this.errorMessage = null;
    this.customers$ = this.customerService.getCustomers().pipe(
      catchError((error) => {
        this.errorMessage = error;
        return throwError(() => error);
      }),
      finalize(() => {
        this.loading = false;
      })
    );
  }

}
