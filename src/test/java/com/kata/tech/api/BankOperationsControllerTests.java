package com.kata.tech.api;


import com.kata.tech.dao.BankAccountRepository;
import com.kata.tech.dao.BankUserRepository;
import com.kata.tech.exception.BusinessException;
import com.kata.tech.model.BankAccount;
import com.kata.tech.model.BankAccountType;
import com.kata.tech.model.BankUser;
import com.kata.tech.service.BankAccountService;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankOperationsControllerTests {

    private static final String COMMA = ",";

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankUserRepository bankUserRepository;

    @Autowired
    private BankOperationsController controller;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BankAccountService bankAccountService;

    @BeforeAll
    @Transactional
    void init(){
        //MockitoAnnotations.initMocks(this);
        BankUser bankUser1 = new BankUser.BankUserBuilder().id(123L).firstName("Ali").lastName("ALM").customerIdentifier("CI123")
                .build();
        BankUser bankUser2 = new BankUser.BankUserBuilder().id(456L).firstName("Mary").lastName("ANNE").customerIdentifier("CI456")
                .build();
        BankUser bankUser3 = new BankUser.BankUserBuilder().id(789L).firstName("John").lastName("DOE").customerIdentifier("CI789")
                .build();
        BankAccount bankAccount1 = new BankAccount.BankAccountBuilder().id(1001L).accountNumber("ACC123").accountType(BankAccountType.CHEQUE).balance(2900.0)
                .build();
        BankAccount bankAccount2 = new BankAccount.BankAccountBuilder().id(1002L).accountNumber("ACC555").accountType(BankAccountType.SAVINGS).balance(5500.0)
                .build();

        bankAccount1.setOwner(bankUser1);
        bankAccount2.setOwner(bankUser1);

        bankUserRepository.save(bankUser1);
        bankUserRepository.save(bankUser2);
        bankUserRepository.save(bankUser3);

        bankAccountRepository.save(bankAccount1);
        bankAccountRepository.save(bankAccount2);
    }

    @Test
    public void contextLoads()  {
        Assertions.assertNotNull(controller);
    }

    @DisplayName("test API method of finding a bank accounts of a user - Nominal")
    @Test
    public void testFindAccountsOfBankUser()  {
        /*List<BankAccount> bankAccountsMocked = Arrays.asList(new BankAccount.BankAccountBuilder().id(1001L).accountNumber("ACC123").accountType(BankAccountType.CHEQUE).balance(2900.0).build(),
                new BankAccount.BankAccountBuilder().id(1002L).accountNumber("ACC555").accountType(BankAccountType.SAVINGS).balance(5500.0).build());
        try {
            Mockito.when(bankAccountService.findBankAcountsByUser(Mockito.any())).thenReturn(bankAccountsMocked);
        } catch (BusinessException e) {
            Assertions.fail();
        }*/

        Long inputId = 123L;
        String requestBody = "{\"id\":\"" + inputId + "\"}";

        try {
            StringBuilder expectedJSONStringBuilder = new StringBuilder("[{").append("\"id\":")
                                                      .append(1001).append(COMMA).append("\"accountType\":").append("\"CHEQUE\"").append(COMMA).append("\"accountNumber\":").append("\"ACC123\"")
                                                      .append(COMMA).append("\"balance\":").append(2900).append("}").append(COMMA).append("{")
            .append("\"id\":").append(1002).append(COMMA).append("\"accountType\":").append("\"SAVINGS\"").append(COMMA).append("\"accountNumber\":").append("\"ACC555\"")
                    .append(COMMA).append("\"balance\":").append(5500)
                    .append("}]");

            mockMvc.perform(post("/bankAccount/findByOwner")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
                    .andExpect(status().isOk())
                    .andExpect(content().json(expectedJSONStringBuilder.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }
}
