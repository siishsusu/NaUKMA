import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.util.SoundClip;

public class MainPage extends BreakoutMain{
	
	/**
     * mouse clicked sound
     */
     public SoundClip click = new SoundClip("clickButton.wav");
     
	/* Method: run() */
	/** Runs the BreakoutMain program. 
	*/
	public void run(){
		
		this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT); 
		screen=1;
		while(screen!=9){
		background();
		playQuitButtons();
		addMouseListeners();
		
		while(screen!=3){
		}
		startGame();
		while(screen!=6){
		}restartGame();
	}
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
	
	
	/* 
	 * If key 1 is pressed level 1 starts
	 * If key 2 is pressed level 2 starts
	 * If key 3 is pressed level 3 starts
	 * @author Vladyslava Rudas
	 */
	public void keyPressed(KeyEvent e){
		if (e.getKeyCode() == KeyEvent.VK_1) {
			lvl=1;
			removeAll();
			screen=3;
		    }else if (e.getKeyCode() == KeyEvent.VK_2) {
		    lvl=2;
		    removeAll();
			screen=3;
		    }else if (e.getKeyCode() == KeyEvent.VK_3) {
		    lvl=3;
		    removeAll();
			screen=3;
			}
		
	}
	/* 
	 * screen 1(play\quit)
	 * screen 2 (quit)
	 * screen 4(restart\close)
	 * screen 5(restart\close)
	 */
	public void mouseClicked(MouseEvent e){
		double ClickX= e.getX();
        double ClickY=e.getY();
        GObject collObj = getElementAt(ClickX, ClickY);
		if(screen==1){
			if (collObj == quit)  {
				click.setVolume(0.5);
				click.play();
				System.exit(0);
	        }else if (collObj == play)  {
	        	click.setVolume(0.5);
				click.play();
				
				pause(400);
	        	remove(play);
	        	remove(quit);
	        	screen=2;
	        	addKeyListeners();
	        	backQuit();
	        	buttonsLevel();
	        	
	        }
		}else if(screen==2){
			if (collObj == close)  {
	        	click.setVolume(0.5);
				click.play();
	        	System.exit(0);
	        }
		}else if(screen==4){
			if (collObj == restart)  {
				click.setVolume(0.5);
				click.play();
				removeAll();
				screen=6;
			}else if (collObj == close)  {
	        	click.setVolume(0.5);
				click.play();
	        	System.exit(0);
	        }else if (collObj == nextLevel)  {
	        	click.setVolume(0.5);
				click.play();
				lvl++;
				removeAll();
				screen=3;
	        }
        }else if(screen==5){
        	if (collObj == restart)  {
				click.setVolume(0.5);
				click.play();
				removeAll();
				
				screen=6;
			}else if (collObj == close)  {
	        	click.setVolume(0.5);
				click.play();
	        	System.exit(0);
	        }
        }
	}
	/**
	 * Restart game
	 * @author Vladyslava Rudas
	 */
	private void restartGame() {
		
		NBRICKS_PER_ROW = 10;
		NBRICK_ROWS = 10;
		scoreValue=0;
		lives=3;
		heart1=getHeart();
		heart2=getHeart();
		heart3=getHeart();
		run();
	}

	/**
	 * Creates 2 buttons go back/quit the program
	 * @author Vladyslava Rudas
	 */
	private void backQuit() {
		
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
	
}
