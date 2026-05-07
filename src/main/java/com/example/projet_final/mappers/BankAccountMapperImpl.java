package com.example.projet_final.mappers;

import com.example.projet_final.dtos.CurrentAccountDto;
import com.example.projet_final.dtos.CustmerDto;
import com.example.projet_final.dtos.SavingAccountDto;
import com.example.projet_final.entities.CurrentAccount;
import com.example.projet_final.entities.Custmer;
import com.example.projet_final.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapperImpl {
    public CustmerDto fromcustomer(Custmer custmer){
        CustmerDto  custmerDto = new CustmerDto();
        BeanUtils.copyProperties(custmer,custmerDto);
        return  custmerDto;

    }
    public Custmer fromcustomerDto(CustmerDto custmerDto){
        Custmer custmer = new Custmer();
        BeanUtils.copyProperties(custmerDto,custmer);
        return  custmer;
    }

    public CurrentAccountDto fromCurrentAccount(CurrentAccount currentAccount){
        CurrentAccountDto currentAccountDto = new CurrentAccountDto();
        BeanUtils.copyProperties(currentAccount,currentAccountDto);
        currentAccountDto.setCustmerDto(fromcustomer(currentAccount.getCustmer()));
        return currentAccountDto;

    }
    public  CurrentAccount fromCurrentAccountDto(CurrentAccountDto currentAccountDto){
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentAccountDto,currentAccount);
        currentAccount.setCustmer(fromcustomerDto(currentAccountDto.getCustmerDto()));
        return  currentAccount;
    }
 public SavingAccountDto fromSavingAccount(SavingAccount savingAccount){
        SavingAccountDto savingAccountDto = new SavingAccountDto();
        BeanUtils.copyProperties(savingAccount,savingAccountDto);
        savingAccountDto.setCustmerDto(fromcustomer(savingAccount.getCustmer()));
        return  savingAccountDto;
 }
 public SavingAccount fromSavingAccountDto(SavingAccountDto savingAccountDto){
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingAccountDto,savingAccount);
        savingAccount.setCustmer(fromcustomerDto(savingAccountDto.getCustmerDto()));
        return  savingAccount;
 }
}
