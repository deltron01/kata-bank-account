package com.kata.tech.dao;

import com.kata.tech.model.BankAccount;
import com.kata.tech.model.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    Optional<BankAccount> findById(Long id);
    Optional<BankAccount> findByAccountNumber(String accountNumber);
    List<BankAccount> findByOwner(BankUser owner);

}
