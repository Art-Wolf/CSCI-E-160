package cscie160.project;

/**
 * Generic exception class for Security Exceptions.
 *
 * @author johndoyle
 */
public class SecurityException extends Exception{
    /**
     * Generic exception.
     * 
     * @param msg Message explaining the exception.
     */
    public SecurityException(String msg) 
    {
        super(msg);
    }
}