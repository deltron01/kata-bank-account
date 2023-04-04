package com.kata.tech.dao;

import com.kata.tech.model.BankAccount;
import com.kata.tech.model.BankUser;

import java.util.List;

public interface BankUserDao {

    BankUser findUserById(Long userId);

}
