/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;


public class CheckerboardKarel extends SuperKarel {

	public void run() {
		 turnLeft();
		  finishColumn();
		  while (frontIsClear()){ //If there's a next column, then finish that column
		   goToNextColumn();
		   if (frontIsClear()){ //In case that the world is a one column-world
		    finishColumn();
		   }		    
		  }
		    
		 }
		  
		 public void finishColumn(){
			 putBeeper();		  
		  while (frontIsClear()){
		   move();
		   if (frontIsClear()){
		    move();
		    putBeeper();
		   }
		  }
		  faceEast();
		 }

		 public void faceEast (){
		  if (facingNorth()){
		   turnRight();
		  }
		  else{
		   if (facingSouth()){
		    turnLeft();
		   }
		  }
		 }
		 		                    
		 public void turn (){
		  if (rightIsClear()){
			  turnRight();
		  }else{
		   turnLeft();
		  }
		 }

		  
		 public void goToNextColumn(){
		  if (noBeepersPresent()){
		   move();
		   turn();
		  }
		  else{
		   move();
		   turn();   
		   if (frontIsClear()) {
		    move();
		   }
		   else{
		    faceEast();
		    if (frontIsClear()) {
		     move();
		    }		     
		   }		     
		  }
		 }


}

