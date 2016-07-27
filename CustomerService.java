package com.abc.service;

import com.abc.domain.Account;
import com.abc.domain.Customer;

public interface CustomerService {

    public Customer createCustomer();
    public Customer openAccount(Account account);
    public double totalInterestEarned();
    public String getStatement();
    public String statementForAccount(Account a);
    public String toDollars(double d);
}
