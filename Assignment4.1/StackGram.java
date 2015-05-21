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
public class StackGram extends ConsoleProgram {
	public void run() {
		Rational r = new Rational(1, 2);
		r = raiseToPower(r, 3);
		println("r ^ 3 = " + r);
		}
		private Rational raiseToPower(Rational x, int n){
		Rational result = new Rational(1);
		for (int i = 0; i < n; i++) {
		result = result.multiply(x);
		}
		return result;
		}
}

