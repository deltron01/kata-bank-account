package com.kata.tech.dao;

import com.kata.tech.model.BankUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BankUserDaoImpl implements BankUserDao {

    @Autowired
    private BankUserRepository bankUserRepository;

    @Override
    public BankUser findUserById(Long userId) {
        Optional<BankUser> bankUserOptional = bankUserRepository.findById(userId);
        return bankUserOptional.isPresent() ? bankUserOptional.get() : null;
    }
}
