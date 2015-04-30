/*
 * File: Pyramid.java
 * Name: Pyramid
 * Section Leader: Yong Yao
 * ------------------
 * This file is the solving of the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

@SuppressWarnings("unused")
public class Pyramid extends GraphicsProgram {

	private static final long serialVersionUID = 1L;

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	//set the size of the canvas
	public void init() {
		//this.setName("ProgramHierarchy");
		this.setSize(700, 500);
		
	}
	public void run() {
		/* You fill this in. */
		int n=14,N=14,xx,yy,r;
		//find x,y coordinates of first box
		int x = (getWidth()-BRICK_WIDTH*BRICKS_IN_BASE)/2-50; 
		int y = getHeight()-BRICK_HEIGHT;
		
		//draw the whole pyramid in loop
		for(r=1; r<=N; r++){
			x=x+BRICK_WIDTH/2;
            y=y-BRICK_HEIGHT;
            n=N+1-r; 
            //draw boxes of the row i 
    		for(int i=1; i<=n; i++){
    			xx=x+i*BRICK_WIDTH;
    			yy=y;
    		    GRect rect = new GRect(xx, yy, BRICK_WIDTH, BRICK_HEIGHT);
    		      rect.setVisible(isVisible());
    		     // rect.setFilled(true);
    		     // rect.setColor(Color.RED);
    		      add(rect);
    		}
    	}    
	}
}

