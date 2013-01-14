package cscie160.project;

import java.rmi.Naming;

/**
 * The bank server where the ATMs are registered with the naming service.
 * 
 * @author johndoyle
 */
public class ATMServer 
{
    /**
     * The main method which will add the ATM Factory to the naming service.
     * @param argv
     */
	public static void main(String argv[]) 
	{
	    try {
	        /**
	         * Initialize the ATM Factory with an implementation.
	         */
	        ATMFactory atmFactory = new ATMFactoryImpl();
	        
	        /**
	         * Bind the factory to the RMI Registry.
	         */
	        Naming.bind("//localhost/atmfactory", atmFactory);
	    } catch (Exception e) {
	        System.out.println("An exception occured while binding the ATMFactory.");
	        e.printStackTrace();
	    }
	}
}
