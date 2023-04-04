package com.kata.tech.service;

import com.kata.tech.dao.BankUserDao;
import com.kata.tech.model.BankUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankUserServiceImpl implements BankUserService {

    @Autowired
    private BankUserDao bankUserDao;

    @Override
    public BankUser findUserById(Long userId) {
        return bankUserDao.findUserById(userId);
    }
}
