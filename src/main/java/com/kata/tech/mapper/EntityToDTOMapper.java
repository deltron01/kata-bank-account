package com.kata.tech.mapper;

import com.kata.tech.model.BankAccount;
import com.kata.tech.model.BankUser;
import com.kata.tech.openapi.BankAccountDTO;
import com.kata.tech.openapi.BankAccountDepositDTO;
import com.kata.tech.openapi.BankAccountWithdrawDTO;
import com.kata.tech.openapi.BankUserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityToDTOMapper {

    BankAccountDTO bankAccountEntityToBankAccountDTO(BankAccount bankAccount);
    BankUserDTO bankUserEntityToBankUserDTO(BankUser bankUser);

    BankAccount bankAccountDTOToBankAccountEntity(BankAccountDTO bankAccountDTO);
    BankUser bankUserDTOToBankUserEntity(BankUserDTO BankUserDTO);

    BankAccount bankAccountDepositDTOToBankAccountEntity(BankAccountDepositDTO bankAccountDepositDTO);
    BankAccount bankAccountWithdrawalDTOToBankAccountEntity(BankAccountWithdrawDTO bankAccountDepositDTO);


}
