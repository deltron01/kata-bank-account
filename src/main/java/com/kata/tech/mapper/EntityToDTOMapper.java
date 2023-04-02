package com.kata.tech.mapper;

import com.kata.tech.model.BankAccount;
import com.kata.tech.model.BankUser;
import com.kata.tech.openapi.BankAccountDTO;
import com.kata.tech.openapi.BankUserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface EntityToDTOMapper {

    BankAccountDTO bankAccountEntityToBankAccount(BankAccount bankAccount);
    BankUserDTO bankUserEntityToBankUser(BankUser bankUser);
}
