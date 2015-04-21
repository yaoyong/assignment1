/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;
// This class is for Karel to collect newspaper 
public class CollectNewspaperKarel extends SuperKarel {

	public void run() {
		   moveToNewsPaper();
		   pickUpNewsPaper();
		   returnToStartingPoint();
		   
		 }
		
	// Karel move to the newspaper	
	 private void moveToNewsPaper() {
		  turnRight();
		  move();
		  turnLeft();
		  for (int i=0; i<3; i++) {    
			  	move();   
			  }     
			    
		  } 
		  
		 //Karel picks up the newspaper
		 private void pickUpNewsPaper() {
		  pickBeeper();
		 
		 }
		   
		 //Karel returns to original position
		 private void returnToStartingPoint() {
		  turnAround();
		  for (int i=0; i<3; i++) {    
			  	move();   
			  }     
			  turnRight();   
			  move();   
			  turnRight();    
		 	}
		 }


