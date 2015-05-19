/*
 * File: HangmanCanvas.java
 * File name: Hangman Canvas 
 * Author: Yong Yao
 * ------------------------
 * This file keeps track of the Hangman display. 
 */
 
import acm.graphics.*;
 
public class HangmanCanvas extends GCanvas {
 
/** Resets the display so that only the scaffold appears */
	public void reset() {
		draw_fold();
	}
	// draw the fold part of hangman
	private void draw_fold() {
		double fold_Top_X = getWidth()/2 - UPPER_ARM_LENGTH;
		double fold_Top_Y = getHeight()/2 - BODY_LENGTH - HEAD_RADIUS*2 - ROPE_LENGTH;
		double fold_Bottom_Y = fold_Top_Y + SCAFFOLD_HEIGHT;
		GLine fold= new GLine (fold_Top_X, fold_Top_Y, fold_Top_X, fold_Bottom_Y);
		add(fold);
		double beam_Right_X = fold_Top_X + BEAM_LENGTH;
		GLine beam = new GLine(fold_Top_X, fold_Top_Y, beam_Right_X, fold_Top_Y);
		add(beam);
		double rope_Bottom_Y = fold_Top_Y + ROPE_LENGTH;
		GLine rope = new GLine (beam_Right_X, fold_Top_Y, beam_Right_X, rope_Bottom_Y);
		add(rope);
	}
 
/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void display_Word(String word) {
		//adds the label with the correctly guessed letters
		double x = getWidth()/4;
		double y = getHeight() - HEAD_RADIUS*2;
		GLabel non_Guessed_Word = new GLabel(word, x, y);
		non_Guessed_Word.setFont("Halvetica-24");
		//removes the latest hidden word and replaces it 
		//with the newest one with the new updated guessed letter
		if (getElementAt(x,y) != null){
			remove(getElementAt(x,y));
		}
		add(non_Guessed_Word);
 
	}
 
/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void note_Incorrect_Guess(String incorrect_Guesses) {
		//adds the label with the incorrect letters
		double x = getWidth()/4;
		double y = getHeight() - HEAD_RADIUS;
		GLabel incorrect_Letters = new GLabel(incorrect_Guesses, x, y);
		//checks to see if there is already a list of incorrect letters in place, 
		//and removes them before adding the new string, with the latest letter
		if(getElementAt(x,y) != null) {
			remove(getElementAt(x,y));
		}
		add(incorrect_Letters);
		//checks how many incorrect guessed letters there are 
		//and draws the next appropriate body part of the hangman
		if(incorrect_Guesses.length() == 1) {
			draw_Head();
		}
		if(incorrect_Guesses.length() == 2) {
			draw_Body();
		}
		if(incorrect_Guesses.length() == 3) {
			draw_Left_Arm();
		}
		if(incorrect_Guesses.length() == 4) {
			draw_Right_Arm();
		}
		if(incorrect_Guesses.length() == 5) {
			draw_Left_Leg();
		}
		if(incorrect_Guesses.length() == 6) {
			draw_Right_Leg();
		}
		if(incorrect_Guesses.length() == 7) {
			draw_Left_Foot();
		}
		if(incorrect_Guesses.length() == 8) {
			draw_Right_Foot();
		}
	}
 
	private void draw_Head() {
		double x = getWidth()/2 - UPPER_ARM_LENGTH + BEAM_LENGTH - HEAD_RADIUS;
		double y = getHeight()/2 - BODY_LENGTH - HEAD_RADIUS*2;
		GOval head = new GOval (x, y, HEAD_RADIUS*2, HEAD_RADIUS*2);
		add(head);
	}
 
	private void draw_Body() {
		double x = getWidth()/2 + UPPER_ARM_LENGTH/2 + HEAD_RADIUS;
		double top_Y = getHeight()/2 - BODY_LENGTH;
		double bottom_Y = top_Y + BODY_LENGTH;
		GLine body = new GLine (x, top_Y, x, bottom_Y);
		add(body);
	}
 
	private void draw_Left_Arm() {
		double arm_Start_X = getWidth()/2 + UPPER_ARM_LENGTH/2 + HEAD_RADIUS;
		double arm_End_X = getWidth()/2 + UPPER_ARM_LENGTH/2 + HEAD_RADIUS - UPPER_ARM_LENGTH;
		double upper_Arm_Height_Y = getHeight()/2 - BODY_LENGTH + ARM_OFFSET_FROM_HEAD;
		GLine upper_Left_Arm = new GLine (arm_Start_X, upper_Arm_Height_Y, arm_End_X, upper_Arm_Height_Y);
		add(upper_Left_Arm);
		double lower_Arm_End_Y = upper_Arm_Height_Y + LOWER_ARM_LENGTH;
		GLine lower_Left_Arm = new GLine (arm_End_X, upper_Arm_Height_Y, arm_End_X, lower_Arm_End_Y);
		add(lower_Left_Arm);
	}
 
	private void draw_Right_Arm() {
		double arm_Start_X = getWidth()/2 + UPPER_ARM_LENGTH/2 + HEAD_RADIUS;
		double arm_End_X = getWidth()/2 + UPPER_ARM_LENGTH/2 + HEAD_RADIUS + UPPER_ARM_LENGTH;
		double upper_Arm_Height_Y = getHeight()/2 - BODY_LENGTH + ARM_OFFSET_FROM_HEAD;
		GLine upper_Right_Arm = new GLine (arm_Start_X, upper_Arm_Height_Y, arm_End_X, upper_Arm_Height_Y);
		add(upper_Right_Arm);
		double lower_Arm_End_Y = upper_Arm_Height_Y + LOWER_ARM_LENGTH;
		GLine lower_Right_Arm = new GLine (arm_End_X, upper_Arm_Height_Y, arm_End_X, lower_Arm_End_Y);
		add(lower_Right_Arm);
	}
 
	private void draw_Left_Leg() {
		double hip_Start_X = getWidth()/2 + UPPER_ARM_LENGTH/2 + HEAD_RADIUS;
		double hip_End_X = hip_Start_X - HIP_WIDTH;
		double hip_Height_Y = getHeight()/2;
		GLine left_Hip = new GLine(hip_Start_X, hip_Height_Y, hip_End_X, hip_Height_Y);
		add(left_Hip);
		double left_Leg_Y = hip_Height_Y + LEG_LENGTH;
		GLine left_Leg = new GLine(hip_End_X, hip_Height_Y, hip_End_X, left_Leg_Y);
		add(left_Leg);
 
	}
 
	private void draw_Right_Leg() {
		double hip_Start_X = getWidth()/2 + UPPER_ARM_LENGTH/2 + HEAD_RADIUS;
		double hip_End_X = hip_Start_X + HIP_WIDTH;
		double hip_Height_Y = getHeight()/2;
		GLine right_Hip = new GLine(hip_Start_X, hip_Height_Y, hip_End_X, hip_Height_Y);
		add(right_Hip);
		double right_Leg_End_Y = hip_Height_Y + LEG_LENGTH;
		GLine right_Leg = new GLine(hip_End_X, hip_Height_Y, hip_End_X, right_Leg_End_Y);
		add(right_Leg);
	}
 
	private void draw_Left_Foot() {
		double foot_Start_X = getWidth()/2 + UPPER_ARM_LENGTH/2 + HEAD_RADIUS - HIP_WIDTH;
		double foot_End_X = foot_Start_X - FOOT_LENGTH;
		double foot_Height_Y = getHeight()/2 + LEG_LENGTH;
		GLine left_Foot = new GLine(foot_Start_X, foot_Height_Y, foot_End_X, foot_Height_Y);
		add(left_Foot);
	}
 
	private void draw_Right_Foot() {
		double foot_Start_X = getWidth()/2 + UPPER_ARM_LENGTH/2 + HEAD_RADIUS + HIP_WIDTH;
		double foot_End_X = foot_Start_X + FOOT_LENGTH;
		double foot_Height_Y = getHeight()/2 + LEG_LENGTH;
		GLine right_Foot = new GLine(foot_Start_X, foot_Height_Y, foot_End_X, foot_Height_Y);
		add(right_Foot);
	}
 
/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
 
}