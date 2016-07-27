package com.abc.domain;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }
    
    public List<Account> getListAccounts() {
        return accounts;
    }
    public int getNumberOfAccounts() {
        return accounts.size();
    }
    
    @Override
    public String toString() {
        return String.format(" %s , %s ", getName(), getListAccounts().size());
    }
    
}
