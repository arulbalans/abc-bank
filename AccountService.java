package com.abc.service;

import com.abc.domain.Account;


public interface AccountService {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    public Account createAccount();
    
    public void deposit(double amount);

    public void withdraw(double amount);

    public double interestEarned();

    public double sumTransactions();

}
