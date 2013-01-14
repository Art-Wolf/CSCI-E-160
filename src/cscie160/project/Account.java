package cscie160.project;

import java.io.Serializable;

/**
 * Personal Account object that tracks a person's bank balance.
 * 
 * @author John Doyle
 *
 */
public class Account implements Serializable {

    /**
     * Serializable object passed between the ATM and Bank systems.
     */
    private static final long serialVersionUID = 2403218795690363277L;

    /**
     * Track the account balance.
     */
    private float balance;
    
    /**
     * Account Identifier
     */
    private int accountNumber;
    
    /**
     * Default constructor - if this is called then the other constructor is called with the balance of zero.
     */
    public Account(int accountNumber) {
        this(accountNumber, 0);
    }
    
    /**
     * Constructor that sets the opening balance.
     * 
     * @param balance The opening balance of the account.
     */
    public Account(int accountNumber, float balance) {
        this.setBalance(balance);
        this.accountNumber = accountNumber;
    }

    /**
     * @return the balance
     */
    public float getBalance() {
        return balance;
    }
    
    /**
     * @return the Account number
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(float balance) {
        this.balance = balance;
    }
}
