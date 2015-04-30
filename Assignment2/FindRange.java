/*
 * File: FindRange.java
 * Name: Range
 * Section Leader: Yong Yao 
 * --------------------
 * This file is the solving of the FindRange problem.
 *If enters only one value before the sentinel, the program will report
  that value as both the largest and smallest.
 *If enters the sentinel on the very first input line, then no values have been
  entered, and it will display a message to that effect.
 */

import acm.program.*;
/* FindRange finds the smallest and the largest
* in a list of integers entered by the user
* and stops with a sentinel value of 0.
*/
public class FindRange extends ConsoleProgram {
	private static final int SENTINEL = 0;

	public void run() {
		println("This program finds the largest and smallest numbers.");
		int Firstnumber=readInt("?");
		if (Firstnumber==SENTINEL){
			println("No values have been entered.");
		}
		else{

			int largestNumber= Firstnumber;
			int smallestNumber= Firstnumber;
			while (true) {
				int number = readInt("?");        
				if (number == SENTINEL) break;
				if (number != 0 && number<=smallestNumber){
					smallestNumber = number;
				}
				if (number>=largestNumber){
					largestNumber=number;
				}
			}
			println("This program finds the largest and smallest numbers.");
			println("Largest:" + largestNumber );
			println("Smallest:" + smallestNumber );    
		}
	}
}

