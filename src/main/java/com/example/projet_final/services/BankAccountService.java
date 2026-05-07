package com.example.projet_final.services;

import com.example.projet_final.dtos.BankAccountDto;
import com.example.projet_final.dtos.CurrentAccountDto;
import com.example.projet_final.dtos.CustmerDto;
import com.example.projet_final.dtos.SavingAccountDto;
import com.example.projet_final.entities.BankAccount;
import com.example.projet_final.entities.CurrentAccount;
import com.example.projet_final.entities.Custmer;
import com.example.projet_final.entities.SavingAccount;
import com.example.projet_final.exceptions.BalanceNotSufisantException;
import com.example.projet_final.exceptions.BankAccountNotFoundException;
import com.example.projet_final.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    CustmerDto saveCustmer(CustmerDto custmer);
    CurrentAccountDto saveCurrentBankAccount(double initialBalance, double overDrafte, Long id) throws CustomerNotFoundException;
    SavingAccountDto saveSavingBankAccount(double initialBalance, double InterestRate, Long id) throws CustomerNotFoundException;
    List<CustmerDto> listCustmers();
    BankAccountDto getBankAccount(String accountId) throws BankAccountNotFoundException;
    void debit (String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufisantException;
    void credit(String accountId,double amount, String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSource ,String accountIdDestination , double amount) throws BankAccountNotFoundException, BalanceNotSufisantException;

    List<BankAccount> bankAccounts();

    CustmerDto getCustmer(Long id) throws CustomerNotFoundException;

    CustmerDto updateCustmer(CustmerDto custmerdto);

    void deleteCustmer(Long id) ;
//    List<BankAccount> bankAccounts();


}



