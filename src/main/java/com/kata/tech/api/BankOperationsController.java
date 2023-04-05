package com.kata.tech.api;


import com.kata.tech.openapi.BankAccountDepositDTO;
import com.kata.tech.openapi.BankAccountWithdrawDTO;
import com.kata.tech.openapi.BankUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

/*
handles the Bank Operations requests from external system clients

 */
public interface BankOperationsController {


    ResponseEntity<Object> findBankAccountByOwner(@RequestBody BankUserDTO bankUserDTO);
    ResponseEntity<Object> depositMoneyInBankAccount(@RequestBody BankAccountDepositDTO bankAccountDepositDTO);
    ResponseEntity<Object> withdrawMoneyInBankAccount(@RequestBody BankAccountWithdrawDTO bankAccountWithdrawDTO);

}
