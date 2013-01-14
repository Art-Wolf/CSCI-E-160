package cscie160.project;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The interface into a Bank.
 *
 * @author johndoyle
 */
public interface Bank extends Remote
{
    /**
     * The Bank system will return the Account object that matches the AccountInfo
     * properties.
     * 
     * @param accountInfo The account number and pin code.
     * @return The Account object.
     * @throws RemoteException If there is an issue with the connection.
     */
	public Account getAccount(AccountInfo accountInfo) throws RemoteException;	
	
	/**
	 * If the ATM makes a change to the Account this change must be passed back to the
	 * Bank.
	 * 
	 * @param accountInfo The account number and pin code.
	 * @param account The Account object that has been changed.
	 * @throws RemoteException If there is an issue with the connection.
	 */
	public void setAccount(AccountInfo accountInfo, Account account) throws RemoteException;
}
