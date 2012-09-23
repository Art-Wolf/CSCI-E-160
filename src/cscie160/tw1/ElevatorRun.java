package cscie160.tw1;

import cscie160.hw1.Elevator;

/**
* Test the java class Elevator.
* 
* @author	John Doyle
* @version	1.0
*/
public class ElevatorRun {

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
