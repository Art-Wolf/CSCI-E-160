package cscie160.hw4;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit Test of the Account class.
 * 
 * @author John Doyle
 *
 */
public class AccountTest {

    @Test
    /**
     * Test that the default constructor sets
     * the initial balance to zero.
     *
     */
    public void defaultConstructor() {
        Account testAccount = new Account();
        assertEquals(0, testAccount.getBalance(),0);
    }
    
    @Test
    /**
     * Test that the constructor correctly sets
     * the initial balance when passed an amount.
     *
     */
    public void initialBalanceConstructor() {
        float initialBalance = 100;
        Account testAccount = new Account(initialBalance);
        assertEquals(initialBalance, testAccount.getBalance(),0);
    }
    
    @Test
    /**
     * Test that the setter correctly sets the
     * new balance.
     *
     */
    public void setBalance() {
        Account testAccount = new Account();
        float initialBalance = 100;
        testAccount.setBalance(initialBalance);
        assertEquals(initialBalance, testAccount.getBalance(),0);
    }

}
