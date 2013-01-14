package cscie160.project;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.UnknownHostException;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings("serial")
public class Client extends UnicastRemoteObject implements ATMListener {
    
    /**
     * The connection identifier.
     */
    private String clientString;
    
    /**
     * Default constructor that sets the client string to identify this connnection.
     * @throws RemoteException
     */
    public Client() throws RemoteException {
        /**
         * REquired for UnicastRemoteObjects
         */
        super();
        
        /**
         * To identify this connection we will set the Host IP address.
         */
        InetAddress localhost;
            
            try {
                localhost = InetAddress.getLocalHost();
                clientString = localhost.getHostAddress();
            } catch (java.net.UnknownHostException e) {
                e.printStackTrace();
            }
        
    }

    /**
     * If printing out the Client connection return the Host IP Address.
     */
    public String toString() {
        return clientString;
    }
    
    /**
     * Main method.
     * 
     * @param args
     * @throws RemoteException
     */
    public static void main(String[] args) throws RemoteException {
        /**
         * Instantiate the object.
         */
        Client client = new Client();
        
        /**
         * Run the test suite and exit afterwards.
         */
        try {
            client.clientTest();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ATMException e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
    
    /**
     * Kick off the test suite.
     * 
     * @throws RemoteException If there is an issue with the connection
     * @throws ATMException If there is a issue with the transaction.
     */
    public void clientTest() throws RemoteException, ATMException {
        ATM atm = null;

        System.out.println("Going to retrieve the ATM from RMI name service.");
        
        try {
            
            /**
             * Get the ATM Factory Binding from the name service.
             */
            ATMFactory factory = (ATMFactory) Naming
                    .lookup("//localhost/atmfactory");
            
            System.out.println("Connected to name service.");
            
            /**
             * Initialize the ATM from the ATM Factory.
             */
            atm = factory.getATM();
            
            System.out.println("Got ATM implementation.");
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (NotBoundException nbe) {
            nbe.printStackTrace();
        } catch (UnknownHostException uhe) {
            uhe.printStackTrace();
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        
        /**
         * If the ATM was initialized correctly, run the tests.
         */
        if (atm != null) {
            
            /**
             * Register the connection listener.
             */
            atm.register(this);
            
            /**
             * Run the tests.
             */
            testATM(atm);
            
            /**
             * Unregister the listener.
             */
            atm.unregister(this);
        }
    }
    
    /**
     * Private method to return an AccountInfo object.
     * 
     * @param accountNumber
     * @param pin
     * @return AccountInfo object.
     */
    private static AccountInfo getAccountInfo(int accountNumber, int pin)
    {
        return new AccountInfo(accountNumber, pin);
    }
    
    public static void testATM(ATM atm) {
        if (atm!=null) {
           printBalances(atm);
           performTestOne(atm);
           performTestTwo(atm);
           performTestThree(atm);
           performTestFour(atm);
           performTestFive(atm);
           performTestSix(atm);
           performTestSeven(atm);
           performTestEight(atm);
           performTestNine(atm);
           printBalances(atm);
        }
     }        
     public static void printBalances(ATM atm) {        
        try {
           System.out.println("Balance(0000001): "+atm.getBalance(getAccountInfo(0000001, 1234)));
           System.out.println("Balance(0000002): "+atm.getBalance(getAccountInfo(0000002, 2345)));
           System.out.println("Balance(0000003): "+atm.getBalance(getAccountInfo(0000003, 3456)));
        } catch (Exception e) {
           e.printStackTrace();
        }
     }
     public static void performTestOne(ATM atm) {       
        try {
           atm.getBalance(getAccountInfo(0000001, 5555));
        } catch (Exception e) {
           System.out.println("Failed as expected: "+e);
        }
     }
     public static void performTestTwo(ATM atm) {       
        try {
           atm.withdraw(getAccountInfo(0000002, 2345), 500);
        } catch (Exception e) {
           System.out.println("Failed as expected: "+e);
        }
     }
     public static void performTestThree(ATM atm) {        
        try {
           atm.withdraw(getAccountInfo(0000001, 1234), 50);
        } catch (Exception e) {
           System.out.println("Failed as expected: "+e);
        }
     }
     public static void performTestFour(ATM atm) {         
        try {
           atm.deposit(getAccountInfo(0000001, 1234), 500);
        } catch (Exception e) {
           System.out.println("Unexpected error: "+e);
        }
     }
     public static void performTestFive(ATM atm) {         
        try {
           atm.deposit(getAccountInfo(0000002, 2345), 100);
           } catch (Exception e) {
           System.out.println("Unexpected error: "+e);
        }
     }
     public static void performTestSix(ATM atm) {       
        try {
           atm.withdraw(getAccountInfo(0000001, 1234), 100);
          } catch (Exception e) {
           System.out.println("Unexpected error: "+e);
        }
     }
     public static void performTestSeven(ATM atm) {        
        try {
           atm.withdraw(getAccountInfo(0000003, 3456), 300);
            } catch (Exception e) {
           System.out.println("Unexpected error: "+e);
        }
     }
     public static void performTestEight(ATM atm) {        
        try {
           atm.withdraw(getAccountInfo(0000001, 1234), 200);
          } catch (Exception e) {
           System.out.println("Failed as expected: "+e);
        }
     }
     public static void performTestNine(ATM atm) {        
        try {
           atm.transfer(getAccountInfo(0000001, 1234),getAccountInfo(0000002, 2345), 100);
          } catch (Exception e) {
           System.out.println("Unexpected error: "+e);
        }
     }

    @Override
    public void handler(TransactionNotification notification)
            throws RemoteException {
        // TODO Auto-generated method stub
        System.out.println("Transaction Notification " + notification.toString());
    }

}
