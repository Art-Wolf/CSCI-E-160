package cscie160.project;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * The ATM Factory Implementation that will return a new ATM Implementation.
 * 
 * @author johndoyle
 */
@SuppressWarnings("serial")
public class ATMFactoryImpl extends UnicastRemoteObject implements ATMFactory {

    /**
     * Constructor that calls the supper constructor - required for the UnicastRemoteObject
     * @throws RemoteException
     */
    public ATMFactoryImpl() throws RemoteException {
        super();
    }
    
    /**
     * Return an ATM Implementation
     *
     */
    public ATM getATM() throws RemoteException {
        return new ATMImplemention();
    }

}
