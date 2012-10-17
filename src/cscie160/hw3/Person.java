package cscie160.hw3;

/**
 * Basic interface all people will have in the building.
 *
 * @author John Doyle
 * @version 1.0
 */
public interface Person {

    /**
     * Getter for the current Floor the Passenger is on.
     *
     * @return the currentFloor
     */
    Floor getCurrentFloor();

    /**
     * Set the current Floor the Passenger is on.
     *
     * @param currentFloor the currentFloor to set
     */
    void setCurrentFloor(Floor currentFloor);
}
