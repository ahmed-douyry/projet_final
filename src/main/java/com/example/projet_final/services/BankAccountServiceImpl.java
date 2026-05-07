package com.example.projet_final.services;

import com.example.projet_final.entities.*;
import com.example.projet_final.enums.OperationType;
import com.example.projet_final.exceptions.BalanceNotSufisantException;
import com.example.projet_final.exceptions.BankAccountNotFoundException;
import com.example.projet_final.exceptions.CustomerNotFoundException;
import com.example.projet_final.repositories.AccountOperationRepository;
import com.example.projet_final.repositories.BankAccountRepository;
import com.example.projet_final.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
@Transactional
@Slf4j
public class BankAccountServiceImpl implements  BankAccountService{
    private BankAccountRepository bankAccountRepository;
    private CustomerRepository customerRepository ;
    private AccountOperationRepository accountOperationRepository ;
//    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    @Override
    public Custmer saveCustmer(Custmer custmer) {
        log.info("Saving custmer");
        Custmer savedCustmer = customerRepository.save(custmer);

        return savedCustmer;
    }

    @Override
    public CurrentAccount saveCurrentBankAccount(double initialBalance, double overDrafte, Long id) throws CustomerNotFoundException {
        CurrentAccount  bankAccount  = new CurrentAccount();
        Custmer custmer = customerRepository.findById(id).orElse(null);
        if(custmer == null){
            throw  new CustomerNotFoundException("Customer not found");
        }
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreatedAt(new Date());
        bankAccount.setBalance(initialBalance);
        bankAccount.setOverDraft(overDrafte);
        bankAccount.setCustmer(custmer);
        CurrentAccount savedCurentaccount  = bankAccountRepository.save(bankAccount);
        return savedCurentaccount;

    }

    @Override
    public SavingAccount saveSavingBankAccount(double initialBalance, double InterestRate, Long id) throws CustomerNotFoundException {
        SavingAccount  bankAccount  = new SavingAccount();
        Custmer custmer = customerRepository.findById(id).orElse(null);
        if(custmer == null){
            throw  new CustomerNotFoundException("Customer not found");
        }
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreatedAt(new Date());
        bankAccount.setBalance(initialBalance);
        bankAccount.setIntrestRAte(InterestRate);
        bankAccount.setCustmer(custmer);
        SavingAccount savedSavingAccount  = bankAccountRepository.save(bankAccount);
        return savedSavingAccount;

    }


    @Override
    public List<Custmer> listCustmers() {
        return  customerRepository.findAll();
    }

    @Override
    public BankAccount getBankAccount(String accountId) {
        return bankAccountRepository.findById(accountId).orElse(null);
    }

    @Override
    public void debit(String accountId, double amount, String description) throws BankAccountNotFoundException , BalanceNotSufisantException {
        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElse(null);
        if(bankAccount == null){
            throw new BankAccountNotFoundException("Bank account not found");
        }
        if(bankAccount.getBalance()<amount){
            throw new BalanceNotSufisantException("Balance not sufficient");
        }
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setOperationDate(new Date());
        accountOperation.setAmount(amount);
        accountOperation.setType(OperationType.DEBIT);
        accountOperation.setBankAccount(bankAccount);
        accountOperation.setDescription(description);
        accountOperationRepository.save(accountOperation);
//        bankAccount.getAccountOperations().add
//                (accountOperationRepository.save(new AccountOperation(null,new Date(),amount, OperationType.DEBIT,bankAccount,description)));
        bankAccount.setBalance(bankAccount.getBalance()-amount);
        bankAccountRepository.save(bankAccount);




    }

    @Override
    public void credit(String accountId, double amount, String description) throws BankAccountNotFoundException  {
        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElse(null);
        if(bankAccount == null){
            throw new BankAccountNotFoundException("Bank account not found");
        }

        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setOperationDate(new Date());
        accountOperation.setAmount(amount);
        accountOperation.setType(OperationType.CREDIT);
        accountOperation.setBankAccount(bankAccount);
        accountOperation.setDescription(description);
        accountOperationRepository.save(accountOperation);

//        bankAccount.getAccountOperations().add
//                (accountOperationRepository.save(new AccountOperation(null,new Date(),amount, OperationType.DEBIT,bankAccount,description)));
        bankAccount.setBalance(bankAccount.getBalance()+amount);

        bankAccountRepository.save(bankAccount);




    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount) throws  BankAccountNotFoundException,BalanceNotSufisantException{
        debit(accountIdSource,amount,"Transfer to "+accountIdDestination);
        credit(accountIdDestination,amount,"Transfer from "+accountIdSource);

    }
    @Override
    public List<BankAccount> bankAccounts() {
        return  bankAccountRepository.findAll();
    }
}

