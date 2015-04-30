/*
 * File: PythagoreanTheorem.java
 * Name: Pythagorean theorem   
 * Section Leader: Yong Yao
 * -----------------------------
 * This file is the solving of the PythagoreanTheorem problem.
 * It will make you input two numbers then you can get its value 
 * according to pythagorean theorem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		/* You fill this in */
		message_of_welcom();
		user_input();
	}
	// Displaying the message of welcome.
	private void message_of_welcom() {
		println( "Enter values to compute the Pythagorian theorem" );
	}
	// Asking you input numbers.
	private void user_input() {
		int a = readInt ("a:"); //asks user to enter an integer for a
		int b = readInt ("b:"); //asks user to enter an integer for b
		double x = (double)a; // converts variable "a" from an integer to a double 
		double c = Math.sqrt((x*x) + (b*b)); //calculates square root
		println("c:"+ c); //displays value as a double
	}
}
