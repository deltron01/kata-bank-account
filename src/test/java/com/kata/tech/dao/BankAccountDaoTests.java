package com.kata.tech.dao;


import com.kata.tech.exception.BusinessExcpetion;
import com.kata.tech.model.BankAccount;
import com.kata.tech.model.BankAccountType;
import com.kata.tech.model.BankUser;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankAccountDaoTests {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankUserRepository bankUserRepository;

    @Autowired
    private BankAccountDao bankAccountDao;

    @Autowired
    private BankUserDao bankUserDao;



    @BeforeAll
    @Transactional
    void init() {
        BankUser bankUser1 = new BankUser.BankUserBuilder().id(123L).firstName("Ali").lastName("ALM").customerIdentifier("CI123")
                .build();
        BankUser bankUser2 = new BankUser.BankUserBuilder().id(456L).firstName("Mary").lastName("ANNE").customerIdentifier("CI456")
                .build();
        BankUser bankUser3 = new BankUser.BankUserBuilder().id(789L).firstName("John").lastName("DOE").customerIdentifier("CI789")
                .build();
        BankAccount bankAccount1 = new BankAccount.BankAccountBuilder().id(1001L).accountNumber("AC100XYZ").accountType(BankAccountType.CHEQUE).balance(5000.0)
                .build();
        BankAccount bankAccount2 = new BankAccount.BankAccountBuilder().id(1002L).accountNumber("AC200XYZ").accountType(BankAccountType.SAVINGS).balance(8500.0)
                .build();

        bankAccount1.setOwner(bankUser1);
        bankAccount2.setOwner(bankUser2);

        bankUserRepository.save(bankUser1);
        bankUserRepository.save(bankUser2);
        bankUserRepository.save(bankUser3);

        bankAccountRepository.save(bankAccount1);
        bankAccountRepository.save(bankAccount2);
    }

    @DisplayName("test DAO method of finding a bank Account by its Id - Nominal")
    @Test
    void testFindBankAccountByIdSuccessful(){

        BankAccount bankAccount = bankAccountDao.findBankAccountById(1002L);
        Assertions.assertEquals(1002L, bankAccount.getId());
    }
    @DisplayName("test DAO method of finding a bank Account by its Id - Not Found")
    @Test
    void testFindBankAccountByIdNotFound(){

        BankAccount bankAccount = bankAccountDao.findBankAccountById(4000L);
        Assertions.assertNull(bankAccount);
    }
    @DisplayName("test DAO method of finding a bank Account by its accountNumber - Nominal")
    @Test
    void testFindBankAccountByAccountNumberSuccessful(){
        BankAccount bankAccount = bankAccountDao.findBankAccountByAccountNumber("AC200XYZ");
        Assertions.assertEquals(1002L, bankAccount.getId());
    }
    @DisplayName("test DAO method of finding a bank Account by its accountNumber - Not Found")
    @Test
    void testFindBankAccountByAccountNumberNotFound(){
        BankAccount bankAccount = bankAccountDao.findBankAccountByAccountNumber("unknown");
        Assertions.assertNull(bankAccount);
    }

    @DisplayName("test DAO method of finding a bank Account by its owner - Nominal")
    @Test
    void testFindBankAcountsByUserSuccessful(){

        // retrieve existing bankUser that has bank account
        BankUser bankUser = bankUserDao.findUserById(456L);
        List<BankAccount> bankAccounts = bankAccountDao.findBankAcountsByUser(bankUser);
        Assertions.assertTrue(!CollectionUtils.isEmpty(bankAccounts));

    }

    @DisplayName("test DAO method of finding a bank Account by its owner - Not Found")
    @Test
    void testFindBankAcountsByUserNotFound(){
        // retrieve existing bankUser that dos not have any bank account
        BankUser bankUser = bankUserDao.findUserById(789L);
        List<BankAccount> bankAccounts = bankAccountDao.findBankAcountsByUser(bankUser);
        Assertions.assertTrue(CollectionUtils.isEmpty(bankAccounts));
    }

    @DisplayName("test DAO method of deposit of money in bank Account")
    @Test
    @Transactional
    void testDepositMoneyInAccount(){
        Double moneyAmount = 2500.0;
        BankAccount bankAccount = bankAccountDao.findBankAccountByAccountNumber("AC100XYZ");
        Double balanceBeforeDeposit = bankAccount.getBalance();
        try {

            BankAccount bankAccountAfterDeposit = bankAccountDao.depositMoneyInAccount(bankAccount, moneyAmount);
            Assertions.assertEquals(balanceBeforeDeposit + moneyAmount, bankAccountAfterDeposit.getBalance());

        } catch (BusinessExcpetion businessExcpetion) {
            Assertions.fail();
        }

    }


    @DisplayName("test DAO method of withdrawal of money from bank Account")
    @Test
    @Transactional
    void testWithdrawMoneyInAccountSuccessful(){
        Double moneyAmount = 1700.0;
        BankAccount bankAccount = bankAccountDao.findBankAccountByAccountNumber("AC200XYZ");
        Double balanceBeforeWithdrawal = bankAccount.getBalance();
        try {
            BankAccount bankAccountAfterWithdrawal = bankAccountDao.withdrawMoneyFromAccount(bankAccount, moneyAmount);
            Assertions.assertEquals(balanceBeforeWithdrawal - moneyAmount, bankAccountAfterWithdrawal.getBalance());

        } catch (BusinessExcpetion businessExcpetion) {
            Assertions.fail();
        }
    }



    void testWithdrawMoneyInAccountInsufficientFunds(){

    }

}
