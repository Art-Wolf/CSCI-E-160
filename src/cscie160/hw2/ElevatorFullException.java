package cscie160.hw2;

/**
 * Custom exception to occur when an elevator reaches capacity and more
 * passengers wish to board.
 *
 * @author John Doyle
 * @version 1.0
 */
public class ElevatorFullException extends Exception {

    /**
     * Standard constructor - pass message to the super class.
     *
     * @param msg Explanation of the exception.
     */
    public ElevatorFullException(final String msg) {
        super(msg);
    }
}
