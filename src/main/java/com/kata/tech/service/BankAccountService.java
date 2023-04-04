package com.kata.tech.service;

import com.kata.tech.exception.BusinessException;
import com.kata.tech.model.BankAccount;
import com.kata.tech.model.BankUser;

import java.util.List;

public interface BankAccountService {

    BankAccount findBankAccountById(Long accountId);
    List<BankAccount> findBankAcountsByUser(BankUser user) throws BusinessException;

    BankAccount findBankAccountByAccountNumber(String accountNumber);
    BankAccount depositMoneyInAccount(BankAccount account, Double moneyAmount) throws BusinessException;
    BankAccount withdrawMoneyFromAccount(BankAccount account, Double moneyAmount) throws BusinessException;

}
