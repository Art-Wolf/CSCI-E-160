package cscie160.hw3;

public interface Person {

    /**
     * Getter for the current Floor the Passenger is on.
     *
     * @return the currentFloor
     */
    public abstract Floor getCurrentFloor();

    /**
     * Set the current Floor the Passenger is on.
     *
     * @param currentFloor the currentFloor to set
     */
    public abstract void setCurrentFloor(Floor currentFloor);

}