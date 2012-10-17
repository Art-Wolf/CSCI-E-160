package cscie160.hw3.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import cscie160.hw3.Elevator;
import cscie160.hw3.Floor;
import cscie160.hw3.Passenger;

/**
 * Java Unit Test for the Passenger object.
 *
 * @author John Doyle
 * @version 1.0
 */
public class PassengerTest {
    private Passenger testPassenger;
    private final int testFloorNumberOne = 1;
    private final int testFloorNumberTwo = 2;
    private final int testFloorNumberThree = 3;
    
    @Before
    public void setUp() throws Exception {
        Elevator testElevator = new Elevator();
        Floor testFloor = new Floor(testFloorNumberOne, testElevator);
        Floor testFloor2 = new Floor(testFloorNumberTwo, testElevator);
        
        this.testPassenger = new Passenger(testFloor, testFloor2);
    }

    /**
     * Test that the passenger initialized current floor correctly.
     *
     */
    @Test
    public void initCurrentFloor() {
        
        Floor testFloor = this.testPassenger.getCurrentFloor();
        /** Check that the current Floor initialized correctly. */
        if (testFloor.getFloorNumber() != testFloorNumberOne) {
            assertTrue(false);
        }
    }

    /**
     * Test that the passenger initialized destination floor correctly.
     *
     */
    @Test
    public void initDestinationFloor() {
        
        Floor testFloor = this.testPassenger.getDestinationFloor();
        /** Check that the destination Floor initialized correctly. */
        if (testFloor.getFloorNumber() != testFloorNumberTwo) {
            assertTrue(false);
        }
    }
    
    /**
     * Test the setter and getter for the current Floor.
     *
     */
    @Test
    public void changeCurrentFloor() {

        Elevator testElevator = new Elevator();
        Floor testFloor = new Floor(testFloorNumberThree, testElevator);
        
        this.testPassenger.setCurrentFloor(testFloor);
        
        Floor testFloorCheck = this.testPassenger.getCurrentFloor();
        /** Check that the destination Floor initialized correctly. */
        if (testFloorCheck.getFloorNumber() != testFloorNumberThree) {
            assertTrue(false);
        }
    }
}