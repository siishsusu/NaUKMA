import java.awt.Color;

import org.w3c.dom.css.RGBColor;

import acm.graphics.*;
import acm.program.GraphicsProgram;

public class Objects extends GraphicsProgram
{
	static Color planeWingColor = new Color(194, 194, 163);
	static Color planeColor = new Color(173, 173, 133);
	public void run()
	{
		this.setSize(1300,900);
		//add(staticTarget(),0,getHeight()-staticTarget().getHeight());
		//add(bomb(), 0, 0);
		//add(bigMovingTargetRight(),0,0);
		//add(largeExplosion(),0,0);
		//finalScreen(2);
		startScreen();
	}
	public static GCompound planeRight(int planeWidth, int planeHeight)
	{
		GCompound planeFacingRight = new GCompound();
		GPolygon planeSoul=new GPolygon();
		planeSoul.addVertex(0, 0);
		planeSoul.addEdge(planeWidth, 0);
		planeSoul.addEdge(-planeWidth*35/100,-planeHeight*8/10);
		planeSoul.addEdge(-planeWidth/5, 0);
		planeSoul.addEdge(-planeWidth*15/100,planeHeight/2);
		planeSoul.addEdge(-planeWidth/10,0);
		planeSoul.addEdge(-planeWidth/10, -planeHeight*7/10);
		planeSoul.addEdge(-planeWidth/10,0);
		planeSoul.addEdge(0, planeHeight);
		planeSoul.setFilled(true);
		planeSoul.setFillColor(planeColor);
		//add(planeSoul, x, y);
		GPolygon planeWindow=new GPolygon();
		planeWindow.addVertex(0.55*planeWidth,-0.5*planeHeight);
		planeWindow.addEdge(planeWidth*0.23125, 0);
		planeWindow.addEdge(-planeWidth*0.13125, -0.25*planeHeight);
		planeWindow.addEdge(-0.1*planeWidth, 0);
		planeWindow.addEdge(0,0.25*planeHeight);
		planeWindow.setFilled(true);
		planeWindow.setFillColor(Color.blue);
		//add(planeWindow, x, y);
		GPolygon planeWing = new GPolygon();
		planeWing.addVertex(0.45*planeWidth, -0.3*planeHeight);
		planeWing.addEdge(0.2*planeWidth, 0);
		planeWing.addEdge(-planeWidth*0.1, planeHeight*0.5);
		planeWing.addEdge(-0.15*planeWidth, 0);
		planeWing.addEdge(planeWidth*0.05, -planeHeight*0.5);
		planeWing.setFilled(true);
		planeWing.setFillColor(planeWingColor);
		//add(planeWing, x, y);
		GPolygon planeWing1 = new GPolygon();
		planeWing1.addVertex(0.03*planeWidth, -0.3*planeHeight);
		planeWing1.addEdge(0.2*planeWidth, 0);
		planeWing1.addEdge(-planeWidth*0.1, planeHeight*0.5);
		planeWing1.addEdge(-0.15*planeWidth, 0);
		planeWing1.addEdge(planeWidth*0.05, -planeHeight*0.5);
		planeWing1.scale(0.75);
		planeWing1.setFilled(true);
		planeWing1.setFillColor(planeWingColor);
		//add(planeWing1, x, y);
		planeFacingRight.add(planeSoul);
		planeFacingRight.add(planeWindow);
		planeFacingRight.add(planeWing);
		planeFacingRight.add(planeWing1);
		return planeFacingRight;
	}
	public static GCompound planeLeft(int planeWidth, int planeHeight)
	{
		GCompound planeFacingLeft = new GCompound();
		GPolygon planeSoul=new GPolygon();
		planeSoul.addVertex(0, 0);
		planeSoul.addEdge(-planeWidth, 0);
		planeSoul.addEdge(planeWidth*35/100,-planeHeight*8/10);
		planeSoul.addEdge(planeWidth/5, 0);
		planeSoul.addEdge(planeWidth*15/100,planeHeight/2);
		planeSoul.addEdge(planeWidth/10,0);
		planeSoul.addEdge(planeWidth/10, -planeHeight*7/10);
		planeSoul.addEdge(planeWidth/10,0);
		planeSoul.addEdge(0, planeHeight);
		planeSoul.setFilled(true);
		planeSoul.setFillColor(planeColor);
		GPolygon planeWindow=new GPolygon();
		planeWindow.addVertex(-0.55*planeWidth,-0.5*planeHeight);
		planeWindow.addEdge(-planeWidth*0.23125, 0);
		planeWindow.addEdge(planeWidth*0.13125, -0.25*planeHeight);
		planeWindow.addEdge(0.1*planeWidth, 0);
		planeWindow.addEdge(0,0.25*planeHeight);
		planeWindow.setFilled(true);
		planeWindow.setFillColor(Color.blue);
		GPolygon planeWing = new GPolygon();
		planeWing.addVertex(-0.45*planeWidth, -0.3*planeHeight);
		planeWing.addEdge(-0.2*planeWidth, 0);
		planeWing.addEdge(planeWidth*0.1, planeHeight*0.5);
		planeWing.addEdge(0.15*planeWidth, 0);
		planeWing.addEdge(-planeWidth*0.05, -planeHeight*0.5);
		planeWing.setFilled(true);
		planeWing.setFillColor(planeWingColor);
		GPolygon planeWing1 = new GPolygon();
		planeWing1.addVertex(-0.03*planeWidth, -0.3*planeHeight);
		planeWing1.addEdge(-0.2*planeWidth, 0);
		planeWing1.addEdge(planeWidth*0.1, planeHeight*0.5);
		planeWing1.addEdge(0.15*planeWidth, 0);
		planeWing1.addEdge(-planeWidth*0.05, -planeHeight*0.5);
		planeWing1.scale(0.75);
		planeWing1.setFilled(true);
		planeWing1.setFillColor(planeWingColor);
		planeFacingLeft.add(planeSoul);
		planeFacingLeft.add(planeWindow);
		planeFacingLeft.add(planeWing);
		planeFacingLeft.add(planeWing1);
		return planeFacingLeft;
	}
	public static GImage bigMovingTargetRight()
	{
		GImage bigMovingTargetRight = new GImage("C:\\Users\\maxlo\\Pictures\\PngItem_4809651.png");
		bigMovingTargetRight.scale(0.1);
		return bigMovingTargetRight;
	}
	public static GImage bigMovingTargetLeft()
	{
		GImage bigMovingTargetLeft = new GImage("C:\\Users\\maxlo\\Pictures\\PngItem_4809651 (2).png");
		bigMovingTargetLeft.scale(0.1);
		return bigMovingTargetLeft;
	}
	public static GImage smallMovingTargetRight()
	{
		GImage smallMovingTargetRight = new GImage("C:\\Users\\maxlo\\Pictures\\NicePng_army-tank-png_2798465.png");
		smallMovingTargetRight.scale(0.05);
		return smallMovingTargetRight;
	}
	public static GImage smallMovingTargetLeft()
	{
		GImage smallMovingTargetLeft = new GImage("C:\\Users\\maxlo\\Pictures\\NicePng_army-tank-png_2798465 (2).png");
		smallMovingTargetLeft.scale(0.05);
		return smallMovingTargetLeft;
	}
	public static GImage staticTarget()
	{
		GImage staticTarget = new GImage("C:\\Users\\maxlo\\Pictures\\base.png");
		staticTarget.scale(0.45);
		return staticTarget;
	}
	public static GImage bomb()
	{
		GImage bomb = new GImage("C:\\Users\\maxlo\\Pictures\\bomb_resized.png");
		return bomb;
	}
	public static GImage explosion()
	{
		GImage explosion = new GImage("C:\\Users\\maxlo\\Pictures\\explosion.png");
		explosion.scale(0.25);
		return explosion;
	}
	public static GImage largeExplosion()
	{
		GImage largeExplosion = new GImage("C:\\Users\\maxlo\\Pictures\\nuclear-bomb.png");
		largeExplosion.scale(0.35);
		return largeExplosion;
	}
	public void startScreen()
	{
		GLabel pressPlay = new GLabel("Plane Game!");
		pressPlay.setFont("Matura MT Script Capitals-70");
		GImage play = new GImage("C:\\Users\\maxlo\\Pictures\\play-button.png");
		play.scale(0.5);
		GRect rect = new GRect(500, 200);
		add(pressPlay,(getWidth()-pressPlay.getWidth())/2,(getHeight()-2*pressPlay.getHeight())/2-70);
		add(play,getWidth()/2-play.getWidth()/2, getHeight()/2);
	}
	public void finalScreen(int n)
	{
		GImage tryAgain = new GImage("\\Users\\maxlo\\Pictures\\undo-arrow.png");
		GImage soldier = new GImage("\\Users\\maxlo\\Pictures\\soldier.png");
		GImage sadSoldier = new GImage("\\Users\\maxlo\\Pictures\\soldier (2).png");
		tryAgain.scale(0.5);
		soldier.scale(0.5);
		sadSoldier.scale(0.5);
		if(n==1)
		{
			GImage trophy = new GImage("\\Users\\maxlo\\Pictures\\trophy (1).png");
			GImage trophy1 = new GImage("\\Users\\maxlo\\Pictures\\trophy (1).png");
			trophy.scale(0.5);
			trophy1.scale(0.5);
			GLabel victory = new GLabel("Victory!");
			victory.setFont("Matura MT Script Capitals-70");
			victory.setColor(Color.yellow);
			add(victory,(getWidth()-victory.getWidth())/2,(getHeight()-victory.getHeight())/2);
			add(trophy, victory.getX()-trophy.getWidth()*1.5, victory.getY());
			add(trophy1, victory.getX()+trophy.getWidth()/2+victory.getWidth(), victory.getY());
			add(soldier, victory.getX()+victory.getWidth()/2-soldier.getWidth()/2, victory.getY()-soldier.getHeight()-victory.getHeight()-5);
			add(tryAgain,getWidth()/2-tryAgain.getWidth()/2, getHeight()/2+40);
		}
		else if(n==2)
		{
			GImage skull = new GImage("\\Users\\maxlo\\Pictures\\game-over.png");
			GImage skull1 = new GImage("\\Users\\maxlo\\Pictures\\game-over.png");
			skull.scale(0.5);
			skull1.scale(0.5);
			GLabel victory = new GLabel("Defeat!");
			victory.setFont("Matura MT Script Capitals-70");
			victory.setColor(Color.red);
			add(victory,(getWidth()-victory.getWidth())/2,(getHeight()-victory.getHeight())/2);
			add(skull, victory.getX()-skull.getWidth()*1.5, victory.getY());
			add(skull1, victory.getX()+skull1.getWidth()/2+victory.getWidth(), victory.getY());
			add(tryAgain,getWidth()/2-tryAgain.getWidth()/2, getHeight()/2+40);
			add(sadSoldier, victory.getX()+victory.getWidth()/2-soldier.getWidth()/2, victory.getY()-soldier.getHeight()-victory.getHeight()-5);
		}
	}
	public static GImage background()
	{
		GImage background = new GImage("C:\\Users\\maxlo\\Pictures\\background.png");
		background.scale(0.9);
		return background;
	}
	public static GImage start_bg()
	{
		GImage start_bg = new GImage("C:\\Users\\maxlo\\Pictures\\start_bg.png");
		start_bg.scale(2);
		return start_bg;
	}
	
	//add(pressPlay, (getWidth()-pressPlay.getWidth())/2,(getHeight()-2*pressPlay.getHeight())/2-25);
	
	
	
}
