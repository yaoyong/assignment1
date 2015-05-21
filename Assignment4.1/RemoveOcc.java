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
public class RemoveOcc extends ConsoleProgram {
	public void run() {
		
		println(removeAllOccurrences("This is a test", 't'));
		println(removeAllOccurrences("Summer is here!", 'e')); 
		println(removeAllOccurrences("---0---", '-'));
		}
			
	private String removeAllOccurrences(String str, char ch) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
		if (str.charAt(i) != ch) result += str.charAt(i);
		}
		return result;
		}
}

