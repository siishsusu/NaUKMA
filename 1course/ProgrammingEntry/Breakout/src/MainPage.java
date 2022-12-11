import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import acm.graphics.GImage;
import acm.graphics.GObject;


public class MainPage extends BreakoutMain{
	
	/**
	 * Images used in the game
	 */
	protected GImage background, play, quit, level1, level2, level3, back, close, youWin,youLose;
	
	public void run(){
		this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT); 
		screen=1;
		background();
		playQuitButtons();
		if(screen!=2)addMouseListeners();
		while(screen!=3){
		}
		startGame();
	}
	
	/**
	 * Creates 2 buttons play/quit
	 * @author Vladyslava Rudas
	 */
	protected void playQuitButtons(){
		play = new GImage("play.png");
		play.setSize(210,61);
		add(play, APPLICATION_WIDTH/4, APPLICATION_HEIGHT/2.9);
		
		quit = new GImage("quit.png");
		quit.setSize(210,61);
		add(quit, APPLICATION_WIDTH/4, APPLICATION_HEIGHT/1.9);
	}
	/**
	 * Set background picture
	 * @author Vladyslava Rudas
	 */
	protected GImage background(){
		background = new GImage("background.jpg");
		background.sendBackward();
		add(background, 0, 0);
		return background;
	}
	
	/* 
	 * If key 1 is pressed level 1 starts
	 * If key 2 is pressed level 2 starts
	 * If key 3 is pressed level 3 starts
	 * @author Vladyslava Rudas
	 */
	public void keyPressed(KeyEvent e){
		if (e.getKeyCode() == KeyEvent.VK_1) {
			lvl=1;
		    }else if (e.getKeyCode() == KeyEvent.VK_2) {
		    lvl=2;
		    }else if (e.getKeyCode() == KeyEvent.VK_3) {
		    lvl=3;
			}
		removeAll();
		screen=3;
	}
	public void mouseClicked(MouseEvent e){
		double ClickX= e.getX();
        double ClickY=e.getY();
        GObject collObj = getElementAt(ClickX, ClickY);
		if(screen==1){
			if (collObj == quit)  {
				System.exit(0);
	        }else if (collObj == play)  {
	        	remove(play);
	        	remove(quit);
	        	screen=2;
	        	addKeyListeners();
	        	backQuit();
	        	buttonsLevel();
	        	
	        }
		}else if(screen==2){
			if (collObj == back)  {
				removeAll();
				run();
	        }else if (collObj == close)  {
	        	System.exit(0);
	        }/*else if (collObj == level1)  {
	        	removeAll();
	        	background();
	        	screen=3;
	    			waitForClick();
	    			removeAll();
	    			//game();
	    			
	        	
	        }*/
		}
	}
	/**
	 * Creates 2 buttons go back/quit the program
	 * @author Vladyslava Rudas
	 */
	private void backQuit() {
		back = new GImage("back.png");
		back.setSize(50,50);
		add(back, 0, 0);
		close = new GImage("close.png");
		close.setSize(50,50);
		add(close,APPLICATION_WIDTH-50, 0);
	}
	/**
	 * Creates 3 buttons with levels
	 * @author Vladyslava Rudas
	 */
	private void buttonsLevel() {
		level1 = new GImage("level1.png");
		add(level1, APPLICATION_WIDTH/3, APPLICATION_HEIGHT/2-100);
		level2 = new GImage("level2.png");
		add(level2, APPLICATION_WIDTH/3, APPLICATION_HEIGHT/2);
		level3 = new GImage("level3.png");
		add(level3, APPLICATION_WIDTH/3, APPLICATION_HEIGHT/2+100);
	}
	/**
	 * Creates victory screen
	 * @author Vladyslava Rudas
	 */
	public void victory() {
		youWin = new GImage("youWin.jpg");
		add(youWin, APPLICATION_WIDTH/5, APPLICATION_HEIGHT/2-100);
	}
	/**
	 * Creates losing screen
	 * @author Vladyslava Rudas
	 */
	public void lose() {
		youLose = new GImage("youLose.jpg");
		add(youLose, APPLICATION_WIDTH/5, APPLICATION_HEIGHT/2-100);
	}
}