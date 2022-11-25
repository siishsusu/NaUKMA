import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.util.RandomGenerator;

public class Global extends Grid
{
	private static int SIZE = 600;
	private static int GRID_WIDTH=SIZE/60;
	private static boolean CrossOrCircle=false;
	private static int screens=2;
	public static int sq1=0;
	public static int sq2=0;
	public static int sq3=0;
	public static int sq4=0;
	public static int sq5=0;
	public static int sq6=0;
	public static int sq7=0;
	public static int sq8=0;
	public static int sq9=0;
	
	
	public static ImageType playerImage;
	public static ScreenType screen;
	static enum ImageType{
		Cross,
		Circle,
	}
	static enum ScreenType{
		StartScreen,
		GameScreen,
		RestartScreen,
	}
	
	public void run()
	{
		this.setSize(SIZE, SIZE+60);
		setBackground(Color.yellow);
		addMouseListeners();
		choice();
		//grid();
	}
	
	/*public void init(){
		
		
	}*/
	
	
	private void check() 
	{
		if(sq1==1&&sq2==1&&sq3==1||
			sq4==1&&sq5==1&&sq6==1||
			sq7==1&&sq8==1&&sq9==1||
			sq2==1&&sq5==1&&sq8==1||
			sq3==1&&sq6==1&&sq9==1||
			sq1==1&&sq4==1&&sq7==1||
			sq1==1&&sq5==1&&sq9==1||
			sq7==1&&sq5==1&&sq3==1)
				{
					this.removeAll();
					string(1);
					buttons();
					screens=1;
				}
		else if(sq1==2&&sq2==2&&sq3==2||
				sq4==2&&sq5==2&&sq6==2||
				sq7==2&&sq8==2&&sq9==2||
				sq2==2&&sq5==2&&sq8==2||
				sq3==2&&sq6==2&&sq9==2||
				sq1==2&&sq4==2&&sq7==2||
				sq1==2&&sq5==2&&sq9==2||
				sq7==2&&sq5==2&&sq3==2)
		{
			this.removeAll();
			string(2);
			buttons();
			screens=1;
		}
		/*else if((sq1==1||sq1==2)&&sq2==1||sq2==2&&
				sq3==1||sq3==2&&sq4==1||sq4==2&&
				sq5==1||sq5==2&&sq6==1||sq6==2&&
				sq7==1||sq7==2&&sq8==1||sq8==2&&
				sq9==1||sq9==2)
		{
			this.removeAll();
			string(0);
			buttons();
			screens=1;
		}*/
					
		
	}

	int i=0;
	public void mouseClicked(MouseEvent e)
	{
		int random=rgen.nextInt(1,9);
		if(screens==0)
		{
			
			
			
			
			
			
			if(CrossOrCircle==true)
			{
					if(e.getX()<=getWidth()/3)
					{
						if(e.getY()<=getHeight()/3&&sq1==0) 
						{
							circle(0, 0);
							sq1=2;
							CrossOrCircle=false;
						}
						else if(e.getY()>getHeight()/3&&e.getY()<=2*getHeight()/3&&sq2==0) 
						{
							circle(0,GRID_WIDTH+5+ getHeight()/3);
							sq2=2;
							CrossOrCircle=false;
						}
						else if(e.getY()>2*getHeight()/3&&sq3==0)
						{
							circle(0, 2*(GRID_WIDTH+getHeight()/3));
							sq3=2;
							CrossOrCircle=false;
						}
					}
					else if(e.getX()>getWidth()/3&&e.getX()<=2*getWidth()/3)
					{
						if(e.getY()<=getHeight()/3&&sq4==0) 
						{
							circle(getWidth()/3+GRID_WIDTH, 0);
							sq4=2;
							CrossOrCircle=false;
						}
						else if(e.getY()>getHeight()/3&&e.getY()<=2*getHeight()/3&&sq5==0) 
						{
							circle(getWidth()/3+GRID_WIDTH, getHeight()/3+5+GRID_WIDTH);
							sq5=2;   
							CrossOrCircle=false;
						}
						else if(e.getY()>2*getHeight()/3&&sq6==0) 
						{
							circle(getWidth()/3+GRID_WIDTH, 2*(getHeight()/3+GRID_WIDTH));
							sq6=2;
							CrossOrCircle=false;
						}
					}
					else if(e.getX()>2*getWidth()/3)
					{
						if(e.getY()<=getHeight()/3&&sq7==0) 
						{
							circle(2*(getWidth()/3+GRID_WIDTH), 0);
							sq7=2;
							CrossOrCircle=false;
						}
						else if(e.getY()>getHeight()/3&&e.getY()<=2*getHeight()/3&&sq8==0) 
						{
							circle(2*(getWidth()/3+GRID_WIDTH), getHeight()/3+5+GRID_WIDTH);
							sq8=2;
							CrossOrCircle=false;
						}
						else if(e.getY()>2*getHeight()/3&&sq9==0) 
						{
							circle(2*(getWidth()/3+GRID_WIDTH), 2*(getHeight()/3+GRID_WIDTH));
							sq9=2;
							CrossOrCircle=false;
						}
					}
					check();
			}
			
		
		
		
			else if(CrossOrCircle==false)
			{
				
					if(e.getX()<=getWidth()/3)
					{
						if(e.getY()<=getHeight()/3&&sq1==0) 
						{
							cross(0, 0);
							sq1=1;
							CrossOrCircle=true;
							computer(i);
						}
						else if(e.getY()>getHeight()/3&&e.getY()<=2*getHeight()/3&&sq2==0) 
						{
							cross(0,GRID_WIDTH+5+ getHeight()/3);
							sq2=1;
							CrossOrCircle=true;
							computer(i);
						}
						else if(e.getY()>2*getHeight()/3&&sq3==0)
						{
							cross(0, 2*(GRID_WIDTH+getHeight()/3));
							sq3=1;
							CrossOrCircle=true;
							computer(i);
						}
					}
					else if(e.getX()>getWidth()/3&&e.getX()<=2*getWidth()/3)
					{
						if(e.getY()<=getHeight()/3&&sq4==0) 
						{
							cross(getWidth()/3+GRID_WIDTH, 0);
							sq4=1;
							CrossOrCircle=true;
							computer(i);
						}
						else if(e.getY()>getHeight()/3&&e.getY()<=2*getHeight()/3&&sq5==0) 
						{
							cross(getWidth()/3+GRID_WIDTH, getHeight()/3+5+GRID_WIDTH);
							sq5=1;
							CrossOrCircle=true;
							computer(i);
						}
						else if(e.getY()>2*getHeight()/3&&sq6==0) 
						{
							cross(getWidth()/3+GRID_WIDTH, 2*(getHeight()/3+GRID_WIDTH));
							sq6=1;
							CrossOrCircle=true;
							computer(i);
						}
					}
					else if(e.getX()>2*getWidth()/3)
					{
						if(e.getY()<=getHeight()/3&&sq7==0) 
						{
							cross(2*(getWidth()/3+GRID_WIDTH), 0);
							sq7=1;
							CrossOrCircle=true;
							computer(i);
						}
						else if(e.getY()>getHeight()/3&&e.getY()<=2*getHeight()/3&&sq8==0) 
						{
							cross(2*(getWidth()/3+GRID_WIDTH), getHeight()/3+5+GRID_WIDTH);
							sq8=1;
							CrossOrCircle=true;
							computer(i);
						}
						else if(e.getY()>2*getHeight()/3&&sq9==0) 
						{
							cross(2*(getWidth()/3+GRID_WIDTH), 2*(getHeight()/3+GRID_WIDTH));
							sq9=1;
							CrossOrCircle=true;
							computer(i);
						}
					}
					check();
					
			}
					
		}
		else if(screens==1)
		{
			if(e.getX()>getWidth()/5&&e.getX()<getWidth()/2)
			{
				if(e.getY()>getHeight()/2&&e.getY()<4*getHeight()/5)
				{
					this.removeAll();
					sq1=0;
					sq2=0;
					sq3=0;
					sq4=0;
					sq5=0;
					sq6=0;
					sq7=0;
					sq8=0;
					sq9=0;
					screens=2;
					choice();
				}
			}
			else if(e.getX()<4*getWidth()/5&&e.getX()>getWidth()/2)
			{
				if(e.getY()>getHeight()/2&&e.getY()<4*getHeight()/5)
					System.exit(0);
			}
		}
		else if (screens==2)
		{
			if(e.getX()>getWidth()/5&&e.getX()<getWidth()/2)
			{
				if(e.getY()>getHeight()/2&&e.getY()<4*getHeight()/5)
				{
					this.removeAll();
					CrossOrCircle=false;
					screens=0;
					grid();
				}
			}
			else if(e.getX()<4*getWidth()/5&&e.getX()>getWidth()/2)
			{
				if(e.getY()>getHeight()/2&&e.getY()<4*getHeight()/5)
				{
					this.removeAll();
					CrossOrCircle=true;
					screens=0;
					grid();
				}
			}
		}
		//cross(e.getX(), e.getY());
	}
	private void computer(int i)
	{
		if(i==1)
		{
			if(sq5==0)
			{
				circle(getWidth()/3+GRID_WIDTH, getHeight()/3+10+GRID_WIDTH);
				sq5=2;
				CrossOrCircle=false;
			}
			else if(sq5!=0)
			{
				circle(0, 2*(GRID_WIDTH+getHeight()/3));
				sq3=2;
				CrossOrCircle=false;
			}
		}
		else if(i==2)
		{
			if(sq1+sq2==2||sq1+sq3==2||sq2+sq3==2)
			{
				if(sq3==0)
				{
					circle(0, 2*(GRID_WIDTH+getHeight()/3));
					sq3=2;
					CrossOrCircle=false;
				} 
				else if(sq2==0)
				{
					
				}
			}
			
		}
	}
	private void computer1()
	{
		int random=rgen.nextInt(1,9);
		
	}
	private RandomGenerator rgen = RandomGenerator.getInstance();
}
