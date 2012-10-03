package cscie160.hw1;

/**
* Track passengers as they get on the elevator and off at their destination.
*
* @author   John Doyle
* @version   1.0
*/
public class Elevator {
   /** The total capacity for the elevator - logic not implemented. */
   public static final int CAPACITY = 10;

   /** The top floor the elevator goes to. */
   public static final int TOP_FLOOR = 7;

   /** The bottom floor the elevator goes to. */
   public static final int BOTTOM_FLOOR = 1;

   /** For test run, the first passenger will disembark on this floor. */
   public static final int FIRST_DESTINATION = 2;

   /** For test run, the second passenger will disembark on this floor. */
   public static final int SECOND_DESTINATION = 2;

   /** For test run, the third passenger will disembark on this floor. */
   public static final int THIRD_DESTINATION = 3;

   /** Number of executions the test run will complete. */
   public static final int RUN_TOTAL = 12;

   /** The current floor the Elevator located at, default ground floor. */
   private int currentFloor = 1;

   /** Direction the elevator is moving in, default up. */
   private boolean up = true;

   /** Array of passengers with the destination floors. */
   private int[] passengerList;

   /** Current total number of passengers in the Elevator. */
   private int passengerCount = 0;

   /** The next target floor to unload a passenger at. **/
   private int targetFloor = 1;

   /**
   * Elevator constructor that creates the floor array to track passenger
   * destinations.
   *
   */
   public Elevator() {
      passengerList = new int[TOP_FLOOR + 1];

      for (int i = BOTTOM_FLOOR; i <= TOP_FLOOR; i++) {
         passengerList[i] = 0;
      }
   }

   /**
   * Moves the elevator in the direction it is currently going. If it hits
   * the top or bottom floor it reverses direction.
   *
   */
   public final void move() {
      if (up) {
         if (currentFloor == TOP_FLOOR) {
            up = false;
            currentFloor--;
         } else {
            currentFloor++;
         }
      } else {
         if (currentFloor == BOTTOM_FLOOR) {
            up = true;
            currentFloor++;
         } else {
            currentFloor--;
         }
      }

      if (passengerList[currentFloor] > 0) {
         stop();
      }
   }

   /**
   * Print out the current status of the Elevator - number of passengers and
   * current floor.
   *
   * @return Current status of the elevator.
   */
   @Override public final String toString() {
      String output = "Currently " + passengerCount + " passengers on board";
      output += System.getProperty("line.separator");
      output += "Current Floor: " + currentFloor;

      return output;
   }

   /**
   * When the elevator reaches a floor which one or more passengers had
   * indicated was their destination, the passenger count decrements and the
   * passengers are removed. The status is then printed out to the console.
   *
   */
   public final void stop() {
      passengerCount -= passengerList[currentFloor];
      passengerList[currentFloor] = 0;

      System.out.println("Stopping on floor " + currentFloor);
      System.out.println(toString());
   }

   /**
   * Moves the elevator in the direction it is currently going. If it hits
   * the top or bottom floor it reverses direction.
   *
   * @param floor The floor the passeneger is boarding from.
   */
   public final void boardPassenger(final int floor) {
      if (floor >= BOTTOM_FLOOR && floor <= TOP_FLOOR) {
         passengerList[floor]++;
         passengerCount++;
      }
   }

   /**
   * Add three passengers and run the elevator up and down the building to
   * ensure the passengers get off correctly.
   *
   * @param args Arguments passed into the application.
   */
   public static void main(final String [] args) {

       Elevator myElevator = new Elevator();
       myElevator.boardPassenger(FIRST_DESTINATION);
       myElevator.boardPassenger(SECOND_DESTINATION);
       myElevator.boardPassenger(THIRD_DESTINATION);

       System.out.println(myElevator);

       for (int i = 0; i < RUN_TOTAL; i++) {
           myElevator.move();
        }
    }
}
