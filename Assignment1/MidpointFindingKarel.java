/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	public void run() {
		  layBeepers(); //Put one beeper in each square in the row
		  cleanUpBeepers(); //Alternately remove one beeper from each end. This way Karel will
		         //be removing the beeper at the middle square last
		  putBeeper(); //Put one beeper back in the middle square
		 
		}
		 
		//Put one beeper in each square of the row
		public void layBeepers(){
		  putBeeper();
		  while (frontIsClear()){
		   move();
		   putBeeper();
		  }
		  turnAround(); //Turn around so that Karel is facing the center
		}
		 
		//Only move if front is clear
		public void moveIfSafe(){
		  if(frontIsClear()){
		   move();
		  }
		}
		 
		//Pre-condition: Karel is facing west or east
		//Post-condition: Karel is facing south
		public void turnSouth(){
		  if(facingEast()){
		   turnRight();
		  }else{
		   turnLeft();
		  }
		  
		}
		 
		//Turn around, move one space (if possible), and then pick up the beeper (if possible)
		public void turnAroundAndPickBeeper(){
		  turnAround();
		  moveIfSafe();
		  if (beepersPresent()){
		   pickBeeper();
		  }
		}
		 
		//The purpose of this method is basically to let Karel pick up a beeper from the opposite end
		public void pickUpEndBeeper(){
		  while (beepersPresent()){//Karel keeps walking until there isn't a beeper at his
		                        //location or until he reaches the wall
		   if (frontIsClear()){
		    move();
		   }
		   else{
		    pickBeeper();
		    turnSouth();//Turning south is a signal for "already picked up a beeper)
		   }
		  }
		  
		  if (frontIsClear()){
		   turnAroundAndPickBeeper();//Turn around and pick up the beeper located at the end
		  }
		  else{
		   if (facingSouth()) //If Karel has already picked up a beeper, then he will
		                   //simply change direction
		   {
		    faceCenter();
		   }
		   else{
		    turnAroundAndPickBeeper();//If not, he will pick up the beeper at the end
		   }
		    
		  }
		   
		}
		 
		//Let Karel face the center
		public void faceCenter(){
		  if (rightIsClear()){
		   turnRight();
		  }else{
		   turnLeft();
		  }
		 
		  
		}
		 
		//Karel alternately picks up a beeper from each end. Eventually, Karely will stop at the
		//middle square
		public void cleanUpBeepers(){
		  while (notFacingSouth()){
		   
		   pickUpEndBeeper();
		   moveIfSafe();
		   if (noBeepersPresent()){//If this is satisfied, it means that all beepers have been
		                        //cleared
		    //Move back to the middle square:
		    turnAround();
		    moveIfSafe();
		    turnSouth();//Since no variable can be used, facing south is the only way
		                //to break out of the while loop
		   }
		  }
		  
		}

}
