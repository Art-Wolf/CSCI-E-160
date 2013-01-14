package cscie160.project;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * The Security Factory Implementation that will return a new Security Implementation.
 * 
 * @author johndoyle
 */
public class SecurityFactoryImpl extends UnicastRemoteObject implements SecurityFactory {

    /**
     * Constructor that calls the supper constructor - required for the UnicastRemoteObject
     * @throws RemoteException
     */
    public SecurityFactoryImpl() throws RemoteException {
        super();
    }
    
    /**
     * Return an Security Implementation
     *
     */
    public Security getSecurity() throws RemoteException {
        return new SecurityImplementation();
    }
}