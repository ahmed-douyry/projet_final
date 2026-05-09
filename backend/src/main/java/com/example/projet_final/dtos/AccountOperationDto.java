package com.example.projet_final.dtos;

import com.example.projet_final.entities.BankAccount;
import com.example.projet_final.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data

public class AccountOperationDto {
    private  Long id ;
    private Date operationDate ;
    private double amount ;
    private OperationType type ;
    private String description ;
}
