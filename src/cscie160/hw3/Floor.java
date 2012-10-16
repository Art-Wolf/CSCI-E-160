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
    private ArrayDeque<Passenger> passengersUp = new ArrayDeque<Passenger>();

    /** Track passengers waiting to down a floor. */
    private ArrayDeque<Passenger> passengersDown = new ArrayDeque<Passenger>();

    /** Track passengers that are staying on the floor. */
    private ArrayList<Passenger> passengersOnFloor = new ArrayList<Passenger>();

    /**
     * Floor constructor that registers passengers with the elevator.
     *
     * @param floorNumber The floor number of the object.
     * @param myElevator The elevator that services the floor.
     */
    public Floor(final int floorNumber, 
            final Elevator myElevator) {
        this.floorNumber = floorNumber;
        this.myElevator = myElevator;
    }

    /**
     * Have the floor remove passengers destined for it and then load passengers
     * up to the capacity of the Elevator.
     *
     * @param myElevator The elevator servicing the floor.
     */
    public final void unloadPassengers(final Elevator myElevator) {

        /** If the Floor has passengers wait, remove the request for the
         * Elevator.
         */
        if ((passengersUp.size() > 0) && (passengersDown.size() > 0)) {
            myElevator.unregisterRequest(floorNumber);
        }

        int initialCount = passengersOnFloor.size();
        
        passengersOnFloor.addAll(myElevator.unloadPassengers());

        int offElevator = passengersOnFloor.size() - initialCount;
        
        /** Print the status of people getting off the elevator. */
        System.out.println("\t" + offElevator
                + " passengers got off the elevator.");

        /** Print status of the passengers going up. */
        System.out.println("\tCurrently " + passengersUp.size()
                + " passengers waiting to go up.");
        
        /** Print status of the passengers going down. */
        System.out.println("\tCurrently " + passengersDown.size()
                + " passengers waiting to go down.");
        
        /** Print status of the passengers staying on the floor. */
        System.out.println("\tCurrently " + passengersOnFloor.size()
                + " passengers staying on the floor.");

        /**
         * For each passenger, try and have them board the elevator until
         * everyone is on board or an exception is thrown.
         */
        if(myElevator.getDirection() == Direction.UP) {
            do {
            try {
                myElevator.boardPassenger(passengersUp.pop());
            } catch (ElevatorFullException efEx) {
                            System.out.println("\tElevator Full - "
                                    + "stopped loading passengers;");
                            break;
                        }
            } while (passengersUp.size() > 0);
          } else {
              do {
                  try {
                      myElevator.boardPassenger(passengersDown.pop());
                  } catch (ElevatorFullException efEx) {
                                  System.out.println("\tElevator Full - "
                                          + "stopped loading passengers;");
                                  break;
                              }
                  } while (passengersDown.size() > 0);
                }
        

    /** Print status of the passengers going up. */
    System.out.println("\tCurrently " + passengersUp.size()
            + " passengers waiting to go up.");
    
    /** Print status of the passengers going down. */
    System.out.println("\tCurrently " + passengersDown.size()
            + " passengers waiting to go down.");
    
    /** Print status of the passengers staying on the floor. */
    System.out.println("\tCurrently " + passengersOnFloor.size()
            + " passengers staying on the floor.");
    }

    /**
     * @param passengersUp the passengersUp to set
     */
    public void setPassengersUp(ArrayList<Passenger> passengersUp) {
        for(Passenger currentPassenger: passengersUp) { 
            myElevator.registerRequest(this.floorNumber);
            this.passengersUp.add(currentPassenger);
        }
    }

    /**
     * @param passengersDown the passengersDown to set
     */
    public void setPassengersDown(ArrayList<Passenger> passengersDown) {
        for(Passenger currentPassenger: passengersDown) { 
            myElevator.registerRequest(this.floorNumber);
            this.passengersDown.add(currentPassenger);
        }
    }

    /**
     * @param passengersOnFloor the passengersOnFloor to set
     */
    public void setPassengersOnFloor(ArrayList<Passenger> passengersOnFloor) {
        this.passengersOnFloor = passengersOnFloor;
    }
    
    public int getFloorNumber() {
        return floorNumber;
    }
}
