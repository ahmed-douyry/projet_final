package com.example.projet_final;

import com.example.projet_final.dtos.BankAccountDto;
import com.example.projet_final.dtos.CurrentAccountDto;
import com.example.projet_final.dtos.CustmerDto;
import com.example.projet_final.dtos.SavingAccountDto;
import com.example.projet_final.entities.*;
import com.example.projet_final.enums.AccountStatus;
import com.example.projet_final.enums.OperationType;
import com.example.projet_final.repositories.AccountOperationRepository;
import com.example.projet_final.repositories.BankAccountRepository;
import com.example.projet_final.repositories.CustomerRepository;
import com.example.projet_final.services.BankAccountService;
import com.example.projet_final.services.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class ProjetFinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetFinalApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService, CustomerRepository customerRepository) {
        return args -> {
            Stream.of("hassan", "yassine", "Aicha").forEach(name -> {
                CustmerDto custmer = new CustmerDto();
                custmer.setName(name);
                custmer.setEmail(name + "@gmail.com");
                bankAccountService.saveCustmer(custmer);
            });
            bankAccountService.listCustmers().forEach(custmer -> {;
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random() * 90000, 9000, custmer.getId());
                    bankAccountService.saveSavingBankAccount(Math.random() * 120000, 5.5, custmer.getId());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            List<BankAccountDto> bankAccounts = bankAccountService.bankAccounts();
            for(BankAccountDto bankAccount:bankAccounts){
                for (int i = 0; i < 20; i++) {
                    String accountId ;
                    if(bankAccount instanceof SavingAccountDto){
                        accountId = ((SavingAccountDto) bankAccount).getId();
                    }else {
                        accountId = ((CurrentAccountDto)bankAccount).getId();
                    }
                    bankAccountService.credit(accountId, Math.random() * 120000, "Credit");
                    bankAccountService.debit(accountId, Math.random() * 10000, "Debit");

                }
            }

        };
    }}