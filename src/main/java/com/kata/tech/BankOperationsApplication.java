package com.kata.tech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.kata.tech.dao"})
public class BankOperationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankOperationsApplication.class, args);
	}

}
