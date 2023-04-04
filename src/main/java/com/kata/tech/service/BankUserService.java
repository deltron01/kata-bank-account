package com.kata.tech.service;

import com.kata.tech.model.BankAccount;
import com.kata.tech.model.BankUser;

import java.util.List;

public interface BankUserService {

    BankUser findUserById(Long userId);
}
