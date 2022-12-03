
/**
 * @author Maksym Loshak @author Vladyslava Rudas
 * 
 * @version 30.11.2022
 * no recent changes 
 */
/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */
import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class BreakoutMain extends GraphicsProgram {


	private static final long serialVersionUID = 1L;
/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
/** The item of GObject paddle (GRect) */
	private static GRect paddle;
	
/** The item of GObject ball (GOval) */
	private static GOval ball;
	
/** Random generating algorithm */
	private static RandomGenerator rgen = RandomGenerator.getInstance();
	
/** Static color of the paddle */
	private static final Color paddleColor = new Color(rgen.nextInt(1, 255), rgen.nextInt(1, 255), rgen.nextInt(1, 255));

/* Method: run() */
/** Runs the BreakoutMain program. */
	public void run() 
	{
		setup();
		addMouseListeners();
	}
/**
 * Sets the size of the console
 * Sets all necessary variables	values
 * Adds all necessary in-game items 
 * @author Maksym Loshak
 */
	public void setup()
	{
		this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		bricks();
		paddle=getPaddle();
		ball=getBall();
		add(paddle, (WIDTH-PADDLE_WIDTH)/2, HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT);
		add(ball, (WIDTH-BALL_RADIUS)/2, (HEIGHT-BALL_RADIUS)/2);
	}
	
	//*************************************************************************************
	
	
	
/**
 * Creates bricks 10x10 (rows x columns) size set in the header.
 * @author Maksym Loshak
 */
	private void bricks()
	{
		for(int i=0; i<NBRICK_ROWS; i++)
		{
			for(int u=0; u<NBRICKS_PER_ROW; u++)
			{
				GRect brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
				//int random = rgen.nextInt(1, 255);
				//int random2 = rgen.nextInt(1, 255);
				//int random3 = rgen.nextInt(1, 255);
				Color brickColor = new Color(255-25*(i+u)/2, 25*(Math.abs((i-u))), 25*(i+u)/2);
				brick.setFilled(true);
				brick.setFillColor(brickColor);
				add(brick, u*(BRICK_WIDTH+BRICK_SEP)+BRICK_SEP/2,BRICK_Y_OFFSET+i*(BRICK_HEIGHT+BRICK_SEP));
			}
		}
		
	}
/**
 * Creates an Object of GObject class (GRect)
 * in-game paddle
 * @author Maksym Loshak
 * @return paddle
 */
	private GRect getPaddle()
	{
		GRect paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setFillColor(paddleColor);
		return paddle;
	}
/**
 * Creates an Object of GObject class (GOval)
 * in-game ball
 * @author Maksym Loshak
 * @return ball
 */
	private GOval getBall()
	{
		GOval ball = new GOval(BALL_RADIUS, BALL_RADIUS);
		ball.setFilled(true);
		ball.setFillColor(Color.cyan);
		return ball;
	}
	
	
	
	
	//***************************************************************************************
	
	
	
/**
 * Validates movement of the paddle in the in-game area due to the movement of the cursor.
 * @author Maksym Loshak
 */
	public void mouseMoved(MouseEvent e)
	{
		remove(paddle);
		paddle=null;
		paddle = getPaddle();
		if(e.getX()<=WIDTH-PADDLE_WIDTH/2&&e.getX()>=PADDLE_WIDTH/2)
			add(paddle, e.getX()-getPaddle().getWidth()/2, HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT);
		else if(e.getX()>WIDTH-PADDLE_WIDTH/2)
			add(paddle, WIDTH-PADDLE_WIDTH, HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT);
		else if(e.getX()<PADDLE_WIDTH/2)
			add(paddle, 0, HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT);
	}
	
	private void ballMovement()
	{
		
	}
	
	
	
	
	
}