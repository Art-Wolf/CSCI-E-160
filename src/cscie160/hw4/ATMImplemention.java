package cscie160.hw4;

/**
 * Class that implements the ATM interface to the single account
 * that the Server controls.
 *
 * @author John Doyle
 *
 */
public class ATMImplemention implements ATM {

    /**
     * The single account accessible via the ATM.
     */
    private Account account;

    /**
     * Default constructor that generates a new Account.
     */
    public ATMImplemention() {
        this(new Account());
    }

    /**
     * Constructor that sets the account.
     *
     * @param account The client's account.
     */
    public ATMImplemention(Account account) {
       this.account = account;
    }

    @Override
    /**
     * Deposit money into the account.
     */
    public final void deposit(float amount) throws ATMException {

        /**
         * If this is a negative amount, i.e. a withdrawal, 
         * throw an exception.
         */
        if (amount < 0) {
            throw new ATMException("Negative Deposit.");
        }

        /**
         * Set the new balance as the current balance and the new amount.
         */
        account.setBalance(account.getBalance() + amount);
    }

    @Override
    /**
     * Withdraw money from the account.
     */
    public final void withdraw(float amount) throws ATMException {

        /**
         * Get the current balance of the account.
         */
        float currentBalance = account.getBalance();

        /**
         * If the amount requested is greater than the balance,
         * throw an exception.
         */
        if (currentBalance < amount) {
            throw new ATMException("Insufficent funds.");
        }

        /**
         * Set the new balance as the current balance minus the new amount.
         */
        account.setBalance(currentBalance - amount);
    }

    @Override
    /**
     * Return the current balance of the account.
     */
    public final Float getBalance() throws ATMException {
        return account.getBalance();
    }
}
