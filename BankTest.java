package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.abc.domain.Account;
import com.abc.domain.Bank;
import com.abc.domain.Customer;
import com.abc.service.AccountService;
import com.abc.service.impl.AccountServiceImpl;
import com.abc.service.impl.CustomerServiceImpl;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;
    private static Bank bank=null;
    
    @Before
    public void initialize(){
    	bank=new Bank();
    }
    
    @Test
    public void customerSummary() {
        CustomerServiceImpl customerServiceImpl=new CustomerServiceImpl("John");
        
        AccountServiceImpl accountServiceImpl=new AccountServiceImpl(AccountService.CHECKING);
        Account account=accountServiceImpl.createAccount();
        customerServiceImpl.openAccount(account);
        
        bank.addCustomer(customerServiceImpl.createCustomer());

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        AccountServiceImpl checkingAccountServiceImpl=new AccountServiceImpl(AccountService.CHECKING);
        Account checkingAccount=checkingAccountServiceImpl.createAccount();
    	
        CustomerServiceImpl customerServiceImpl=new CustomerServiceImpl("Bill");
        customerServiceImpl.openAccount(checkingAccount);
        bank.addCustomer(customerServiceImpl.createCustomer());
        
        checkingAccountServiceImpl.deposit(100.0);
        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
        //Bank bank = new Bank();
        AccountServiceImpl checkingAccountServiceImpl=new AccountServiceImpl(AccountService.SAVINGS);
        Account checkingAccount=checkingAccountServiceImpl.createAccount();
        
        CustomerServiceImpl customerServiceImpl=new CustomerServiceImpl("Bill");
        customerServiceImpl.openAccount(checkingAccount);

        bank.addCustomer(customerServiceImpl.createCustomer());
        
        checkingAccountServiceImpl.deposit(1500.0);
        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        AccountServiceImpl maxiAccountServiceImpl=new AccountServiceImpl(AccountService.MAXI_SAVINGS);
        Account maxiAccount=maxiAccountServiceImpl.createAccount();

        CustomerServiceImpl customerServiceImpl=new CustomerServiceImpl("Bill");
        customerServiceImpl.openAccount(maxiAccount);
        
        bank.addCustomer(customerServiceImpl.createCustomer());

        maxiAccountServiceImpl.deposit(3000.0);

        assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    
    @After
    public void print(){
    	System.out.println((bank!=null)?bank.toString():"");
    }

}