package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.abc.domain.Account;
import com.abc.service.AccountService;
import com.abc.service.impl.AccountServiceImpl;
import com.abc.service.impl.CustomerServiceImpl;

public class CustomerTest {

	Account savingsAccount=null;
	Account checkingAccount= null;
	Account maxiSavingsAccount= null;
	CustomerServiceImpl customerServiceImpl=null;
	
    @Before
    public void initialize(){
        AccountServiceImpl savingsAccountServiceImpl=new AccountServiceImpl(AccountService.SAVINGS);
        savingsAccount=savingsAccountServiceImpl.createAccount();

        AccountServiceImpl checkingAccountServiceImpl=new AccountServiceImpl(AccountService.CHECKING);
        checkingAccount=checkingAccountServiceImpl.createAccount();

        AccountServiceImpl maxiSavingsServiceImpl=new AccountServiceImpl(AccountService.MAXI_SAVINGS);
        maxiSavingsAccount=maxiSavingsServiceImpl.createAccount();
    }

    @Test //Test customer statement generation
    public void testApp(){

        //create checking account
    	AccountServiceImpl checkingAccountServiceImpl=new AccountServiceImpl(AccountService.CHECKING);
        Account checkingAccount=checkingAccountServiceImpl.createAccount();
        
        AccountServiceImpl savingsAccountServiceImpl=new AccountServiceImpl(AccountService.SAVINGS);
        Account savingsAccount=savingsAccountServiceImpl.createAccount();
        
        //create customer
        customerServiceImpl=new CustomerServiceImpl("Henry");
        customerServiceImpl.openAccount(checkingAccount);
        customerServiceImpl.openAccount(savingsAccount);

        checkingAccountServiceImpl.deposit(100.0);
        savingsAccountServiceImpl.deposit(4000.0);
        savingsAccountServiceImpl.withdraw(200.0);

        assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00", customerServiceImpl.getStatement());
    }

    @Test
    public void testOneAccount(){
        //create customer
        customerServiceImpl=new CustomerServiceImpl("Oscar");
        customerServiceImpl.openAccount(this.savingsAccount);
        //Check
        assertEquals(1, customerServiceImpl.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
    	
        //create customer
        customerServiceImpl=new CustomerServiceImpl("Oscar");
        customerServiceImpl.openAccount(this.savingsAccount);
        customerServiceImpl.openAccount(this.checkingAccount);

        //Check
        assertEquals(2, customerServiceImpl.getNumberOfAccounts());
    }

    @Ignore
    public void testThreeAcounts() {
        
        //create customer
        customerServiceImpl=new CustomerServiceImpl("Oscar");
        customerServiceImpl.openAccount(this.savingsAccount);
        customerServiceImpl.openAccount(this.checkingAccount);
        customerServiceImpl.openAccount(this.maxiSavingsAccount);

        //Check
        assertEquals(3, customerServiceImpl.getNumberOfAccounts());
    }
    
    @After
    public void print(){
    	System.out.println((customerServiceImpl!=null)?customerServiceImpl.createCustomer().toString():"");
    }

}
