package com.example.projet_final.dtos;

import com.example.projet_final.entities.AccountOperation;
import com.example.projet_final.entities.Custmer;
import com.example.projet_final.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public  class SavingAccountDto  extends BankAccountDto {

    private String id ;
    private double balance ;
    private Date createdAt ;
    private AccountStatus status ;
    private CustmerDto custmerDto ;
    private double intrestRAte ;

}
