package com.example.projet_final;

import com.example.projet_final.entities.*;
import com.example.projet_final.enums.AccountStatus;
import com.example.projet_final.enums.OperationType;
import com.example.projet_final.repositories.AccountOperationRepository;
import com.example.projet_final.repositories.BankAccountRepository;
import com.example.projet_final.repositories.CustomerRepository;
import com.example.projet_final.services.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class ProjetFinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetFinalApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(BankService bankService){
        return args -> {
            bankService.consulter();
        };
    }
//    @Bean
    public CommandLineRunner start(CustomerRepository customerRepository,
                            BankAccountRepository bankAccountRepository ,
                            AccountOperationRepository accountOperationRepository
    ){
        return args -> {
            Stream.of("hassan","yassine","Aicha").forEach(name->{
                Custmer custmer = new Custmer();
                custmer.setName(name);
                custmer.setEmail(name+"@gmail.com");
                customerRepository.save(custmer);
            });
             customerRepository.findAll().forEach(custmer -> {

                 CurrentAccount currentAccount = new CurrentAccount();
                 currentAccount.setId(UUID.randomUUID().toString());
                 currentAccount.setBalance(Math.random()*9000);
                 currentAccount.setCreatedAt(new Date());
                 currentAccount.setOverDraft(9000);
                 currentAccount.setCustmer(custmer);
                 currentAccount.setStatus(AccountStatus.CREATED);
                 bankAccountRepository.save(currentAccount);
                 SavingAccount savingAccount = new SavingAccount();
                 savingAccount.setId(UUID.randomUUID().toString());

                 savingAccount.setBalance(Math.random()*9000);
                 savingAccount.setCreatedAt(new Date());
                 savingAccount.setIntrestRAte(5.5);
                 savingAccount.setCustmer(custmer);
                 savingAccount.setStatus(AccountStatus.CREATED);
                 bankAccountRepository.save(savingAccount);
             });
             bankAccountRepository.findAll().forEach(bankAccount -> {;
                 for (int i = 0; i < 10; i++) {
                     AccountOperation accountOperation = new AccountOperation();
                     accountOperation.setType(Math.random()>0.5? OperationType.DEBIT:OperationType.CREDIT);
                     accountOperation.setAmount(Math.random()*12000);
                     accountOperation.setOperationDate(new Date());
                     accountOperation.setBankAccount(bankAccount);
                     accountOperationRepository.save(accountOperation);
                 }

             });
        };
    }
}
