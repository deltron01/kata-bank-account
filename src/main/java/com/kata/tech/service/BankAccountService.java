package com.kata.tech.service;

import com.kata.tech.model.BankAccount;

public interface BankAccountService {

    BankAccount findBankAccountById(Long accountId);
    BankAccount findBankAccountByAccountNumber(String accountNumber);
    Double depositMoneyInAccount(BankAccount account, Double moneyAmount);
    Double withdrawMoneyInAccount(BankAccount account, Double moneyAmount);

}
