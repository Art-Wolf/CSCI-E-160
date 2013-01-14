package cscie160.project;

import java.rmi.Naming;

/**
 * The bank server where the ATMs are registered with the naming service.
 * 
 * @author johndoyle
 */
public class BankServer 
{
    /**
     * The main method which will add the ATM Factory to the naming service.
     * @param argv
     */
	public static void main(String argv[]) 
	{
	    try {
	        /**
	         * Initialize the Bank Factory with an implementation.
	         */
	        BankFactory bankFactory = new BankFactoryImpl();
	        
	        /**
	         * Bind the factory to the RMI Registry.
	         */
	        Naming.bind("//localhost/bankfactory", bankFactory);
	    } catch (Exception e) {
	        System.out.println("An exception occured while binding the BankFactory.");
	        e.printStackTrace();
	    }
	    
	    try {
            /**
             * Initialize the Security Factory with an implementation.
             */
            SecurityFactory securityFactory = new SecurityFactoryImpl();
            
            /**
             * Bind the factory to the RMI Registry.
             */
            Naming.bind("//localhost/securityfactory", securityFactory);
        } catch (Exception e) {
            System.out.println("An exception occured while binding the SecurityFactory.");
            e.printStackTrace();
        }
	}
}
