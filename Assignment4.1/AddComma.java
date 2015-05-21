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
public class AddComma extends ConsoleProgram {
	public void run() {
		while (true) {
		String digits = readLine("Enter a numeric string: ");
		if (digits.length() == 0) break;
		println(addCommasToNumericString(digits));
		}
		}
	private String addCommasToNumericString(String digits) {
		String result = "";
		int len = digits.length();
		int nDigits = 0;
		for (int i = len - 1; i >= 0; i--) {
		result = digits.charAt(i) + result;
		nDigits++;
		if (nDigits % 3 == 0 && i > 0) {
		result = "," + result;
		}
		}
		return result;
		}
}

