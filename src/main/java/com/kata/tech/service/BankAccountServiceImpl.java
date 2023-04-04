package com.kata.tech.service;

import com.kata.tech.dao.BankAccountDao;
import com.kata.tech.exception.BusinessException;
import com.kata.tech.exception.ErrorCategory;
import com.kata.tech.exception.ErrorDTO;
import com.kata.tech.model.BankAccount;
import com.kata.tech.model.BankUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountDao bankAccountDao;


    @Override
    public BankAccount findBankAccountById(Long accountId) {
        return bankAccountDao.findBankAccountById(accountId);
    }

    @Override
    public List<BankAccount> findBankAcountsByUser(BankUser user) throws BusinessException {
        List<BankAccount> bankAccounts = bankAccountDao.findBankAcountsByUser(user);
        if(CollectionUtils.isEmpty(bankAccounts)){
            ErrorDTO errorDTO = new ErrorDTO(ErrorCategory.NOT_FOUND.getErrorNumber(), new StringBuilder(ErrorCategory.NOT_FOUND.name()).append(" the provided user does not have any Bank Account").toString());
            throw new BusinessException(errorDTO);
        }
        return bankAccounts;
    }

    @Override
    public BankAccount findBankAccountByAccountNumber(String accountNumber) {
        return bankAccountDao.findBankAccountByAccountNumber(accountNumber);
    }

    @Override
    public BankAccount depositMoneyInAccount(BankAccount account, Double moneyAmount) throws BusinessException {
        return bankAccountDao.depositMoneyInAccount(account, moneyAmount);
    }

    @Override
    public BankAccount withdrawMoneyFromAccount(BankAccount account, Double moneyAmount) throws BusinessException {
        BankAccount bankAccountFromDB = bankAccountDao.findBankAccountByAccountNumber(account.getAccountNumber());
        if(bankAccountFromDB.getBalance() < moneyAmount){
            ErrorDTO errorDTO = new ErrorDTO(ErrorCategory.REQUEST_NOT_ALLOWED.getErrorNumber(), new StringBuilder(ErrorCategory.REQUEST_NOT_ALLOWED.name()).append(" You cannont withdraw an amount greater than the current balance").toString());
            throw new BusinessException(errorDTO);
        }
        return bankAccountDao.withdrawMoneyFromAccount(account, moneyAmount);
    }
}
