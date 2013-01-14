package cscie160.project;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for clients as listeners to the ATM transactions.
 *
 * @author johndoyle
 *
 */
public interface ATMListener extends Remote
{
    /**
     * How the client can handle the Transaction Notifictaions
     * sent from the ATM
     * 
     * @param notification The account and command making the transaction.
     * @throws RemoteException If there is an issue with the connection.
     */
    public void handler(TransactionNotification notification)
            throws RemoteException;
}
