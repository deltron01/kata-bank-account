package com.kata.tech.dao;

import com.kata.tech.model.BankAccount;

public interface BankAccountDao {

    BankAccount findBankAccountById(Long accountId);
    BankAccount findBankAccountByAccountNumber(String accountNumber);
    Double depositMoneyInAccount(BankAccount account, Double moneyAmount);
    Double withdrawMoneyInAccount(BankAccount account, Double moneyAmount);


}
