/*
 * File: ProgramHierarchy.java
 * Name: Program Hierarchy
 * Section Leader: Yong Yao
 * ---------------------------
 * This file solves the ProgramHierarchy problem. 
 * Displaying the hierarchy of programming with rect, line and  label in the methods of graphic programming.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
	private static final int BOX_WIDTH = 200;
	private static final int BOX_HEIGHT = 100;

	//set the size of the canvas
	public void init() {
		//this.setName("ProgramHierarchy");
		this.setSize(1000, 700);
		
	}
	
	//Draw boxes, lines and labels, then centered the labels in boxes.
	public void run() {
		GRect box1 = new GRect(Box1_x(), Box1_y(), BOX_WIDTH, BOX_HEIGHT);
		add (box1);
		//System.out.print(Box1_x());
		//boxes
		GRect box2 = new GRect(Box2_x(), Box234_y(), BOX_WIDTH, BOX_HEIGHT);
		add (box2);
		GRect box3 = new GRect(Box3_x(), Box234_y(), BOX_WIDTH, BOX_HEIGHT);
		add (box3);
		GRect box4 = new GRect(Box4_x(), Box234_y(), BOX_WIDTH, BOX_HEIGHT);
		add (box4);
		//lines
		GLine line1 = new GLine (Box1LineX(), Box1LineY(), Box2LineX(), Box234_y());
		add (line1);
		GLine line2 = new GLine (Box1LineX(), Box1LineY(), Box1LineX(), Box234_y());
		add (line2);
		GLine line3 = new GLine (Box1LineX(), Box1LineY(), box4LineX(), Box234_y());
		add (line3);
		//labels
		add (center_Label("Program", Box1_x(), Box1_y()));
		add (center_Label("GraphicsProgram", Box2_x(), Box234_y()));
		add (center_Label("ConsoleProgram", Box3_x(), Box234_y()));
		add (center_Label("DialogProgram", Box4_x(), Box234_y()));
	}
	
	//find midpoint of canvas midx,midy
	private int midx() {
		int x = getWidth()/2;
		return x;
	}

	private int midy(){
		int y = getHeight()/2;
		return y;
	}

	//find x,y coordinates of first box
	private int Box1_x() {
		int x = (midx()-BOX_WIDTH/2);
		return x;
	}

	private int Box1_y() {
		int y = midy() - (3*(BOX_HEIGHT/2));
		return y;
	}

	// the gaps between boxes along x and y axis
	private int xGap(){
		return BOX_HEIGHT/2;
	}
	private int yGap(){
		return BOX_HEIGHT;
	}

	//find x coordinates of second box
	private int Box2_x() {
		int x = Box1_x()-BOX_WIDTH-xGap();
		return x;
	}

	//find x coordinates of third box (same as the first one)
	private int Box3_x() {
		int x = Box1_x();
		return x;
	}

	//find x coordinates of fourth box
	private int Box4_x() {
		int x = Box1_x() + BOX_WIDTH +xGap();
		return x;
	}

	//find y coordinates of second to fourth boxes, they are the same.
	private int Box234_y() {
		int y = Box1_y() + BOX_HEIGHT + yGap();
		return y;
	}

	////LINES

	//find coordinates for the three lines, first get the bottom of the middle of the first box
	// then the top middle x coordinates of the second and fourth boxes,
	//calculated by adding BOX_WIDTH/2 to the x coordinates
	//the y coordinate is the same for the second to fourth boxes: yBox234()
	// and the x coordinate is the same for the 1st and 3rd boxes

	private int Box1LineX(){
		int x = Box1_x() + BOX_WIDTH/2;
		return x;
	}

	private int Box1LineY(){
		int y = Box1_y() + BOX_HEIGHT;
		return y;
	}

	private int Box2LineX(){
		int x = Box2_x() + BOX_WIDTH/2;
		return x;
	}

	private int box4LineX(){
		int x = Box4_x() + BOX_WIDTH/2;
		return x;
	}

	////LABELS

	///labels centered in middle of each box.So find the coordinates relative to their boxes.
	//Minus the length of the string from the width of the box and half it and add it to the x coordinate of the box.
	//for y coordinate, add the font height to the box height, half it and then add that to the y coordinate of the box.
	private GLabel center_Label(String name, double x, double y){    
		GLabel clabel = new GLabel (name);
		clabel.setFont("SansSerif-24");
		double cx = x + ((BOX_WIDTH - clabel.getWidth())/2);
		double cy = y + ((BOX_HEIGHT + clabel.getAscent())/2);
		add (clabel, cx, cy);
		return clabel;   
	}
}

