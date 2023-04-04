package com.kata.tech.model;



import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class BankUser {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String customerIdentifier;

    @OneToMany(fetch = FetchType.LAZY)
    private List<BankAccount> bankAccounts;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCustomerIdentifier() {
        return customerIdentifier;
    }

    public void setCustomerIdentifier(String customerIdentifier) {
        this.customerIdentifier = customerIdentifier;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    // Default constructor
    public BankUser() {
    }

    public BankUser(BankUserBuilder builder) {
        this.id = builder.getId();
        this.firstName = builder.getFirstName();
        this.lastName = builder.getLastName();
        this.customerIdentifier = builder.getCustomerIdentifier();
    }

    public static class BankUserBuilder {
        private Long id;
        private String firstName;
        private String lastName;
        private String customerIdentifier;

        public BankUserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public BankUserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public BankUserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public BankUserBuilder customerIdentifier(String customerIdentifier) {
            this.customerIdentifier = customerIdentifier;
            return this;
        }
        // Builder method
        public BankUser build(){
            return  new BankUser(this);
        }
        // Getters of the builder class

        public Long getId() {
            return id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getCustomerIdentifier() {
            return customerIdentifier;
        }
    }
}
