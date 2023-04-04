package com.kata.tech.dao;

import com.kata.tech.exception.BusinessException;
import com.kata.tech.exception.ErrorCategory;
import com.kata.tech.exception.ErrorDTO;
import com.kata.tech.model.BankAccount;
import com.kata.tech.model.BankUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BankAccountDaoImpl implements BankAccountDao {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
    public BankAccount findBankAccountById(Long accountId) {
        Optional<BankAccount> bankAccountOptional = bankAccountRepository.findById(accountId);
        return bankAccountOptional.isPresent() ? bankAccountOptional.get() : null;
    }

    @Override
    public BankAccount findBankAccountByAccountNumber(String accountNumber) {
        Optional<BankAccount> bankAccountOptional = bankAccountRepository.findByAccountNumber(accountNumber);
        return bankAccountOptional.isPresent() ? bankAccountOptional.get() : null;
    }

    @Override
    public List<BankAccount> findBankAcountsByUser(BankUser user) {
        return bankAccountRepository.findByOwner(user);
    }

    @Override
    public BankAccount depositMoneyInAccount(BankAccount account, Double moneyAmount) throws BusinessException {
        Optional<BankAccount> bankAccountOptional = bankAccountRepository.findByAccountNumber(account.getAccountNumber());
        if(!bankAccountOptional.isPresent()){
            ErrorDTO errorDTO = new ErrorDTO(ErrorCategory.NOT_FOUND.getErrorNumber(), new StringBuilder(ErrorCategory.NOT_FOUND.name()).append(" no BankAccount corresponds to the given accountNumber").toString());
            throw new BusinessException(errorDTO);
        }
        BankAccount bankAccount = bankAccountOptional.get();
        bankAccount.setBalance(bankAccount.getBalance() + moneyAmount);
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public BankAccount withdrawMoneyFromAccount(BankAccount account, Double moneyAmount) throws BusinessException {
        Optional<BankAccount> bankAccountOptional = bankAccountRepository.findByAccountNumber(account.getAccountNumber());
        if(!bankAccountOptional.isPresent()){
            ErrorDTO errorDTO = new ErrorDTO(ErrorCategory.NOT_FOUND.getErrorNumber(), new StringBuilder(ErrorCategory.NOT_FOUND.name()).append(" no BankAccount corresponds to the given accountNumber").toString());
            throw new BusinessException(errorDTO);
        }
        BankAccount bankAccount = bankAccountOptional.get();
        bankAccount.setBalance(bankAccount.getBalance() - moneyAmount);
        return bankAccountRepository.save(bankAccount);
    }
}
