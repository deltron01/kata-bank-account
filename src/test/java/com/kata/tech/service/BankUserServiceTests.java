package com.kata.tech.service;


import com.kata.tech.dao.BankUserRepository;
import com.kata.tech.model.BankUser;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankUserServiceTests {


    @Autowired
    BankUserService bankUserService;

    @Autowired
    private BankUserRepository bankUserRepository;

    @BeforeAll
    @Transactional
    void init() {
        BankUser bankUser1 = new BankUser.BankUserBuilder().id(123L).firstName("Ali").lastName("ALM").customerIdentifier("CI123")
                .build();
        BankUser bankUser2 = new BankUser.BankUserBuilder().id(456L).firstName("Mary").lastName("ANNE").customerIdentifier("CI456")
                .build();
        BankUser bankUser3 = new BankUser.BankUserBuilder().id(789L).firstName("John").lastName("DOE").customerIdentifier("CI789")
                .build();
        bankUserRepository.save(bankUser1);
        bankUserRepository.save(bankUser2);
        bankUserRepository.save(bankUser3);

    }

    @DisplayName("test Service method of finding a bank user by their Id - Nominal")
    @Test
    void testFindUserById(){
        BankUser bankUser = bankUserService.findUserById(456L);
        Assertions.assertNotNull(bankUser);
        Assertions.assertEquals(456L, bankUser.getId());
    }

    @DisplayName("test Service method of finding a bank user by their Id - Not found")
    @Test
    void findBankAcountsByUser(){
        BankUser bankUser = bankUserService.findUserById(400L);
        Assertions.assertNull(bankUser);
    }
}
