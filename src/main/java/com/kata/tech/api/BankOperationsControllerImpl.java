package com.kata.tech.api;

import com.kata.tech.exception.BusinessException;
import com.kata.tech.mapper.EntityToDTOMapper;
import com.kata.tech.model.BankAccount;
import com.kata.tech.model.BankUser;
import com.kata.tech.openapi.BankAccountDTO;
import com.kata.tech.openapi.BankAccountDepositDTO;
import com.kata.tech.openapi.BankAccountWithdrawDTO;
import com.kata.tech.openapi.BankUserDTO;
import com.kata.tech.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bankAccount")
public class BankOperationsControllerImpl implements BankOperationsController {

    @Autowired
    private EntityToDTOMapper entityToDTOMapper;

    @Autowired
    private BankAccountService bankAccountService;

    @Override
    @RequestMapping(path = "/findByOwner", method = RequestMethod.POST)
    public ResponseEntity<Object> findBankAccountByOwner(BankUserDTO bankUserDTO) {

        BankUser bankUserEntity = entityToDTOMapper.bankUserDTOToBankUserEntity(bankUserDTO);
        try {
            List<BankAccount> bankAcounts = bankAccountService.findBankAcountsByUser(bankUserEntity);
            List<BankAccountDTO> listOfAccountsToReturn = new ArrayList<>();
            for (BankAccount account: bankAcounts){
                listOfAccountsToReturn.add(entityToDTOMapper.bankAccountEntityToBankAccountDTO(account));
            }
            return ResponseEntity.ok().body(listOfAccountsToReturn);
        } catch (BusinessException be) {
            return ResponseEntity.badRequest().body(be.getErrorDTO().getErrorMessage());
        }
    }

    @Override
    @RequestMapping(path = "/deposit", method = RequestMethod.PUT)
    public ResponseEntity<Object> depositMoneyInBankAccount(BankAccountDepositDTO bankAccountDepositDTO) {
        return null;
    }

    @Override
    @RequestMapping(path = "/withdraw", method = RequestMethod.PUT)
    public ResponseEntity<Object> withdrawMoneyInBankAccount(BankAccountWithdrawDTO bankAccountWithdrawDTO) {
        return null;
    }
}
