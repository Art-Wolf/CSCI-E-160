package cscie160.hw2;

import java.util.Random;

/**
 * Track passengers as get on and off the elevator at different floors.
 *
 * @author John Doyle
 * @version 2.0
 */
public class Elevator {
    /** The total capacity for the elevator - logic not implemented. */
    private static final int CAPACITY = 10;

    /** The top floor the elevator goes to. */
    private static final int TOP_FLOOR = 7;

    /** Number of executions the test run will complete. */
    private static final int RUN_TOTAL = 25;

    /** The current floor the Elevator located at, default ground floor. */
    private int currentFloor = 0;

    /** Direction the elevator is moving in, default up. */
    private Direction direction = Direction.UP;

    /** Array of passengers with the destination floors. */
    private int[] passengerList;

    /** Array of floors with passengers waiting to board. */
    private boolean[] floorRegistry;

    /** Array of floors in the building. */
    private Floor[] buildingsFloor;

    /** Current total number of passengers in the Elevator. */
    private int passengerCount = 0;

    /**
     * Elevator constructor, initializes the floors in the building.
     *
     */
    public Elevator() {

        /** Initialize the class array to the building size. */
        buildingsFloor = new Floor[TOP_FLOOR];
        passengerList = new int[TOP_FLOOR];
        floorRegistry = new boolean[TOP_FLOOR];

        /** First floor has no passengers waiting. */
        buildingsFloor[0] = new Floor(0, 0, this);

        /** For each floor, generate a number of passengers. */
        for (int i = 1; i < TOP_FLOOR; i++) {
            buildingsFloor[i] = new Floor(i, new Random().nextInt(CAPACITY),
                    this);

        }

        /**
         * Start with a number of passengers in the Elevator heading to a
         * specific floor. Don't over fill the elevator.
         */
        for (int i = 0; (i < buildingsFloor.length)
                && (passengerCount <= CAPACITY); i++) {
            int newPassengers = new Random().nextInt(CAPACITY);

            /** Add the new passengers if they all fit. */
            if ((newPassengers + passengerCount) <= CAPACITY) {
                passengerList[i] = newPassengers;
                passengerCount += passengerList[i];
            }
        }
    }

    /**
     * A floor can register with the elevator that there are passengers waiting
     * to be collected.
     *
     * @param floorNumber The floor number which made the request.
     */
    public final void registerRequest(final int floorNumber) {
        if ((floorNumber > 0) && (floorNumber <= TOP_FLOOR)) {
            floorRegistry[floorNumber] = true;
        }
    }

    /**
     * When all passengers have been collected from a particular floor, the
     * floor will remove it's request.
     *
     * @param floorNumber The floor object which is removing the request.
     */
    public final void unregisterRequest(final int floorNumber) {
        if ((floorNumber > 0) && (floorNumber <= TOP_FLOOR)) {
            floorRegistry[floorNumber] = false;
        }
    }

    /**
     * Moves the elevator in the direction it is currently going. Let passengers
     * board and unload at the required floors and reverse direction when
     * appropriate.
     *
     */
    public final void move() {

        /** Continue the elevator in the direction it was previously moving. */
        if (direction == Direction.UP) {
            currentFloor++;
        } else {
            currentFloor--;
        }

        /**
         * If there are passengers destined for the floor or if there are people
         * waiting on the floor and there is room on the elevator, stop the
         * elevator.
         */
        if ((passengerList[currentFloor] > 0)
                || ((floorRegistry[currentFloor])
                        && (passengerCount <= CAPACITY))) {
            stop();
        }

        /**
         * Reverse the direction of the elevator when it hits the building
         * ground or top floor.
         */
        if (currentFloor == (buildingsFloor.length - 1)) {
            direction = Direction.DOWN;
        } else if (currentFloor == 0) {
            direction = Direction.UP;
        }
    }

    /**
     * Print out the current status of the Elevator - number of passengers and
     * current floor.
     *
     * @return Current status of the elevator.
     */
    @Override
    public final String toString() {

        String output = "Currently " + passengerCount + " passengers on board";
        output += System.getProperty("line.separator");
        output += "Current Floor " + (currentFloor + 1);
        output += System.getProperty("line.separator");
        output += "Direction: " + direction.name();
        output += System.getProperty("line.separator");

        return output;
    }

    /**
     * When the elevator reaches a floor which one or more passengers had
     * indicated was their destination, the passenger count decrements and the
     * passengers are removed. The status is then printed out to the console.
     *
     */
    private void stop() {
        System.out.println("Stopping on floor " + (currentFloor + 1));

        /** Pass control to the Floor object. */
        Floor tempFloor = buildingsFloor[currentFloor];
        tempFloor.unloadPassengers(this);

        /** Print status. */
        System.out.println(this);

    }

    /**
     * Any passengers that are to get off at this floor are unloaded.
     *
     * @return The number of passengers who disembarked on to the floor.
     */
    public final int unloadPassengers() {

        int passengersOnFloor = passengerList[currentFloor];
        passengerCount -= passengersOnFloor;
        passengerList[currentFloor] = 0;

        return passengersOnFloor;
    }

    /**
     * Moves the elevator in the direction it is currently going. If it hits the
     * top or bottom floor it reverses direction.
     *
     * @param floorNumber The floor the passenger is boarding from.
     * @throws ElevatorFullException thrown when elevator hits capacity.
     */
    public final void boardPassenger(final int floorNumber)
            throws ElevatorFullException {
        if (CAPACITY == passengerCount) {
            throw new ElevatorFullException("Elevator full.");
        }

        passengerList[floorNumber]++;
        passengerCount++;
    }

    /**
     * Add three passengers and run the elevator up and down the building to
     * ensure the passengers get off correctly.
     *
     * @param args Arguments passed into the application.
     */
    public static void main(final String[] args) {
        Elevator myElevator = new Elevator();

        System.out.println(myElevator);

        for (int i = 0; i < RUN_TOTAL; i++) {
            myElevator.move();
        }
    }
}
