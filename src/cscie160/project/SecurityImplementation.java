package cscie160.project;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Class that implements the ATM interface to the single account that the Server
 * controls.
 * 
 * @author John Doyle
 * 
 */
@SuppressWarnings("serial")
public class SecurityImplementation extends UnicastRemoteObject implements Security {

    /**
     * The Pins associated with accounts.
     */
    private ArrayList<AccountInfo> authenticationList = new ArrayList<AccountInfo>();

    /**
     * The individual actions. Each action has its seperate list, which contains the accounts
     * permitted to perform that action.
     */
    private ArrayList<Account> depositList = new ArrayList<Account>();
    private ArrayList<Account> withdrawList = new ArrayList<Account>();
    private ArrayList<Account> balanceList = new ArrayList<Account>();

    /**
     * Default constructor with no existing accounts.
     * 
     * @throws RemoteException
     */
    public SecurityImplementation() throws RemoteException {
        /**
         * Required for the UnicastRemoteObject
         */
        super();

        /**
         * Account 1 pin and actions.
         */
        this.authenticationList.add(new AccountInfo(0000001, 1234));
        this.depositList.add(new Account(0000001));
        this.withdrawList.add(new Account(0000001));
        this.balanceList.add(new Account(0000001));
        
        /**
         * Account 2 pin and actions.
         */
        this.authenticationList.add(new AccountInfo(0000002, 2345));
        this.depositList.add(new Account(0000002));
        this.balanceList.add(new Account(0000002));
        
        /**
         * Account 3 pin and actions.
         */
        this.authenticationList.add(new AccountInfo(0000003, 3456));
        this.withdrawList.add(new Account(0000003));
        this.balanceList.add(new Account(0000003));
    }

    /**
     * Confirm that the account pin provided matches the security system pin.
     * 
     * @param accountInfo The acocunt number and pin code.
     * @return True if the pin codes match.
     * @throws RemoteException If there is an exception with the connection.
     */
    public boolean authenticated(AccountInfo accountInfo)
            throws RemoteException {

        /**
         * Loop over each account.
         */
        for (AccountInfo accountCheck : authenticationList) {
            /**
             * Find the account provided.
             */
            if (accountCheck.getAccountNumber() == accountInfo.getAccountNumber()) {
                /**
                 * Check if the pins match.
                 */
                return accountInfo.checkPin(accountCheck.getPin());
            }
        }

        /**
         * If the account if not found simply return false. Do not explain why
         * the system rejected the account/pin.
         */
        return false;
    }

    /**
     * Confirm that the account is permitted to execute the desired command.
     * 
     * @param accountInfo The account number and pin code.
     * @param command The command the account wants to execute.
     * @return True if the account if permitted.
     * @throws RemoteException If there is an exception with the connection.
     */
    public boolean authorized(AccountInfo accountInfo, Commands command)
            throws RemoteException {
        
        /**
         * Pass the command into a Switch statement.
         */
        switch (command.ordinal()) {
        /**
         * For a Deposit
         */
        case 0:
            /**
             * Loop over the deposit accounts list
             */
            for (Account account : depositList) {
                /**
                 * Check if the account is in the list.
                 */
                if (account.getAccountNumber() == accountInfo.getAccountNumber()) {
                    return true;
                }
            }
            break;
        /**
         * For a Withdrawal
         */
        case 1:
            /**
             * Loop over the withdrawl accounts list.
             */
            for (Account account : withdrawList) {
                /**
                 * Check if the account is in the list.
                 */
                if (account.getAccountNumber() == accountInfo.getAccountNumber()) {
                    return true;
                }
            }
            break;
        /**
         * For a Balance Check
         */
        case 2:
            /**
             * Loop over the balance accounts list
             */
            for (Account account : balanceList) {
                /**
                 * Check if the account is in the list.
                 */
                if (account.getAccountNumber() == accountInfo.getAccountNumber()) {
                    return true;
                }
            }
            break;
        /**
         * Default, return false.
         */
        default:
            return false;
        }

        /**
         * Return false if the account does not have the required permissions.
         */
        return false;
    }
}
