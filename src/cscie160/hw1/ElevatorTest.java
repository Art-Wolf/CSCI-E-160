package cscie160.tw2;

import org.junit.*;
import static org.junit.Assert.*;
import cscie160.hw2.Elevator;
import cscie160.hw2.ElevatorFullException;


public class ElevatorTest {

   @Test public void initElevatorPassenger() {
      Elevator myElevator = new Elevator();
      if (myElevator.toString().indexOf("Currently 0 passengers on board") >= 0) {
         assertTrue(true);
                } else {
         assertTrue(false);
      }
   }

   @Test public void initElevatorFloor() {
      Elevator myElevator = new Elevator();
      if (myElevator.toString().indexOf("Current Floor: 1") >= 0) {
                        assertTrue(true);
                } else {
         assertTrue(false);
      }
   }

   @Test public void addPassenger() {
      Elevator myElevator = new Elevator();
      try {
         myElevator.boardPassenger(2);
      } catch (ElevatorFullException excep) {

      }
      if (myElevator.toString().indexOf("Currently 1 passengers on board") >= 0) {
                        assertTrue(true);
                } else {
         assertTrue(false);
      }
   }

   @Test public void changeFloor() {
      Elevator myElevator = new Elevator();
      myElevator.move();
      if (myElevator.toString().indexOf("Current Floor: 2") >= 0) {
                        assertTrue(true);
                } else {
         assertTrue(false);
      }
   }

   @Test public void passengerGetsOff() {
      Elevator myElevator = new Elevator();
      try {
                   myElevator.boardPassenger(2);
                } catch (ElevatorFullException excep) {

      }

      myElevator.move();
                if (myElevator.toString().indexOf("Currently 0 passengers on board") >= 0) {
                        assertTrue(true);
                } else {
         assertTrue(false);
      }
   }
}
