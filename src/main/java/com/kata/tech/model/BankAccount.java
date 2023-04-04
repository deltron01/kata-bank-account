package com.kata.tech.model;


import javax.persistence.*;

@Entity
public class BankAccount {

    @Id
    private Long id;
    private BankAccountType accountType;
    private String accountNumber;
    private Double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private BankUser owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BankAccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(BankAccountType accountType) {
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public BankUser getOwner() {
        return owner;
    }

    public void setOwner(BankUser owner) {
        this.owner = owner;
    }

    public BankAccount(BankAccountBuilder builder) {
        this.id = builder.getId();
        this.accountNumber = builder.getAccountNumber();
        this.accountType = builder.getAccountType();
        this.balance = builder.getBalance();
    }

    // Default constructor
    public BankAccount() {
    }

    public static class BankAccountBuilder {
        private Long id;
        private BankAccountType accountType;
        private String accountNumber;
        private Double balance;

        public BankAccountBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public BankAccountBuilder accountType(BankAccountType accountType) {
            this.accountType = accountType;
            return this;
        }

        public BankAccountBuilder accountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public BankAccountBuilder balance(Double balance) {
            this.balance = balance;
            return this;
        }

        //Builder method

        public BankAccount build(){
            return new BankAccount(this);
        }

        // Getters of the builder

        public Long getId() {
            return id;
        }

        public BankAccountType getAccountType() {
            return accountType;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public Double getBalance() {
            return balance;
        }
    }
}


