package cscie160.hw4;

/**
 * Personal Account object that tracks a person's bank balance.
 * 
 * @author John Doyle
 *
 */
public class Account {

    /**
     * Track the account balance.
     */
    private float balance;
    
    /**
     * Default constructor - if this is called then the other constructor is called with the balance of zero.
     */
    public Account() {
        this(0);
    }
    
    /**
     * Constructor that sets the opening balance.
     * 
     * @param balance The opening balance of the account.
     */
    public Account(float balance) {
        this.setBalance(balance);
    }

    /**
     * @return the balance
     */
    public float getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(float balance) {
        this.balance = balance;
    }
}
