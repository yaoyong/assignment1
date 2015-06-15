/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */
 
import acm.io.*;
import acm.program.*;
import acm.util.*;
import java.util.*;
 
public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	/* Private instance variables */
	private int n_Players; //number of players
	private String[] player_Names; //an array of Player names
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator(); //random number generator
	private int[] dice_rolled = new int [N_DICE]; //stores the most recently rolled dice numbers
	private int[][] category_Scores; //stores the score for each category for each player
	private int category; //selected category
	private int[][] selected_Categories; //stores the already selected categories
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		n_Players = dialog.readInt("Enter number of players");
		while(true) {
			if(n_Players <= MAX_PLAYERS) break;
			n_Players = dialog.readInt("You can only enter up to " + MAX_PLAYERS +" number of players. Enter number of players");
		}
		player_Names = new String[n_Players];
		category_Scores =  new int [n_Players + 1][N_CATEGORIES+1];
		selected_Categories = new int[n_Players+1][N_CATEGORIES+1];
		for (int i = 1; i <= n_Players; i++) {
			player_Names[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), player_Names);
		play_Game();
	}
 
	private void play_Game() {
		for(int i = 0; i < N_SCORING_CATEGORIES; i++) {
			for(int j=1; j <= n_Players; j++) {
				init_First_Roll(j);
				second_and_Third_Roll(j);
				select_Category(j);
			}
		}
		calculate_Results();
		select_Winner();
	}
 
	
	/* In the beginning of a players turn, 
	 * the player clicks on "Roll Dice", 
	 * the Dice results are displayed and 
	 * stored in the diceResults array */
	private void init_First_Roll(int player_Number) {
		for(int i = 0; i < N_DICE; i++) {
			int dice_Roll = rgen.nextInt(1,6);
			dice_rolled[i] = dice_Roll;
		}
		
		display.printMessage(player_Names[player_Number - 1] + "'s turn! Click the " + "\"Roll Dice\" " + "button to roll the dice.");
		display.waitForPlayerToClickRoll(player_Number);
		display.displayDice(dice_rolled);
	}
	//here
	/* For the second and third roll, 
	 * the player selects the dice he or she wants to re-roll, 
	 * the selected dice are re-rolled, and the new 
	 * dice values are displayed and stored in the diceResults array */
	private void second_and_Third_Roll(int playerNumber) {
		for (int i = 0; i < 2; i++) {
			display.printMessage("Select the dice you wish to re-roll and click " + "\"Roll Again\"");
			display.waitForPlayerToSelectDice();
			for(int j = 0; j < N_DICE; j++) {
				if(display.isDieSelected(j) == true) {
					int dieRoll = rgen.nextInt(1,6);
					dice_rolled[j] = dieRoll;
				}
			}
			display.displayDice(dice_rolled);
		}
	}
 
	/* Pre-condition: The player has rolled the dice three times. 
	 * The player selects the category for the dice. 
	 * The player cannot select a category that he/she already chose in a previous turn.*/
	private void select_Category(int player_Number) {
		while(true) {
			display.printMessage("Select a category for this roll");
			category = display.waitForPlayerToSelectCategory();
			if(selected_Categories[player_Number][category] == 0) {
				calculate_Category_Score(player_Number);
				break;
			}
		}	
	}
	
	/* Pre-condition: The user selected a category he/she has not previously selected.
	 * Assigns 1 to the selectedCategories array to keep track of selected categories. 
	 * Checks to see if the selected category matches the dice configuration, 
	 * and calculates the score. If it does not match, assigns the score of 0. 
	 * Post-condition: Shows the score category and total score in the scorecard. 
	 */
	private void calculate_Category_Score(int player_Number) {
		selected_Categories[player_Number][category] = 1;
		int total_Score;
		if(check_Category(dice_rolled, category) == true) {
			set_Category_Score(player_Number, category);
			int score = category_Scores[player_Number][category];
			display.updateScorecard(category, player_Number, score);
			calculate_Total_Scores(player_Number);
			total_Score = category_Scores[player_Number][TOTAL];
			display.updateScorecard(TOTAL, player_Number, total_Score);
			}
		else {
			category_Scores[player_Number][category] = 0;
			display.updateScorecard(category, player_Number, 0);
			calculate_Total_Scores(player_Number);
			total_Score = category_Scores[player_Number][TOTAL];
			display.updateScorecard(TOTAL, player_Number, total_Score);
		}
	}
	
	/*sets the score in the categoryScores matrix for each player 
	based on the scoring category they chose after rolling the dice*/ 
	private void set_Category_Score(int player_Number, int category) {
		int score = 0; 
		if(category >= ONES && category <= SIXES) {
			for(int i = 0; i < N_DICE; i++) {
				 if(dice_rolled[i] == category) {
					 score += category;
				 }
			 }
		}
		else if(category == THREE_OF_A_KIND || category == FOUR_OF_A_KIND || category == CHANCE) {
			for(int i = 0; i<N_DICE; i++) {
				score += dice_rolled[i];
			}
		}
		else if(category == FULL_HOUSE) {
			score = 25;
		}
		else if(category == SMALL_STRAIGHT) {
			score = 30;
		}
		else if(category == LARGE_STRAIGHT) {
			score = 40;
		}
		else if(category == YAHTZEE) {
			score = 50;
		}
		category_Scores[player_Number][category] = score;
	}
	
	
	/*sets the total scores for each player */
	private void calculate_Total_Scores(int player_Number) {
		int upper_Score = 0;
		int lower_Score = 0;
		int total_Score = 0;
		for(int i = ONES; i <= SIXES; i++) {
			upper_Score += category_Scores[player_Number][i];
			}
		for(int i = THREE_OF_A_KIND; i <= CHANCE; i++) {
			lower_Score += category_Scores[player_Number][i];
			}
		total_Score = upper_Score + lower_Score; 
		category_Scores[player_Number][UPPER_SCORE] = upper_Score; 
		category_Scores[player_Number][LOWER_SCORE] = lower_Score;
		category_Scores[player_Number][TOTAL] = total_Score; 
	}
	
	/* Pre-condition: All players have completed the game. 
	 * Calculates and displays the Upper Score, Upper Bonus, and LowerScore */
	private void calculate_Results() {
		for(int i = 1; i <= n_Players; i++) {
			display.updateScorecard(UPPER_SCORE, i, category_Scores[i][UPPER_SCORE]);
			display.updateScorecard(LOWER_SCORE, i, category_Scores[i][LOWER_SCORE]);
			if(category_Scores[i][UPPER_SCORE] >= 63) {
				category_Scores[i][UPPER_BONUS] = 35;
			}
			display.updateScorecard(UPPER_BONUS, i, category_Scores[i][UPPER_BONUS]);
			category_Scores[i][TOTAL] = category_Scores[i][TOTAL] + category_Scores[i][UPPER_BONUS];
			display.updateScorecard(TOTAL, i, category_Scores[i][TOTAL]);
		}
	}
	
	/* Pre-condition: The game has ended, and all the final scores have been added up. 
	 * Calculates which player has the highest score and what the highest score is 
	 * and prints that information in a message at the very end of the game.*/
	private void select_Winner() {
		int winning_Score = 0;
		int winning_Player_Number = 0;
		for(int i = 1; i<=n_Players; i++) {
			int x = category_Scores[i][TOTAL];
			if( x > winning_Score) {
				winning_Score = x;
				winning_Player_Number = i - 1;
			}
		}
		display.printMessage("Congratulations, " + player_Names[winning_Player_Number] + ", you're the winner with a total score of " + winning_Score + "!");
	}
 
	/* Pre-condition: The player has finished rolling the dice and selects a category. 
	 * This method returns true if the selected category matches 
	 * to the actual category correctly, and false if it does not match. */
	private boolean check_Category(int[] dice, int category) {
		boolean category_Match = false;
		if(category >= ONES && category <= SIXES || category == CHANCE) {
			category_Match = true;
		}
		else {
			
			//creates an array for each possible dice value (1-6)
			ArrayList <Integer> ones = new ArrayList<Integer>();  
			ArrayList <Integer> twos = new ArrayList<Integer>(); 
			ArrayList <Integer> threes = new ArrayList<Integer>(); 
			ArrayList <Integer> fours = new ArrayList<Integer>(); 
			ArrayList <Integer> fives = new ArrayList<Integer>(); 
			ArrayList <Integer> sixes = new ArrayList<Integer>();
			
			/*goes through each rolled die and puts 1 as a place-holder into the appropriate ArrayList
			* e.g. if the first die value is 1, then 1 is added to the ones ArrayList or
			* if the second die value is 5, then 1 is added to the fives ArrayList*/
			for(int i = 0; i < N_DICE; i++) {
				if(dice[i] == 1) {
					ones.add(1);
				}
				else if(dice[i] == 2) {
					twos.add(1);
				}
				else if(dice[i] == 3) {
					threes.add(1);
				}
				else if(dice[i] == 4) {
					fours.add(1);
				}
				else if(dice[i] == 5) {
					fives.add(1);
				}
				else if(dice[i] == 6) {
					sixes.add(1);
				}
			}
			if(category == THREE_OF_A_KIND) {
				if(ones.size() >= 3 || twos.size() >= 3 || threes.size() >= 3 || fours.size() >= 3 || fives.size() >= 3 || sixes.size() >= 3) {
					category_Match = true;
				}
			}	
			else if(category == FOUR_OF_A_KIND) { 
				if(ones.size() >= 4 || twos.size() >= 4 || threes.size() >= 4 || fours.size() >= 4 || fives.size() >= 4 || sixes.size() >= 4) {
					category_Match = true;
				}
			}
			else if(category == YAHTZEE) {
				if(ones.size() == 5 || twos.size() == 5 || threes.size() == 5 || fours.size() == 5 || fives.size() == 5 || sixes.size() == 5) {
					category_Match = true;
				}
			}
			else if(category == FULL_HOUSE) {
				if(ones.size() == 3 || twos.size() == 3 || threes.size() == 3 || fours.size() == 3 || fives.size() == 3 || sixes.size() == 3) {
					if(ones.size() == 2 || twos.size() == 2 || threes.size() == 2 || fours.size() == 2 || fives.size() == 2 || sixes.size() == 2) {
						category_Match = true;
					}
				}
			}	
			else if(category == LARGE_STRAIGHT) { 
				if(ones.size() == 1 && twos.size() == 1 && threes.size() == 1 && fours.size() == 1 && fives.size() == 1){
					category_Match = true;
				}
				else if(twos.size() == 1 && threes.size() == 1 && fours.size() == 1 && fives.size() == 1 && sixes.size() == 1) {
					category_Match = true;
				}
			}
			else if(category == SMALL_STRAIGHT) { 
				if(ones.size() >= 1 && twos.size() >= 1 && threes.size() >= 1 && fours.size() >= 1) {
					category_Match = true;
				}
				else if(twos.size() >= 1 && threes.size() >= 1 && fours.size() >= 1 && fives.size() >= 1) {
					category_Match = true;
				}
				else if(threes.size() >= 1 && fours.size() >= 1 && fives.size() >= 1 && sixes.size() >= 1) {
					category_Match = true;
				}
			}
		}
		return category_Match;
	}
	
}