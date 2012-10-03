package cscie160.hw2;

/**
 * Track passengers waiting to board an Elevator.
 *
 * @author John Doyle
 * @version 1.0
 */
public class Floor {
    /** The floor number the object is assigned to. */
    private final int floorNumber;

    /** Current total number of passengers in the Elevator. */
    private int passengerCount = 0;

    /**
     * Floor constructor that registers passengers with the elevator.
     *
     * @param floorNumber The floor number of the object.
     * @param passengerCount Number of passengers waiting on the floor.
     * @param myElevator The elevator that services the floor.
     */
    public Floor(final int floorNumber, final int passengerCount,
            final Elevator myElevator) {
        this.floorNumber = floorNumber;
        this.passengerCount = passengerCount;
        myElevator.registerRequest(this.floorNumber);
    }

    /**
     * Have the floor remove passengers destined for it and then load passengers
     * up to the capacity of the Elevator.
     *
     * @param myElevator The elevator servicing the floor.
     */
    public final void unloadPassengers(final Elevator myElevator) {
        int offElevator = myElevator.unloadPassengers();

        /** Print the status of people getting off the elevator. */
        System.out.println("\t" + offElevator
                + " passengers got off the elevator.");

        /** Print status of the elevator currently. */
        System.out.println("\tCurrently " + passengerCount
                + " passengers on the floor.");

        /**
         * For each passenger, try and have them board the elevator until
         * everyone is on board or an exception is thrown.
         */
        for (int i = passengerCount; i > 0; i--) {
            try {
                myElevator.boardPassenger(0);
            } catch (ElevatorFullException efEx) {
                System.out.println("\tElevator Full - "
                        + "stopped loading passengers;");
                break;
            }

            /**
             * Decrees the people on the floor on successfully loading a person.
             */
            passengerCount--;
        }

        /** Print status of the elevator after boarding. */
        System.out.println("\t" + passengerCount
                + " passengers remaining on the floor.");

        /** If no-one left - unregister the request. */
        if (passengerCount == 0) {
            myElevator.unregisterRequest(floorNumber);
        }
    }
}
