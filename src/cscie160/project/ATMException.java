package cscie160.project;

/**
 * Generic exception class for ATM Exceptions.
 *
 * @author johndoyle
 */
@SuppressWarnings("serial")
public class ATMException extends Exception 
{
    /**
     * Generic exception.
     * 
     * @param msg Message explaining the exception.
     */
    public ATMException(String msg) 
	{
		super(msg);
    }
}
