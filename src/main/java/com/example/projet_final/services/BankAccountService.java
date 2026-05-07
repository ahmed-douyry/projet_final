package com.example.projet_final.services;

import com.example.projet_final.entities.BankAccount;
import com.example.projet_final.entities.CurrentAccount;
import com.example.projet_final.entities.Custmer;
import com.example.projet_final.entities.SavingAccount;
import com.example.projet_final.exceptions.BalanceNotSufisantException;
import com.example.projet_final.exceptions.BankAccountNotFoundException;
import com.example.projet_final.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    Custmer saveCustmer(Custmer custmer);
    CurrentAccount saveCurrentBankAccount(double initialBalance, double overDrafte, Long id) throws CustomerNotFoundException;
    SavingAccount saveSavingBankAccount(double initialBalance, double InterestRate, Long id) throws CustomerNotFoundException;
    List<Custmer> listCustmers();
    BankAccount getBankAccount(String accountId);
    void debit (String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufisantException;
    void credit(String accountId,double amount, String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSource ,String accountIdDestination , double amount) throws BankAccountNotFoundException, BalanceNotSufisantException;

    List<BankAccount> bankAccounts();
//    List<BankAccount> bankAccounts();


}



