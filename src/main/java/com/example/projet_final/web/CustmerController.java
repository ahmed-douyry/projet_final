package com.example.projet_final.web;

import com.example.projet_final.dtos.CustmerDto;
import com.example.projet_final.entities.Custmer;
import com.example.projet_final.exceptions.CustomerNotFoundException;
import com.example.projet_final.services.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/custmers")
@AllArgsConstructor
@Slf4j
public class CustmerController {
    private BankAccountService bankAccountService;
    @GetMapping
    public List<CustmerDto> custmers(){
        return bankAccountService.listCustmers();
    }
    @GetMapping("/{id}")
    public CustmerDto getcusCustmer(@PathVariable(name = "id") Long id) throws CustomerNotFoundException {
        return  bankAccountService.getCustmer(id);
    }
    @PostMapping
    public CustmerDto saveCustomer(@RequestBody  CustmerDto custmerDto){
        bankAccountService.saveCustmer(custmerDto);
        return custmerDto;
    }
    @PutMapping("/{id}")
    public CustmerDto updateCustmer(@PathVariable(name = "id") Long id ,@RequestBody CustmerDto custmerDto){
        custmerDto.setId(id);
        return bankAccountService.updateCustmer(custmerDto);

    }
    @DeleteMapping("/{id}")
    public void deleteCustmer(@PathVariable(name = "id") Long id) {
        bankAccountService.deleteCustmer(id);
    }

}
