package cscie160.hw3;

/**
 * Implementation of person in the building - they must have a current floor..
 *
 * @author John Doyle
 * @version 1.0
 */
public abstract class Resident implements Person {

    /** Track the floor the Passenger is currently on. */
    private Floor currentFloor;

    /**
     * Constructor that sets where the person is in the building.
     *
     * @param currentFloor The Floor object the Person resides on currently.
     */
    public Resident(final Floor currentFloor) {
        this.currentFloor = currentFloor;
    }

    /**
     * Get the current floor the person is on.
     *
     * @see cscie160.hw3.Person#getCurrentFloor()
     * @return Get the floor the person resides on.
     */
    @Override
    public final Floor getCurrentFloor() {
        return currentFloor;
    }

    /**
     * Set the current Floor the person is on.
     *
     * @see cscie160.hw3.Person#setCurrentFloor(cscie160.hw3.Floor)
     * @param currentFloor The Floor the person is being assigned to.
     */
    @Override
    public final void setCurrentFloor(final Floor currentFloor) {
        this.currentFloor = currentFloor;
    }

}
