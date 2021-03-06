package cscie160.project;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The interface into a Remote ATM.
 *
 * @author johndoyle
 */
public interface ATM  extends Remote
{
    /**
     * Deposit money into an account.
     *
     * @param AccountInfo The individual account targeted with their pin code.
     * @param amount The amount of money being deposited.
     * @throws ATMException An exception caused during the transaction.
     * @throws RemoteException An exception caused by the Remote.
     * @throws SecurityException An exception generated by the Security system.
     */
	public void deposit(AccountInfo accountInfo, float amount) throws ATMException, RemoteException, SecurityException;
	
	/**
	 * Withdraw money from an account.
	 * 
     * @param AccountInfo The individual account targeted with their pin code.
     * @param amount The amount of money being withdrawn.
     * @throws ATMException An exception caused during the transaction.
     * @throws RemoteException An exception caused by the Remote.
     * @throws SecurityException An exception generated by the Security system.
	 */
	public void withdraw(AccountInfo accountInfo, float amount) throws ATMException, RemoteException, SecurityException;
	
	/**
	 * Get the balance for an account.
	 * 
     * @param AccountInfo The individual account targeted with their pin code.
     * @return The amount of money held by the account.
     * @throws ATMException An exception caused during the transaction.
     * @throws RemoteException An exception caused by the Remote.
     * @throws SecurityException An exception generated by the Security system.
	 */
	public Float getBalance(AccountInfo accountInfo) throws ATMException, RemoteException, SecurityException;
	
	/**
	 * Withdraw money from one account and deposit it into another.
	 * 
	 * @param accountInfoFrom The account and pin code to withdraw the money from.
	 * @param accountInfoTo The account and pin code to deposit the money to.
	 * @param amount The amount of money to transfer.
	 * @throws ATMException An exception caused during the transaction.
     * @throws RemoteException An exception caused by the Remote.
     * @throws SecurityException An exception generated by the Security system.
	 */
    public void transfer(AccountInfo accountInfoFrom, AccountInfo accountInfoTo, float amount) throws ATMException, RemoteException, SecurityException;
    
	/**
	 * Allow the client to register with the listener.
	 * 
	 * @param atmListener The client connection.
     * @throws RemoteException An exception caused by the Remote.
	 */
	public void register(ATMListener atmListener) throws RemoteException;
	
	/**
     * Allow the client to register with the listener.
     * 
     * @param atmListener The client connection.
     * @throws ATMException An exception caused by an unknown connection.
     * @throws RemoteException An exception caused by the Remote.
     */
	public void unregister(ATMListener atmListener) throws ATMException, RemoteException;
	
}
