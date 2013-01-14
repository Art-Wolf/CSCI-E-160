package cscie160.project;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The interface into Security.
 *
 * @author johndoyle
 */
public interface Security extends Remote
{
    /**
     * Confirm that the account pin provided matches the security system pin.
     * 
     * @param accountInfo The acocunt number and pin code.
     * @return True if the pin codes match.
     * @throws RemoteException If there is an exception with the connection.
     */
	public boolean authenticated(AccountInfo accountInfo) throws RemoteException;
	
	/**
	 * Confirm that the account is permitted to execute the desired command.
	 * 
	 * @param accountInfo The account number and pin code.
	 * @param command The command the account wants to execute.
	 * @return True if the account if permitted.
	 * @throws RemoteException If there is an exception with the connection.
	 */
	public boolean authorized(AccountInfo accountInfo, Commands command) throws RemoteException;
}
