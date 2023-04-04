package com.kata.tech.dao;

import com.kata.tech.exception.BusinessException;
import com.kata.tech.model.BankAccount;
import com.kata.tech.model.BankUser;

import java.util.List;

public interface BankAccountDao {

    BankAccount findBankAccountById(Long accountId);
    BankAccount findBankAccountByAccountNumber(String accountNumber);
    List<BankAccount> findBankAcountsByUser(BankUser user);
    BankAccount depositMoneyInAccount(BankAccount account, Double moneyAmount) throws BusinessException;
    BankAccount withdrawMoneyFromAccount(BankAccount account, Double moneyAmount) throws BusinessException;

}
