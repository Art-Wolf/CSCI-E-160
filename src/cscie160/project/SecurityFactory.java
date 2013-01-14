package cscie160.project;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Security Factory that will be added to the RMI Registry.
 * 
 * @author johndoyle
 */
public interface SecurityFactory extends Remote {
    /**
     * Factory method to return an Security Implementation.
     * 
     * @return A Security Implementation.
     * @throws RemoteException
     */
    public Security getSecurity() throws RemoteException;
}