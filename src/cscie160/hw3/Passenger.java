package cscie160.hw3;

/**
 * Individual passenger on a floor.
 *
 * @author John Doyle
 * @version 1.0
 */
public class Passenger {

    /** Track the floor the Passenger is currently on. */
    private Floor currentFloor;
    /** Track the floor the Passenger is destined for. */
    private Floor destinationFloor;

    /**
     * Getter for the current Floor the Passenger is on.
     *
     * @return the currentFloor
     */
    public Floor getCurrentFloor() {
        return currentFloor;
    }

    /**
     * Set the current Floor the Passenger is on.
     *
     * @param currentFloor the currentFloor to set
     */
    public void setCurrentFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }

    /**
     * Getter for the destination Floor of the Passenger.
     *
     * @return the destinationFloor
     */
    public Floor getDestinationFloor() {
        return destinationFloor;
    }

    /**
     * Set the destination Floor for the Passenger.
     *
     * @param destinationFloor the destinationFloor to set
     */
    public void setDestinationFloor(Floor destinationFloor) {
        this.destinationFloor = destinationFloor;
    }
}
