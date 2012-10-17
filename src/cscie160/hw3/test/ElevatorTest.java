package cscie160.hw3.test;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import cscie160.hw3.Elevator;
import cscie160.hw3.ElevatorFullException;
import cscie160.hw3.Floor;
import cscie160.hw3.Passenger;

/**
 * Java Unit Test for the Elevator object.
 *
 * @author John Doyle
 * @version 1.0
 */
public class ElevatorTest {
    /** Starting index for substring to pick out the number of passengers. */
    private static final int PASS_COUNT_INDEX_START = 10;

    /** Ending index for substring to pick out the number of passengers. */
    private static final int PASS_COUNT_INDEX_END = 11;

    /**
     * Test that the elevator initializer correctly adds passengers to the
     * Elevator.
     *
     */
    @Test
    public final void initElevatorPassenger() {
        Elevator myElevator = new Elevator();

        /** Get the current number of passengers. */
        int currentPassengers = Integer.parseInt(myElevator.toString()
                .substring(PASS_COUNT_INDEX_START, PASS_COUNT_INDEX_END));

        /** We expect at least one passenger to have been generated. */
        if (currentPassengers != 0) {
            assertTrue(false);
        }
    }

    /**
     * Check that the Elevator initializes going UP.
     *
     */
    @Test
    public final void initElevatorDirection() {
        Elevator myElevator = new Elevator();

        if (myElevator.toString().indexOf("Direction: UP") < 0) {
            assertTrue(false);
        }
    }

    /**
     * Confirm you can add a passenger to an elevator that is not at capacity.
     *
     */
    @Test
    public final void addPassenger() {

        Elevator myElevator = new Elevator();

        /** Ensure there is space on the elevator. */
        while (myElevator.toString()
                .indexOf("Currently 10 passengers on board") >= 0) {
            myElevator = new Elevator();
        }

        /** Get the current number of passengers. */
        int currentPassengers = Integer.parseInt(myElevator.toString()
                .substring(PASS_COUNT_INDEX_START, PASS_COUNT_INDEX_END));

        /** Add a passenger, fail if an exception is thrown. */
        Floor testFloor = new Floor(1, myElevator);
        Floor testFloor2 = new Floor(2, myElevator);
        Passenger testPassenger = new Passenger(testFloor, testFloor2);
        try {
            myElevator.boardPassenger(testPassenger);
        } catch (ElevatorFullException excep) {
            assertTrue(false);
        }

        /** Confirm the status includes our new passenger. */
        if (myElevator.toString()
                .indexOf(
                        "Currently " + (currentPassengers + 1)
                                + " passengers on board") < 0) {
            assertTrue(false);
        }
    }

    /**
     * Confirm an exception is thrown when an elevator is over capacity.
     *
     */
    @Test
    public final void addPassengerException() {

        Elevator myElevator = new Elevator();
        boolean testSuccess = false;
        Floor testFloor = new Floor(1, myElevator);
        Floor testFloor2 = new Floor(2, myElevator);
        Passenger testPassenger1 = new Passenger(testFloor, testFloor2);
        Passenger testPassenger2 = new Passenger(testFloor, testFloor2);
        Passenger testPassenger3 = new Passenger(testFloor, testFloor2);
        Passenger testPassenger4 = new Passenger(testFloor, testFloor2);
        Passenger testPassenger5 = new Passenger(testFloor, testFloor2);
        Passenger testPassenger6 = new Passenger(testFloor, testFloor2);
        Passenger testPassenger7 = new Passenger(testFloor, testFloor2);
        Passenger testPassenger8 = new Passenger(testFloor, testFloor2);
        Passenger testPassenger9 = new Passenger(testFloor, testFloor2);
        Passenger testPassenger10 = new Passenger(testFloor, testFloor2);
        Passenger testPassenger11 = new Passenger(testFloor, testFloor2);
        Passenger testPassenger12 = new Passenger(testFloor, testFloor2);
        /** Add 11 passengers, fail if an exception is not thrown. */
        try {
            myElevator.boardPassenger(testPassenger1);
            myElevator.boardPassenger(testPassenger2);
            myElevator.boardPassenger(testPassenger3);
            myElevator.boardPassenger(testPassenger4);
            myElevator.boardPassenger(testPassenger5);
            myElevator.boardPassenger(testPassenger6);
            myElevator.boardPassenger(testPassenger7);
            myElevator.boardPassenger(testPassenger8);
            myElevator.boardPassenger(testPassenger9);
            myElevator.boardPassenger(testPassenger10);
            myElevator.boardPassenger(testPassenger11);
            myElevator.boardPassenger(testPassenger12);
        } catch (ElevatorFullException excep) {
            testSuccess = true;
        }

        if (!testSuccess) {
            assertTrue(false);
        }
    }

    /**
     * Confirm the elevator stops at a floor a passenger is destined for.
     *
     */
    @Test
    public final void changeFloor() {
        Elevator myElevator = new Elevator();

        /** Ensure there is space on the elevator. */
        while (myElevator.toString()
                .indexOf("Currently 10 passengers on board") >= 0) {
            myElevator = new Elevator();
        }

        /**
         * Add a passenger destined for the second floor, index 1.
         * fail if an exception is thrown.
         */
        Floor testFloor = new Floor(1, myElevator);
        Floor testFloor2 = new Floor(2, myElevator);
        Passenger testPassenger = new Passenger(testFloor, testFloor2);
        try {
            myElevator.boardPassenger(testPassenger);
        } catch (ElevatorFullException excep) {
            assertTrue(false);
        }

        myElevator.move();

        /** Confirm elevator stops at the second floor. */
        if (myElevator.toString().indexOf("Current Floor 2") < 0) {
            assertTrue(false);
        }
    }
}
