package com.kata.tech.dao;


import com.kata.tech.model.BankUser;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
//@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankUserDaoTests {

    @Autowired
    private BankUserRepository bankUserRepository;

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
        bankUserRepository.save(bankUser1);
        bankUserRepository.save(bankUser2);
        bankUserRepository.save(bankUser3);

    }

    @DisplayName("test DAO method of finding a bank user by their Id - Nominal")
    @Test
    void testfindUserByIdSuccesfull(){
        BankUser bankUser = bankUserDao.findUserById(456L);
        Assertions.assertEquals(456L, bankUser.getId());

    }

    @DisplayName("test DAO method of finding a bank user by their Id - Not found")
    @Test
    void testfindUserByIdNotFoundl(){
        BankUser bankUser = bankUserDao.findUserById(500L);
        Assertions.assertNull(bankUser);
    }


}
