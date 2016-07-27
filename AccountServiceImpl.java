package com.abc.service.impl;

import com.abc.domain.Account;
import com.abc.domain.Transaction;
import com.abc.service.AccountService;

public class AccountServiceImpl implements AccountService {

    private final static int CHECKING = AccountService.CHECKING;
    private final static int SAVINGS = AccountService.SAVINGS;
    private final static int MAXI_SAVINGS = AccountService.MAXI_SAVINGS;

    private Account account=null;

    public AccountServiceImpl(int accountType) {
    	account=new Account(accountType);
    }
    
    public AccountServiceImpl(Account account) {
    	this.account=account;
    }

    public Account createAccount(){
    	return account;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
        	account.getTransactions().add(new Transaction(amount));
        }
    }

	public void withdraw(double amount) {
	    if (amount <= 0) {
	        throw new IllegalArgumentException("amount must be greater than zero");
	    } else {
	    	account.getTransactions().add(new Transaction(-amount));
	    }
	}

    public double interestEarned() {
        double amount = sumTransactions();
        switch(account.getAccountType()){
            case SAVINGS:
                if (amount <= 1000)
                    return amount * 0.001;
                else
                    return 1 + (amount-1000) * 0.002;
//            case SUPER_SAVINGS:
//                if (amount <= 4000)
//                    return 20;
            case MAXI_SAVINGS:
                if (amount <= 1000)
                    return amount * 0.02;
                if (amount <= 2000)
                    return 20 + (amount-1000) * 0.05;
                return 70 + (amount-2000) * 0.1;
            default:
                return amount * 0.001;
        }
    }

    public double sumTransactions() {
       return checkIfTransactionsExist(true);
    }

    private double checkIfTransactionsExist(boolean checkAll) {
        double amount = 0.0;
        for (Transaction t: account.getTransactions())
            amount += t.amount;
        return amount;
    }
}