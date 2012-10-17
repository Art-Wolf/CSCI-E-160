/**
 * 
 */
package cscie160.hw3;

/**
 * @author johndoyle
 *
 */
public abstract class Resident implements Person {

    /** Track the floor the Passenger is currently on. */
    private Floor currentFloor;
    
    public Resident(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }
    
    /* (non-Javadoc)
     * @see cscie160.hw3.Person#getCurrentFloor()
     */
    @Override
    public Floor getCurrentFloor() {
        return currentFloor;
    }

    /* (non-Javadoc)
     * @see cscie160.hw3.Person#setCurrentFloor(cscie160.hw3.Floor)
     */
    @Override
    public void setCurrentFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }

}
