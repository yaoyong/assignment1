/*
 * File: Target.java
 * Name: Target
 * Section Leader: Yong Yao
 * -----------------
 * This file is the solving of the Target problem.
 * First, draw the outer circle and filled in red color
 * Second, draw the middle circle and filled in white color
 * Third, draw the inner circle and filled in red color
 * In order to finish steps above, also, it will find their coordinates.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {
	private static final double CIRCLE_RADIUS1 = 72;
	private static final double CIRCLE_RADIUS2 = 72*0.65;
	private static final double CIRCLE_RADIUS3 = 72*0.3;

	//set the size of the canvas
	public void init() {
		//this.setName("ProgramHierarchy");
		this.setSize(400, 300);
		
	}
	
	double x1=70, y1=70, x2=70*0.65, y2=70*0.65, x3=70*0.3, y3=70*0.3;
	double xx1=100,yy1=100, xx2=100+(x1-x2)/2,yy2=100+(y1-y2)/2, 
			xx3=xx2+(x2-x3)/2,yy3=yy2+(y2-y3)/2;
	public void run() {
		//draw the outer circle
		GOval goval1 = new GOval(circlex1(),circley1(), CIRCLE_RADIUS1, CIRCLE_RADIUS1);
		goval1.setFilled(true);
		goval1.setColor(Color.RED);
		add(goval1);
		
		//draw the middle circle
		GOval goval2 = new GOval(circlex2(),circley2(), CIRCLE_RADIUS2, CIRCLE_RADIUS2);
		goval2.setFilled(true);
		goval2.setColor(Color.WHITE);
		add(goval2);
		
		//draw the inner circle
		GOval goval3 = new GOval(circlex3(),circley3(), CIRCLE_RADIUS3, CIRCLE_RADIUS3);
		goval3.setFilled(true);
		goval3.setColor(Color.RED);
		add(goval3);
	}
	//find coordinates of the outer circle
	private double circlex1() {
		double x = (getWidth()-CIRCLE_RADIUS1)/2;
		return x;
	}
	private double circley1() {
		double x = (getHeight()-CIRCLE_RADIUS1)/2;
		return x;
	}
	//find coordinates of the middle circle
	private double circlex2() {
		double x = circlex1()+(CIRCLE_RADIUS1-CIRCLE_RADIUS2)/2;
		return x;
	}
	private double circley2() {
		double x = circley1()+(CIRCLE_RADIUS1-CIRCLE_RADIUS2)/2;
		return x;
	}
	//find coordinates of the inner circle
	private double circlex3() {
		double x = circlex2()+(CIRCLE_RADIUS2-CIRCLE_RADIUS3)/2;
		return x;
	}
	private double circley3() {
		double x = circley2()+(CIRCLE_RADIUS2-CIRCLE_RADIUS3)/2;
		return x;
	}
}
