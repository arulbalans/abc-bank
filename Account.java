package com.abc.domain;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private final int accountType;
    public List<Transaction> transactions;

    public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }
    public int getAccountType() {
        return accountType;
    }
    public List<Transaction> getTransactions() {
        return transactions;
    }
    
}
