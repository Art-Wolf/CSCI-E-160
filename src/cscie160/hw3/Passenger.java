package cscie160.hw3;

/**
 * Individual passenger on a floor.
 *
 * @author John Doyle
 * @version 1.0
 */
public class Passenger extends Resident {

    /** Track the floor the Passenger is destined for. */
    private final Floor destinationFloor;

    /**
     * Constructor that sets the current floor by calling the super constructor
     * and then sets the destination floor.
     *
     * @param currentFloor The floor the passenger resides on.
     * @param destinationFloor The floor the passenger aims for.
     */
    public Passenger(final Floor currentFloor, final Floor destinationFloor) {
        super(currentFloor);
        this.destinationFloor = destinationFloor;
    }

    /**
     * Getter for the destination Floor of the Passenger.
     *
     * @return the destinationFloor
     */
    public final Floor getDestinationFloor() {
        return destinationFloor;
    }

    /**
     * Print out the Passenger's current floor.
     *
     * @return Current status of the passenger.
     */
    @Override
    public final String toString() {

        String output = super.toString();

        output += " destined for: " + (destinationFloor.getFloorNumber() + 1);

        return output;
    }
}
