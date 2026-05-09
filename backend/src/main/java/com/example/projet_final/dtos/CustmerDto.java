package com.example.projet_final.dtos;

import com.example.projet_final.entities.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data @NoArgsConstructor @AllArgsConstructor
public class CustmerDto {

    private Long id;
    private String name ;
    private String email ;


}
