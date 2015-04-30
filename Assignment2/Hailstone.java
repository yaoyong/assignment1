/*
 * File: Hailstone.java
 * Name: Hail Stone
 * Section Leader: Yong Yao
 * --------------------
 * This file is the solving of the Hailstone problem.
 * Pick some positive integer and call it n.
 * If n is even, divide it by two.
 * If n is odd, multiply it by three and add one.
 * Continue this process until n is equal to one
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		/* You fill this in */
		int i=0; //counter for steps it takes to reach one
		int  n = readInt("Enter a number: ");
		while (true){
		if (n == 1) break;
		if (n%2 == 0){
			println(n + " is even, so I take half: " + (n/2));
			n= n/2;
		}else{
			println(n + " is odd, so I make 3n+1: " + ((3*n)+1));
			n= (3*n)+1;
		}
		i++; //counter increases each time through loop    
		}
		println("The process took " + i + " to reach 1.");
	}
}

