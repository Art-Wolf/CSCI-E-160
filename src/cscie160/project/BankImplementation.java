package cscie160.project;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


/**
 * Class that implements the ATM interface to the single account
 * that the Server controls.
 *
 * @author John Doyle
 *
 */
@SuppressWarnings("serial")
public class BankImplementation extends UnicastRemoteObject implements Bank {

    /**
     * The single account accessible via the ATM.
     */
    private ArrayList<Account> accountList =
            new ArrayList<Account>();
    
    /**
     * Default constructor with no existing accounts.
     * @throws RemoteException 
     */
    public BankImplementation() throws RemoteException {
        /**
         * Required for UnicateRemoteObject
         */
        super();
        
        /**
         * Set the default account balances.
         */
        this.accountList.add(new Account(0000001, 0));
        this.accountList.add(new Account(0000002, 100));
        this.accountList.add(new Account(0000003, 500));
    }
    
    /**
     * The Bank system will return the Account object that matches the AccountInfo
     * properties.
     * 
     * @param accountInfo The account number and pin code.
     * @return The Account object.
     * @throws RemoteException If there is an issue with the connection.
     */
    public Account getAccount(AccountInfo accountInfo) throws RemoteException
    {
        /**
         * Loop over the list of accounts.
         */
        for (Account account : accountList) {
            /**
             * Find the account that matches the account number passed in.
             */
            if (account.getAccountNumber() == accountInfo.getAccountNumber()) {
                /**
                 * Return the account object.
                 */
                return account;
            }
        }
        return null;
    }

    /**
     * If the ATM makes a change to the Account this change must be passed back to the
     * Bank.
     * 
     * @param accountInfo The account number and pin code.
     * @param account The Account object that has been changed.
     * @throws RemoteException If there is an issue with the connection.
     */
    public void setAccount(AccountInfo accountInfo, Account account) throws RemoteException
    {
        /**
         * Security check - make sure the AccountInfo and Account match.
         */
        if (accountInfo.getAccountNumber() == account.getAccountNumber()) {
            /**
             * Counter to find where in the list the account currently resides.
             */
            int counter = 0;
            
            /**
             * Loop over the account list.
             */
            for (Account accountCount : accountList) {
                /**
                 * Break out of the loop when you match the account.
                 */
                if (accountCount.getAccountNumber() == account.getAccountNumber()) {
                    break;
                }
                /**
                 * Otherwise increment the counter.
                 */
                counter++;
            }
            
            /**
             * Make sure the counter is within bounds.
             */
            if (counter <= accountList.size()) {
                /**
                 * Remove the old account object.
                 */
                accountList.remove(counter);
                /**
                 * Add the new account object.
                 */
                accountList.add(account);
            } 
        }
    }
}
