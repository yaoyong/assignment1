/*
 * File: Hangman.java
 * File name: Hangman game
 * Author: Yong Yao
 * ------------------
 * This program will eventually play the Haoht, while you guess wrong you will get a warning,
 *lose one chance and draw one part of one man till you lose all the game, you will draw a whole man.
 * while you are right it will display where the word you guess in the right letter. Finally if you 
 * guess all the words of the letter, you will win.
 * 
 * * * Total grade: 85

 * How to grade:

 * 1. Must run correct (60%): 50

 * 2. Proper Comments: file comment and function comment (10%): 10

 * 3. Follow the style guideline (10%): 7

 * 4. A right "top-down" Decomposition (20%): 18

 * 5. If late one-day, reduce 10%。
 * run correctly(60):
 * format(10):
 * comment(10):
 */
 
import acm.graphics.*;
import acm.program.*;
import acm.util.*;
 
import java.awt.*;
 
public class Hangman extends ConsoleProgram {
 
	private HangmanLexicon hangman_Words;
	
	private HangmanCanvas hangman_Canvas;
	
	private RandomGenerator ren_Gen = RandomGenerator.getInstance();
	
	/** Tracks the number of guesses the player has */
	private int guess_Counter = 8;
	
	//run the game
	public void run() {
		set_Game();
		play_Game();
	}
	
	//This is the word being guessed
	private String hidden_Word;
	
	//This is the secret word
	private String word = pick_Word();
	
	//This is the latest character entered by the user
	private char ch;
	
	//This string keeps track of all the incorrect guessed letters
	private String incorrect_Letters = "";
	
	/*Set up the game by displaying the welcome message, 
	 *how many letters there are in the word, 
	 *and how many guesses the user has
	 */
	private void set_Game() {
		hangman_Canvas.reset();
		hidden_Word = show_Number_Of_Letters();
		hangman_Canvas.display_Word(hidden_Word);
		println("Welcome to Hangman!");
		println("The word now looks like this: " + hidden_Word);
		println("You have " + guess_Counter + " guesses left.");
	}
	
	//Generates a random word selected from the HangmanLexicon
	private String pick_Word() {
		hangman_Words = new HangmanLexicon();
		int random_Word = ren_Gen.nextInt(0, (hangman_Words.getWordCount() - 1)); 
		String picked_Word = hangman_Words.getWord(random_Word);
		return picked_Word;
	}
	
	//Shows how many letters there are in the word as part of game setup
	private String show_Number_Of_Letters() {
		String result = "";
		for(int i = 0; i< word.length(); i++) {
			result = result + "-";
		}
		return result;
	}
	
	private void play_Game() {
		while(guess_Counter > 0) {
			String get_Char = readLine("Your guess: ");
			while (true) {
				if(get_Char.length() > 1) {
					get_Char = readLine("You can only guess one letter at a time. Try again: ");
				}
				if(get_Char.length() == 1) break;
			}
			ch = get_Char.charAt(0);
			if(Character.isLowerCase(ch)) {
				ch = Character.toUpperCase(ch);
			}
			letter_Check();
			if (hidden_Word.equals(word)) {
				println("You guessed the word: " + word);
				println("You win");
				break;
			}
			println("The word now looks like this: " + hidden_Word);
			println("You have " + guess_Counter + " guesses left.");
			
		}
		if (guess_Counter == 0) {
			println("You're completely hung.");
			println("The word was:" + word);
			println("You lose.");
		}	
	}
	
	//updates the hiddenWord if the character entered is correct 
	private void letter_Check() {
		//checks to see if the guessed letter is in the word
		if(word.indexOf(ch) == -1) {
			println("There are no " + ch + "'s in the word");
			guess_Counter--;
			incorrect_Letters = incorrect_Letters + ch;
			hangman_Canvas.note_Incorrect_Guess(incorrect_Letters);
		}
		if(word.indexOf(ch) != -1) {
			println("The guess is correct.");
		}
		//goes through each of the letters in the word and checks if it matches the guessed letter, 
		//if it's a match, updates the hidden word to reveal the position of the guessed letter
		for(int i = 0; i < word.length(); i++) {
			if (ch == word.charAt(i)) {
				if (i > 0) {
					hidden_Word = hidden_Word.substring(0, i) + ch + hidden_Word.substring(i + 1);
				}
				if(i == 0) {
					hidden_Word = ch + hidden_Word.substring(1);
				}
				hangman_Canvas.display_Word(hidden_Word);
			}
		}
	}
	
	
	public void init() {
		hangman_Canvas = new HangmanCanvas();
		add(hangman_Canvas);
	}
}