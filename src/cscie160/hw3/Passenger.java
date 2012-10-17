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
     * 
     */
    public Passenger(Floor currentFloor, Floor destinationFloor) {
        super(currentFloor);
        this.destinationFloor = destinationFloor;
    }

    /**
     * Getter for the destination Floor of the Passenger.
     *
     * @return the destinationFloor
     */
    public Floor getDestinationFloor() {
        return destinationFloor;
    }
}
