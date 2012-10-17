package cscie160.hw3;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Track passengers waiting to board an Elevator.
 *
 * @author John Doyle
 * @version 1.0
 */
public class Floor {
    /** The floor number the object is assigned to. */
    private final int floorNumber;

    /** The Elevator that comes to this floor. */
    private final Elevator myElevator;

    /** Track passengers waiting to go up a floor. */
    private final ArrayDeque<Passenger> passengersUp =
            new ArrayDeque<Passenger>();

    /** Track passengers waiting to down a floor. */
    private final ArrayDeque<Passenger> passengersDown =
            new ArrayDeque<Passenger>();

    /** Track passengers that are staying on the floor. */
    private ArrayList<Passenger> passengersOnFloor =
            new ArrayList<Passenger>();

    /**
     * Floor constructor that registers passengers with the elevator.
     *
     * @param floorNumber
     *            The floor number of the object.
     * @param myElevator
     *            The elevator that services the floor.
     */
    public Floor(final int floorNumber, final Elevator myElevator) {
        this.floorNumber = floorNumber;
        this.myElevator = myElevator;
    }

    /**
     * Have the floor remove passengers destined for it and then load passengers
     * up to the capacity of the Elevator.
     *
     * @param myElevator
     *            The elevator servicing the floor.
     */
    public final void unloadPassengers(final Elevator myElevator) {

        /**
         * Unload Passengers from the Elevator destined for the current floor.
         */
        passengersOnFloor.addAll(myElevator.unloadPassengers());

        /**
         * For each passenger, try and have them board the elevator until
         * everyone is on board or an exception is thrown.
         */
        if (myElevator.getDirection() == Direction.UP) {
            attempBoarding(myElevator, passengersUp);
        } else {
            attempBoarding(myElevator, passengersDown);
        }

        /**
         * Are there still passengers waiting to go up or down? If not
         * unregister the request with the Elevator.
         */
        if ((passengersUp.size() + passengersDown.size()) == 0) {
            myElevator.unregisterRequest(this.floorNumber);
        }
    }

    /**
     * Pop the top passenger waiting for the elevator and try to board. If the
     * elevator is full, return the passenger to the top of the queue.
     *
     * @param myElevator The elevator the passenger will attempt to board.
     * @param passengerList List of passengers waiting to board the elevator
     *            that is going in the current direction.
     */
    private void attempBoarding(final Elevator myElevator,
            final ArrayDeque<Passenger> passengerList) {

        /**
         * If there are passengers on the queue, attempt to board them.
         */
        while (passengerList.size() > 0) {
            /**
             * Take the first passenger off the queue, and board them.
             */
            Passenger tempPassenger = passengerList.pop();
            try {
                myElevator.boardPassenger(tempPassenger);
            } catch (ElevatorFullException efEx) {
                /**
                 * If the passenger failed to board - add the passenger
                 * back to the top of the queue.
                 */
                passengerList.addFirst(tempPassenger);
                System.out.println("\tElevator Full - "
                        + "stopped loading passengers;");
                break;
            }
        }
    }

    /**
     * Set the passenger queue aiming for floors above the current floor.
     * Register with the elevator if appropriate.
     *
     * @param passengersUp List of passengers going up.
     */
    public final void setPassengersUp(final ArrayList<Passenger> passengersUp) {
        if (passengersUp.size() > 0) {
            myElevator.registerRequest(this.floorNumber);
            this.passengersUp.addAll(passengersUp);
        }
    }

    /**
     * Set the passenger queue aiming for floors below the current floor.
     * Register with the elevator if appropriate.
     *
     * @param passengersDown List of passengers going down.
     */
    public final void setPassengersDown(
            final ArrayList<Passenger> passengersDown) {
        if (passengersDown.size() > 0) {
            myElevator.registerRequest(this.floorNumber);
            this.passengersDown.addAll(passengersDown);
        }
    }

    /**
     * Set the passenger list that are residing on the current floor.
     *
     * @param passengersOnFloor List of passengers staying on the floor.
     */
    public final void setPassengersOnFloor(
            final ArrayList<Passenger> passengersOnFloor) {
        this.passengersOnFloor = passengersOnFloor;
    }

    /**
     * Get the floor number of the object.
     *
     * @return The floor number.
     */
    public final int getFloorNumber() {
        return floorNumber;
    }
}
