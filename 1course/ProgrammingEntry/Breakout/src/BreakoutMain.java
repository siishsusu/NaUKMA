
/**
 * @author Maksym Loshak @author Vladyslava Rudas
 * 
 * @version 04.12.2022
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
	
/** In-game movement delay */
	private static final int DELAY = 6;

/** Number of turns */
	private static final int NTURNS = 3;
	
/** An item of GObject paddle (GRect) */
	private static GRect paddle;
	
/** An item of GObject ball (GOval) */
	private static GOval ball;
	
/** An item of GObject the ball has collided with */
	private static GObject collider;
	
/** An item of GObject showing score of the player */
	private static GLabel score;
	
/** An item of GObject GCompound line */
	private static GCompound header;
	
/** The location of the brick due to the location of ball*/
	private static String brickLocation;
	
/** The speed of the ball by X axis */
	private static double vx;
	
/** The speed of the ball by Y axis */
	private static double vy;
	
/** The counter of score */
	private static int scoreValue=0;
	
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
		while(!gameOver())
		{
			ballMovement();
			collisionCheck();
			pause(DELAY);
		}
		if(gameOver())
			removeAll();
		
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
		score=getScore();
		header=getHeader();
		ballSpeed();
		score.setLabel("Score: "+scoreValue);
		//ballDirection ballMovement = ballDirection.DOWN;
		add(paddle, (WIDTH-PADDLE_WIDTH)/2, HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT);
		add(ball,(WIDTH-BALL_RADIUS)/2, (HEIGHT-BALL_RADIUS)/2);
		add(score, WIDTH-score.getWidth()-10, score.getHeight()-2);
		add(header,0,0);
		
	}
	
	public boolean gameOver()
	{
		return (ball.getY()>=HEIGHT-BALL_RADIUS||scoreValue==1000);
	}
	
	
	
	//---------------------------------------------------------------------------------------------------------
	
	
	
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
				add(brick, u*(BRICK_WIDTH+BRICK_SEP)+BRICK_SEP/2,BRICK_Y_OFFSET+45+i*(BRICK_HEIGHT+BRICK_SEP));
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
	
	private GLabel getScore()
	{
		GLabel score = new GLabel("");
		score.setFont("Times New Roman-36");
		return score;
	}
	
	private GCompound getHeader()
	{
		GCompound header = new GCompound();
		GLine line = new GLine(0,score.getHeight()+5, WIDTH, score.getHeight()+5);
		//header.add(score,  WIDTH-score.getWidth()-100, score.getHeight()+25);
		header.add(line);
		return header;
	}
	
	
	
	
	//---------------------------------------------------------------------------------------------------------
	
	
	
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
	
/**
 * Determines the speed of the ball and sets them in the requested fields.
 * @author Maksym Loshak
 */
	private void ballSpeed()
	{
		vx = rgen.nextDouble(0.5, 1.5); 
		vy = 1.5;
		if (rgen.nextBoolean(0.5)) vx = -vx;
	}
	
/**
 * Creates the ball movement with the set speed, prevents ball from leaving the
 * in-game zone.
 * @author Maksym Loshak
 */
	private void ballMovement()
	{
		ball.move(vx, vy);
		if(ball.getX()<=0 || ball.getX()>=WIDTH-BALL_RADIUS)
			vx=-vx;
		if(ball.getY()<=getHeader().getHeight())
			vy=-vy;
	}

/** Finds the collider for a ball, returns null if there is no colision
 * @author Maksym Loshak
 * @return collider
 */
	private GObject getCollidedObjet()
	{
		GObject collObjLD = getElementAt(ball.getX(), ball.getY()+ball.getHeight()); 
		if(collObjLD!=null)
			return collObjLD;
		GObject collObjRD = getElementAt(ball.getX()+ball.getWidth(), ball.getY()+ball.getHeight());
		if(collObjRD!=null)
			return collObjRD;
		GObject collObjLU = getElementAt(ball.getX(), ball.getY()); 
		if(collObjLU!=null)
			return collObjLU;
		GObject collObjRU = getElementAt(ball.getX()+ball.getWidth(), ball.getY());
		if(collObjRU!=null)
			return collObjRU;
		else
			return null;
	}
	
/** Checks where is the brick located for ball to bounce in the correct direction
 * @author Maksym Loshak
 * @return the side of the ball
 */
	private String brickLocation()
	{
		GObject collObjR1 = getElementAt(ball.getX()+ball.getWidth(), ball.getY()+Math.abs(vy)+0.1);
		GObject collObjR2 = getElementAt(ball.getX()+ball.getWidth(), ball.getY()+ball.getHeight()-Math.abs(vy)-0.1);
		GObject collObjL1 = getElementAt(ball.getX(), ball.getY()+Math.abs(vy)+0.1);
		GObject collObjL2 = getElementAt(ball.getX(), ball.getY()+ball.getHeight()-Math.abs(vy)-0.1);
		GObject collObjU1 = getElementAt(ball.getX()+Math.abs(Math.abs(vy))+0.1, ball.getY());
		GObject collObjU2 = getElementAt(ball.getX()+ball.getWidth()-Math.abs(vy)-0.1, ball.getY());
		GObject collObjD1 = getElementAt(ball.getX()+Math.abs(vy)+0.1, ball.getY()+ball.getHeight());
		GObject collObjD2 = getElementAt(ball.getX()+ball.getWidth()-Math.abs(vy)-0.1, ball.getY()+ball.getHeight());
		if(collObjD1!=null||collObjD2!=null)
			return "DOWN";
		if(collObjR1!=null||collObjR2!=null)
			return "RIGHT";
		if(collObjL1!=null||collObjL2!=null)
			return "LEFT";
		if(collObjU1!=null||collObjU2!=null)
			return "UP";
		else return null;
	}
	
/** Checks for collisions, if the collider id a paddle, ball bounces back, if not
 * the collider is removed from the in-game and the ball finds the direction in which it 
 * bounces with method brickLocation();
 * @author Maksym Loshak
 */
	private void collisionCheck()
	{
		collider=getCollidedObjet();
		brickLocation=brickLocation();
		if(collider!=null)
		{
			if(collider==paddle||collider==header||collider==score)
				vy=-vy;
			else if(brickLocation!=null)
			{
				remove(collider);
				collider=null;
				scoreValue+=10;
				//if(brickLocation!=null)
				{
					if(brickLocation.equals("UP")||brickLocation.equals("DOWN"))
						vy=-vy;
					else if(brickLocation.equals("RIGHT")||brickLocation.equals("LEFT"))
						vx=-vx;
				}
				remove(score);
				score.setLabel("Score: "+scoreValue);
				add(score, WIDTH-score.getWidth()-10, score.getHeight()-4 );
			}
		}
	}
	
	
	
	
	/*
	 * booster idea 
	 * 
	 * 
	 * GObject collObjR1 = getElementAt(ball.getX()+ball.getWidth(), ball.getY()+3);
		GObject collObjR2 = getElementAt(ball.getX()+ball.getWidth(), ball.getY()+ball.getHeight()-3);
		GObject collObjL1 = getElementAt(ball.getX(), ball.getY()+vy);
		GObject collObjL2 = getElementAt(ball.getX(), ball.getY()+ball.getHeight()-vy);
		GObject collObjU1 = getElementAt(ball.getX()+vy, ball.getY());
		GObject collObjU2 = getElementAt(ball.getX()+ball.getWidth()-vy, ball.getY());
		GObject collObjD1 = getElementAt(ball.getX()+vy, ball.getY()+ball.getHeight());
		GObject collObjD2 = getElementAt(ball.getX()+ball.getWidth()-vy, ball.getY()+ball.getHeight());
		if(collObjD1!=null||collObjD2!=null)
			return "DOWN";
		if(collObjR1!=null||collObjR2!=null)
			return "RIGHT";
		if(collObjL1!=null||collObjL2!=null)
			return "LEFT";
		if(collObjU1!=null||collObjU2!=null)
			return "UP";
		else return null;
	 */
	
	
	/*private String brickLocation()
	{
		GObject collObjD = getElementAt(ball.getX()+BALL_RADIUS, ball.getY()+ball.getHeight()+0.01);
		GObject collObjU = getElementAt(ball.getX()+BALL_RADIUS, ball.getY()-0.01);
		GObject collObjR = getElementAt(ball.getX()+ball.getWidth()+0.01, ball.getY()+BALL_RADIUS);
		GObject collObjL = getElementAt(ball.getX()-0.01, ball.getY()+BALL_RADIUS);
		if(collObjU!=null)
			return "UP";
		if(collObjD!=null)
			return "DOWN";
		if(collObjR!=null)
			return "RIGHT";
		if(collObjL!=null)
			return "LEFT";
		else return null;
	}
	*/
	
}