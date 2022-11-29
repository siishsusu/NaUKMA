import acm.program.*; 
import acm.graphics.*; 

import java.awt.*; 
import java.awt.event.*; 

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardDownRightHandler;
public class Global extends Objects
{
	private static final int PLANE_WIDTH = 200; 
	private static final int PLANE_HEIGHT = 40; 
	public static final int WINDOW_WIDTH = 1300;
	public static final int WINDOW_HEIGHT = 900;
	private static final int PLANE_SPEED = 5; 
	private static GCompound plane;
	private static GImage smallMovingTarget;
	private static GImage background = background();
	private static GImage start_bg = start_bg();
	private static GImage largeExplosion = largeExplosion();
	private static GImage smallExplosion = explosion();
	private static final int SMALL_TARGET_SPEED = 4; 
	private static GImage bigMovingTarget;
	private static final int BIG_TARGET_SPEED = 2; 
	private static GImage staticTarget=staticTarget();
	private static final int BULLET_SPEED = 10; 
	private static final int BULLET_DIAM = 5; 
	private static int layer=1;
	private static int screens=0;
	//private static AudioClip explosionAudio = GetAudioClip
	
	private GImage bullet;
	private boolean planeToLeft;
	private boolean smallTargetToLeft;
	private boolean bigTargetToLeft;
	/** Animation cycle delay */ 
	private static final int DELAY = 10; 
	
	public void run()
	{
		add(start_bg,0,0);
		this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT); 
		addMouseListeners();
		startScreen();
		while(screens!=1){
		}
		setup();
		game();
	}
	
	private void setup() { 
		this.removeAll();
		add(background,0,0);
		plane = planeLeft(200, 40);
		smallMovingTarget=smallMovingTargetLeft();
		bigMovingTarget=bigMovingTargetRight();
		add(plane, WINDOW_WIDTH, plane.getHeight()); 
		add(staticTarget, getWidth()/2, getHeight()-staticTarget.getHeight());
		add(smallMovingTarget, getWidth()-smallMovingTarget.getWidth(), getHeight()-smallMovingTarget.getHeight());
		add(bigMovingTarget, 0,getHeight()-bigMovingTarget.getHeight());
		planeToLeft = true; 
		smallTargetToLeft=true;
		bigTargetToLeft=false;
	} 
	private void game()
	{
		while (!gameOver()) 
		{
			movePlane(); 
			moveTargets();
			moveBullet(); 
			removeExplosions();
			checkForCollisions(); 
			pause(DELAY); 
		} 
		if(plane.getY() >= getHeight() - PLANE_HEIGHT)
		{
			remove(plane);
			add(largeExplosion(), plane.getX(), getHeight()-largeExplosion().getHeight());
			plane=null;
			pause(60);
			this.removeAll();
			screens=0;
			add(start_bg,0,0);
			finalScreen(2);
			while(screens!=1){
			}
			layer=1;
			staticTarget=staticTarget();
			largeExplosion = largeExplosion();
			smallExplosion = explosion();
			run();
		}
		else if(staticTarget==null&&smallMovingTarget==null&&bigMovingTarget==null)
		{
			pause(30);
			this.removeAll();
			plane=null;
			screens=0;
			add(start_bg,0,0);
			finalScreen(1);
			while(screens!=1){
			}
			layer=1;
			staticTarget=staticTarget();
			largeExplosion = largeExplosion();
			smallExplosion = explosion();
			run();
		}
	}
	
	public void gamestart()
	{
		
	}
	
	private boolean gameOver() { 
		return (plane.getY() >= getHeight() - PLANE_HEIGHT)||(staticTarget==null&&smallMovingTarget==null&&bigMovingTarget==null); 
	}

	public void mouseClicked(MouseEvent e) 
	{ 
		if(screens==1)
		{
			if (bullet == null) 
			{ 
				bullet = new GImage("C:\\Users\\maxlo\\Pictures\\bomb_resized.png");
				bullet.scale(0.15);
				if(layer%2==0)
				{
					add(bullet, plane.getX()+plane.getWidth()/2,plane.getY()); 
				}
				else
				{
					add(bullet, plane.getX()-plane.getWidth()/2,plane.getY()); 
				}
			} 
		}
		else if(screens==0)
		{
			screens=1;
		}
	} 
	private void removeExplosions()
	{
		if(largeExplosion!=null)
		{
			largeExplosion.move(0, 2);
		}
		if(smallExplosion!=null)
		{
			smallExplosion.move(0, 2);
		}
		/*if(smallExplosion1!=null)
		{
			smallExplosion1.move(0, 2);
		}*/
	}
	
	private void movePlane() { 
		if (planeToLeft) { 
			plane.move(-PLANE_SPEED, 0); 
			if (plane.getX()-plane.getWidth() <= 0) 
			{ 
				remove(plane);
				plane=planeRight(200, 40);
				layer++;
				add(plane, 0, layer*plane.getHeight()); 
				planeToLeft = false; 
				plane.move(0, PLANE_HEIGHT); 
			} 
		} 
		else 
		{ 
			plane.move(PLANE_SPEED, 0); 
			if (plane.getX()>= getWidth() - PLANE_WIDTH) 
			{ 
				remove(plane);
				plane=planeLeft(200, 40);
				layer++;
				add(plane, WINDOW_WIDTH, layer*plane.getHeight()); 
				planeToLeft = true; 
				plane.move(0, PLANE_HEIGHT); 
			} 
		} 
	}
	
	private void moveTargets() { 
		if(smallMovingTarget!=null)
		{
			if (smallTargetToLeft) { 
				smallMovingTarget.move(-SMALL_TARGET_SPEED, 0); 
				if (smallMovingTarget.getX()-smallMovingTarget.getWidth() <= 0) 
				{ 
					remove(smallMovingTarget);
					smallMovingTarget=smallMovingTargetRight();
					add(smallMovingTarget, 0,getHeight()-smallMovingTarget.getHeight()); 
					smallTargetToLeft = false; 
				} 
			} 
			else 
			{ 
				smallMovingTarget.move(SMALL_TARGET_SPEED, 0); 
				if (smallMovingTarget.getX()>= getWidth() - smallMovingTarget.getWidth()) 
				{ 
					remove(smallMovingTarget);
					smallMovingTarget=smallMovingTargetLeft();
					add(smallMovingTarget, getWidth()-smallMovingTarget.getWidth(), getHeight()-smallMovingTarget.getHeight()); 
					smallTargetToLeft = true; 
				} 
			} 
		}
		if(bigMovingTarget!=null)
		{
			if (bigTargetToLeft) { 
				bigMovingTarget.move(-BIG_TARGET_SPEED, 0); 
				if (bigMovingTarget.getX()-bigMovingTarget.getWidth() <= 0) 
				{ 
					remove(bigMovingTarget);
					bigMovingTarget=bigMovingTargetRight();
					add(bigMovingTarget, 0,getHeight()-bigMovingTarget.getHeight()); 
					bigTargetToLeft = false; 
				} 
			} 
			else 
			{ 
				bigMovingTarget.move(BIG_TARGET_SPEED, 0); 
				if (bigMovingTarget.getX()>= getWidth() - bigMovingTarget.getWidth()) 
				{ 
					remove(bigMovingTarget);
					bigMovingTarget=bigMovingTargetLeft();
					add(bigMovingTarget, getWidth()-bigMovingTarget.getWidth(), getHeight()-bigMovingTarget.getHeight()); 
					bigTargetToLeft = true; 
				} 
			} 
		}
	}
	
	private void moveBullet() { 
		if (bullet != null) { 
			bullet.move(0, BULLET_SPEED); 
		} 
	} 
	
	private void checkForCollisions() { 
		collideWithTarget(); 
		moveOffScreen(); 
	}
	
	private void collideWithTarget() { 
		if (bullet != null) { 
			GObject collObj = getElementAt(bullet.getX(), bullet.getY()+bullet.getHeight()); 
			GObject collObj1 = getElementAt(bullet.getX()+bullet.getWidth(), bullet.getY()+bullet.getHeight()); 
			if(staticTarget!=null)
			{
				if (collObj == staticTarget || collObj1== staticTarget) { 
					remove(staticTarget); 
					remove(bullet); 
					//GImage explosion = explosion();
					add(largeExplosion, staticTarget.getX(),getHeight()-largeExplosion().getHeight());
					staticTarget = null; 
					bullet = null; 
				} 
			}
			if(smallMovingTarget!=null)
			{
				if(collObj == smallMovingTarget || collObj1== smallMovingTarget)
				{
					remove(smallMovingTarget); 
					remove(bullet);
					//GImage explosion = explosion();
					add(smallExplosion, smallMovingTarget.getX(),getHeight()-explosion().getHeight());
					smallMovingTarget = null; 
					bullet = null; 
				}
			}
			if(bigMovingTarget!=null)
			{
				if(collObj == bigMovingTarget || collObj1== bigMovingTarget)
				{
					remove(bigMovingTarget); 
					remove(bullet);
					//GImage explosion = explosion();
					add(smallExplosion, bigMovingTarget.getX(),getHeight()-explosion().getHeight());
					bigMovingTarget = null; 
					bullet = null; 
				}
			}
		} 
	} 
	
	private void moveOffScreen() { 
		if (bullet != null) { 
			if (bullet.getY() >= WINDOW_HEIGHT+BULLET_DIAM) { 
				remove(bullet); 
				bullet = null; 
			} 
		} 
		if(largeExplosion!= null)
		{
			if(largeExplosion.getY()>=getHeight())
			{
				remove(largeExplosion);
				largeExplosion=null;
			}
		}
		if(smallExplosion!= null)
		{
			if(smallExplosion.getY()>=getHeight())
			{
				remove(smallExplosion);
				smallExplosion=null;
				smallExplosion=explosion();
			}
		}

	}
	/*
	public static void playMusic(String filePath)
	{
		InputStream music;
		try{
			music = new FileInputStream(new File(filepath));
			AudioStream audios= new AudioStream(music);
			AudioPlayer.player.start(audios);
		}
		catch(Exception e){
			
		}
	}
	*/
}
