
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
	
	
	/** The number of the level played */
		private static int lvl;
		
		
		
	/** Width and height of application window in pixels */
		public static final int APPLICATION_WIDTH = 400;
		public static final int APPLICATION_HEIGHT = 600;  

	/** Dimensions of game board (usually the same) */
		protected static final int WIDTH = APPLICATION_WIDTH;
		protected static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
		protected static int PADDLE_WIDTH = 60;
		protected static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
		protected static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
		protected static int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
		protected static int NBRICK_ROWS = 10;

	/** Separation between bricks */
		protected static final int BRICK_SEP = 4;

	/** Width of a brick */
		protected static final int BRICK_WIDTH =
		  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
		protected static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
		protected static int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
		protected static final int BRICK_Y_OFFSET = 70;
		
	/** In-game movement delay */
		protected static int DELAY = 6;

	/** Number of turns */
		protected static final int NTURNS = 3;
		
	/** An item of GObject paddle (GRect) */
		protected static GRect paddle;
		
	/** An item of GObject ball (GOval) */
		private static GOval ball;
		
	/** An item of GObject the ball has collided with */
		private static GObject collider;
		
	/** An item of GObject that gives additional values when collected */
		private static GRect booster;
		
	/** An item of GObject explaining the function of booster */
		private static GLabel explanation;
		
	/** Timer showing how many ticks have gone since the appearance of the explanation */
		private static int timer;
		
	/** An item of GObject showing score of the player */
		private static GLabel score;
		
	/** An item of GObject GCompound line */
		private static GCompound header;
		
	/** The location of the brick due to the location of ball*/
		private static String brickLocation;
		
	/** Images of a heart */
		private static GImage heart1=getHeart();
		private static GImage heart2=getHeart();
		private static GImage heart3=getHeart();
		
	/** Amount of lives */
		protected static int lives=3;
		
	/** The speed of the ball by X axis */
		private static double vx;
		
	/** The speed of the ball by Y axis */
		private static double vy;
		
	/** The counter of score */
		protected static int scoreValue=0;
		
	/** Random generating algorithm */
		private static RandomGenerator rgen = RandomGenerator.getInstance();
		
	/** Static color of the paddle */
		private static final Color paddleColor = new Color(rgen.nextInt(1, 255), rgen.nextInt(1, 255), rgen.nextInt(1, 255));
	/** Which screen is visible */	
		protected int screen;
		
		/**
		 * Images used in the game
		 */
		protected GImage background, play, quit, level1, level2,
		level3, back, close, youWin,youLose, restart;
		
		/**
	     * If player won
	     */
		public SoundClip whatIfWin = new SoundClip("whatIfWin.au");
	   
	    /**
	     * If player lose
	     */
		public SoundClip whatIfLose = new SoundClip("failing.au");
	/* Method: run() */
	/** Runs the BreakoutMain program. */
		public void startGame() 
		{
			setup(); 
			while(!gameOver())
			{
				ballMovement();
				boosterMovement();
				collisionCheck();
				pause(DELAY);
				
			}
			//if(gameOver())
			}
	/**
	 * Sets the size of the console
	 * Sets all necessary variables	values
	 * Adds all necessary in-game items 
	 * @author Maksym Loshak
	 */
		public void setup()
		{
			
			lvl=2;
			
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
			heartInitialization();
			add(header,0,0);
			
		}
		
		public boolean gameOver()
		{
			if(ball.getY()>=HEIGHT)
			{
				lives--;
				if(lives==2)
				{
					remove(heart3);
					heart3=getHeartBW();
					//add(heart3);
					pause(500);
				}
				else if(lives==1)
				{
					remove(heart2);
					heart2=getHeartBW();
					//add(heart2);
					pause(500);
				}
				else {
					removeAll();
					background();
					whatIfLose.setVolume(0.5);
					whatIfLose.play();
					screen=4;
					lose();
					return (true);
				}
					
			}
			else if (scoreValue==1000){
				removeAll();
				background();
				whatIfWin.setVolume(0.5);
				whatIfWin.play();
				screen=5;
				victory();
			return (true);
			}
			return false;
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
	/** Creates an image of a heart showing the amount of lives 
	 * @author Maksym Loshak
	 * @return heart
	 */
		private static GImage getHeart()
		{
			GImage heart = new GImage("life.png");
			heart.scale(0.11);
			return heart;
		}
	/** Creates an image of a heart showing the amount of lives 
	 * @author Maksym Loshak
	 * @return heartBW
	 */	
		private static GImage getHeartBW()
		{
			GImage heartBW = new GImage("life_BW.png");
			heartBW.scale(0.11);
			return heartBW;
		}
		
		private static GRect getBooster()
		{
			GRect booster = new GRect(10, 10);
			booster.setFilled(true);
			Color boosterColor = new Color(rgen.nextInt(1, 255), rgen.nextInt(1, 255), rgen.nextInt(1, 255));
			booster.setFillColor(boosterColor);
			return booster;
		}
	/**
	 * Creates an Object of GObject class (GRect)
	 * in-game paddle
	 * @author Maksym Loshak
	 * @return paddle
	 */
		protected GRect getPaddle()
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
	/**
	 * Creates an Object of GObject class (GLabel)
	 * in-game score bar
	 * @author Maksym Loshak
	 * @return score
	 */
		private GLabel getScore()
		{
			GLabel score = new GLabel("");
			score.setFont("Times New Roman-36");
			return score;
		}
	/**
	 * Creates an Object of GObject class (GCompound)
	 * in-game headline
	 * @author Maksym Loshak
	 * @return header
	 */
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
		if(screen==3){
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
	 * Reloads the amount of lives 
	 * @author Maksym Loshak
	 */
		private void heartInitialization()
		{
			add(heart1, 10,10);
			add(heart2, heart1.getX()+heart1.getWidth()+5, 10);
			add(heart3, heart2.getX()+heart2.getWidth()+5, 10);
		}
	/**
	 * Generates probability of booster being dropped from random brick
	 * @author Maksym Loshak
	 * @return true/false
	 */
		private boolean boosterChance()
		{
			int chance = 5;// rgen.nextInt(1,5);
			if(chance==5)
				return true;
			else return false;
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
			if(ball.getY()>=HEIGHT+vy)
			{
				remove(ball);
				ball=null;
				if(lives!=0)
				{
					ball=getBall();
					ballSpeed();
					add(ball,(WIDTH-BALL_RADIUS)/2, (HEIGHT-BALL_RADIUS)/2);
					heartInitialization();
				}	
			}
		}
	/**
	 * Creates booster downfall if this item exists
	 * @author Maksym Loshak
	 */
		private void boosterMovement()
		{
			if(booster!=null)
			{
				booster.move(0, 0.5);
				if(booster.getY()>=getHeight())
				{
					remove(booster);
					booster=null;
				}
			}
			if(explanation!=null)
				timer++;
			if(timer==100&&explanation!=null)
			{
				remove(explanation);
				explanation=null;
			}
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
		
	/** Checks for collisions of the ball, if the collider id a paddle, ball bounces back, if not
	 * the collider is removed from the in-game space and the ball finds the direction in which it 
	 * bounces with method brickLocation(); If the collider is a brick there is a probability of 
	 * booster spawning.
	 * 
	 * Also checks if there is a collision between paddle and booster, if so it runs method 
	 * boosterApply();
	 * @author Maksym Loshak
	 */
		private void collisionCheck()
		{
			collider=getCollidedObjet();
			brickLocation=brickLocation();
			if(collider!=null&&collider!=booster)
			{
				if(collider==paddle||collider==header||collider==score)
				{
					if(collider==paddle)
					{
						ball.setLocation(ball.getX(), paddle.getY()-ball.getHeight());
					}
					vy=-vy;
				}
				else if(brickLocation!=null)
				{
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
					if(lvl>1&&booster==null)
					{
						if(boosterChance())
						{
							booster=getBooster();
							add(booster, collider.getX()+(collider.getWidth()-booster.getWidth())/2, collider.getY()+collider.getHeight()/2);
						}
					}
					remove(collider);
					collider=null;
				}
			}
			if(booster!=null)
			{
				if(booster.getX()+booster.getWidth()>paddle.getX()&&booster.getX()<paddle.getX()+paddle.getWidth())
				{
					if(booster.getY()+booster.getHeight()>=paddle.getY()&&booster.getY()<=paddle.getY()+paddle.getHeight())
					{
						remove(booster);
						booster=null;
						boosterApply();
					}
				}
			}
		}
	/**Applies random booster and shows explanation what this booster does
	 * boosters:
	 * paddle (increased/decreased size)
	 * ball (increased/decreased size)
	 * ball (increased/decreased movement speed)
	 * @author Maksym Loshak
	 */
		private void boosterApply()
		{
			if(explanation!=null)
			{
				remove(explanation);
				explanation=null;
			}
			explanation = new GLabel("");
			int booster = rgen.nextInt(1,6);
			if(booster==1)
			{
				PADDLE_WIDTH+=20;
				explanation.setLabel("Paddle size increased!");
				explanation.setColor(Color.blue);
			}
			else if(booster==2)
			{
				if(DELAY>3)
				{
					DELAY-=1;
					explanation.setLabel("Ball speed increased!");
					explanation.setColor(Color.red);
				}
				else boosterApply();
			}
				
			else if(booster==3)
			{
				if(PADDLE_WIDTH>40)
				{
					explanation.setLabel("Paddle size decreased!");
					explanation.setColor(Color.red);
					PADDLE_WIDTH-=20;
				}
				else boosterApply();
			}
			else if(booster==4)
			{
				BALL_RADIUS+=2;  	
				explanation.setLabel("Ball size increased!");
				double x = ball.getX();
				double y = ball.getY();
				remove(ball);
				ball=getBall();
				add(ball, x-2, y-2);
				explanation.setColor(Color.blue);
			}
			else if(booster==5)
			{
				if(BALL_RADIUS>6)
				{
					BALL_RADIUS-=2;  	
					explanation.setLabel("Ball size decreased!");
					double x = ball.getX();
					double y = ball.getY();
					remove(ball);
					ball=getBall();
					add(ball, x-2, y-2);
					explanation.setColor(Color.red);
				}
				else boosterApply();
			}
			else if(booster==6)
			{
				if(DELAY<5)
				{
					DELAY+=1;
					explanation.setLabel("Ball speed decrased!");
					explanation.setColor(Color.blue);
				}
				else boosterApply();
			}
			
			explanation.setFont("Times New Roman-36");
			timer=0;
			add(explanation, (getWidth()-explanation.getWidth())/2, 40+explanation.getHeight());
		}
		
		
//---------------------------------------------------------------------------------------------------------
	
		
		/**
		 * Creates victory screen with restart button
		 * @author Vladyslava Rudas
		 */
		protected void victory() {
			youWin = new GImage("youWin.jpg");
			add(youWin, APPLICATION_WIDTH/5, APPLICATION_HEIGHT/2-100);
			pause(1000);
			restart = new GImage("restart.png");
			add(restart, APPLICATION_WIDTH/2-30, APPLICATION_HEIGHT/2-180);
		}
		/**
		 * Creates losing screen with restart button
		 * @author Vladyslava Rudas
		 */
		protected void lose() {
			youLose = new GImage("youLose.jpg");
			add(youLose, APPLICATION_WIDTH/5, APPLICATION_HEIGHT/2-100);
			pause(1000);
			restart = new GImage("restart.png");
			add(restart, APPLICATION_WIDTH/2-30, APPLICATION_HEIGHT/2-180);
		}
		/**
		 * Set background picture
		 * @author Vladyslava Rudas
		 */
		protected GImage background(){
			background = new GImage("background.jpg");
			background.sendBackward();
			add(background, 0,0);
			return background;
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
