package cscie160.project;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Pass back to the listeners a notifictaion of the transaction the ATM is currently
 * processing.
 *
 * @author johndoyle
 *
 */
public class TransactionNotification implements Serializable {

    /**
     * Serializable object passed between the ATM and Bank systems.
     */
    private static final long serialVersionUID = -6967278354890398515L;
    
    /**
     * The command being performed.
     */
    private final Commands command;
    
    /**
     * The account performing the transaction
     */
    private final AccountInfo accountInfo;
    
    /**
     * The timestamp of the transaction
     */
    private final Timestamp timestamp;
    
    /**
     * Default constructor that requires the account information and the transaction command.
     *
     * @param accountInfo The account number and pin code.
     * @param command The transaction command.
     */
    public TransactionNotification(AccountInfo accountInfo, Commands command)
    {
        /**
         * Set the command.
         */
        this.command = command;
        
        /**
         * Set the account.
         */
        this.accountInfo = accountInfo;
        
        /**
         * Get the timestamp.
         */
        Date date = new Date();
        timestamp = new Timestamp(date.getTime());
    }
    
    /**
     * For the handler to convert this to notification to a String.
     */
    public String toString()
    {
        /**
         * Set the format as [timestamp] account number : command
         */
        String transaction = "[" + timestamp + "] " + accountInfo.getAccountNumber() + " : " + command.name(); 
        
        /**
         * Return the string.
         */
        return transaction;
    }
}