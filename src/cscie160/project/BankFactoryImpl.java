package cscie160.project;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * The ATM Factory Implementation that will return a new ATM Implementation.
 * 
 * @author johndoyle
 */
@SuppressWarnings("serial")
public class BankFactoryImpl extends UnicastRemoteObject implements BankFactory {

    /**
     * Constructor that calls the supper constructor - required for the UnicastRemoteObject
     * @throws RemoteException
     */
    public BankFactoryImpl() throws RemoteException {
        super();
    }
    
    /**
     * Return an ATM Implementation
     *
     */
    @Override
    public Bank getBank() throws RemoteException {
        return new BankImplementation();
    }

}
