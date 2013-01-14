package cscie160.project;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * ATM Factory that will be added to the RMI Registry.
 * 
 * @author johndoyle
 */
public interface ATMFactory extends Remote {
    /**
     * Factory method to return an ATM Implementation.
     * 
     * @return An ATM Implementation.
     * @throws RemoteException
     */
    public ATM getATM() throws RemoteException;
}
