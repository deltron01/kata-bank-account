package com.kata.tech.dao;

import com.kata.tech.model.BankAccount;
import com.kata.tech.model.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BankUserRepository extends JpaRepository<BankUser, Long> {

    Optional<BankUser> findById(Long id);

}
