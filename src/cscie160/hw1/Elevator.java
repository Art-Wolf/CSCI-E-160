package cscie160.hw1;

/**
* Track passengers as they get on the elevator and off at their destination.
* 
* @author	John Doyle
* @version	1.0
*/
public class Elevator {
	/** The total capacity for the elevator - logic not implemented. */
	public final int CAPACITY = 10;
	
	/** The top floor the elevator goes to. */
	public final int TOP_FLOOR = 7;
	
	/** The bottom floor the elevator goes to. */
	public final int BOTTOM_FLOOR = 1;
	
	private int CURRENT_FLOOR = 1;
	private boolean UP = true;
	
	private int PASSENGER_LIST[];
	private int PASSENGER_COUNT = 0;
	private int TARGET_FLOOR = 1;
	
	/**
	* Elevator constructor that creates the floor array to track passenger destinations. 
	* 
	*/
	public Elevator() {
		PASSENGER_LIST = new int[TOP_FLOOR + 1];
			
		for ( int i = BOTTOM_FLOOR; i <= TOP_FLOOR; i++) {
			PASSENGER_LIST[i] = 0;
		}	
	}
	
	/**
	* Moves the elevator in the direction it is currently going. If it hits
	* the top or bottom floor it reverses direction.
	* 
	*/
	public void move() {
		if (UP) {
			if (CURRENT_FLOOR == TOP_FLOOR) {
				UP = false;
				CURRENT_FLOOR--;
			} else {
				CURRENT_FLOOR++;
			}
		} else {
			if (CURRENT_FLOOR == BOTTOM_FLOOR) {
				UP = true;
				CURRENT_FLOOR++;
			} else {
				CURRENT_FLOOR--;
			}
		}
		
		if(PASSENGER_LIST[CURRENT_FLOOR] > 0) {
			stop();
		}
	}
	
	/**
	* Print out the current status of the Elevator - number of passengers and current
	* floor.
	* 
	*/
	@Override public String toString() { 
		String output = "Currently " + PASSENGER_COUNT + " passengers on board";
		output += System.getProperty("line.separator");
		output += "Current Floor: " + CURRENT_FLOOR;
		
		return output;
	}
	
	/**
	* When the elevator reaches a floor which one or more passengers had indicated was 
	* their destination, the passenger count decrements and the passengers are removed.
	* The status is then printed out to the console.
	* 
	*/
	public void stop() {
		PASSENGER_COUNT -= PASSENGER_LIST[CURRENT_FLOOR];
		PASSENGER_LIST[CURRENT_FLOOR] = 0;
	
		System.out.println("Stopping on floor " + CURRENT_FLOOR);	
		System.out.println(toString());
	}
	
	/**
	* Moves the elevator in the direction it is currently going. If it hits
	* the top or bottom floor it reverses direction.
	*
	*/
	public void boardPassenger(int floor) {
		if(floor >= BOTTOM_FLOOR && floor <= TOP_FLOOR) {
			PASSENGER_LIST[floor]++;
			PASSENGER_COUNT++;
		}
	}

	/**
        * Add three passengers and run the elevator up and down the building to
        * ensure the passengers get off correctly.
        *
        */
        public static void main(String [] args) {
                Elevator myElevator = new Elevator();
                myElevator.boardPassenger(2);
                myElevator.boardPassenger(2);
                myElevator.boardPassenger(3);

                System.out.println(myElevator);

                for(int i = 0; i < 12; i++) {
                        myElevator.move();
                }
        }
}
