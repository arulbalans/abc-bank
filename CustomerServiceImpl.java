package com.abc.service.impl;

import static java.lang.Math.abs;

import com.abc.domain.Account;
import com.abc.domain.Customer;
import com.abc.domain.Transaction;
import com.abc.service.AccountService;
import com.abc.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	public Customer customer=null;
	
	public CustomerServiceImpl(String name) {
		this.customer=new Customer(name);
	}

	public CustomerServiceImpl(Customer customer) {
		this.customer=customer;
	}
	
	public Customer createCustomer() {
		return customer;
    }

    public Customer openAccount(Account account) {
    	customer.getListAccounts().add(account);
        return customer;
    }

    public int getNumberOfAccounts() {
        return customer.getListAccounts().size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account account : customer.getListAccounts()){
            AccountServiceImpl accountServiceImpl=new AccountServiceImpl(account);
            total += accountServiceImpl.interestEarned();
        }
        return total;
    }

    public String getStatement() {
        String statement = null;
        statement = "Statement for " + this.customer.getName() + "\n";
        double total = 0.0;
        for (Account account : customer.getListAccounts()) {
            statement += "\n" + statementForAccount(account) + "\n";
            AccountServiceImpl accountServiceImpl=new AccountServiceImpl(account);
            total += accountServiceImpl.sumTransactions();
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }

    public String statementForAccount(Account a) {
        String s = "";

       //Translate to pretty account type
        switch(a.getAccountType()){
            case AccountService.CHECKING:
                s += "Checking Account\n";
                break;
            case AccountService.SAVINGS:
                s += "Savings Account\n";
                break;
            case AccountService.MAXI_SAVINGS:
                s += "Maxi Savings Account\n";
                break;
        }

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.transactions) {
            s += "  " + (t.amount < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.amount) + "\n";
            total += t.amount;
        }
        s += "Total " + toDollars(total);
        return s;
    }

    public String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
}
