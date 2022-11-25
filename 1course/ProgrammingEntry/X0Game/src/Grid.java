import java.awt.Color;

import acm.graphics.*;
import acm.program.GraphicsProgram;
public class Grid extends GraphicsProgram
{
	public void grid()
	{
		GRect lineV1 = new GRect(getWidth()/30, getHeight());
		GRect lineV2 = new GRect(getWidth()/30, getHeight());
			lineV1.setFilled(true);
			lineV2.setFilled(true);
				add(lineV1, getWidth()/3-lineV1.getWidth()/2,0);
				add(lineV2, 2*getWidth()/3-lineV2.getWidth()/2,0);
		GRect lineH1 = new GRect(getWidth(), getHeight()/30);
		GRect lineH2 = new GRect(getWidth(), getHeight()/30);
			lineH1.setFilled(true);
			lineH2.setFilled(true);
				add(lineH1, 0, getHeight()/3-lineH1.getHeight()/2);
				add(lineH2,  0, 2*getHeight()/3-lineH2.getHeight()/2);
	}
	public void cross(int x, int y)
	{
		GImage cross = new GImage("C:\\Users\\maxlo\\Saved Games\\—Pngtree—vector cross icon_4184842.png");
		cross.scale(0.18);
		add(cross, x, y);
	}
	public void circle(int x, int y) 
	{
		GImage circle = new GImage("C:\\Users\\maxlo\\Saved Games\\circle-button-thick-outline-634008.png");
		circle.scale(0.25);
		add(circle,23+ x,23+ y);
	}
	public void string(int n)
	{
		GLabel string = new GLabel("");
		if(n==1)
		{
			string.setLabel("'X' wins!");
		}
		else if(n==2)
		{
			string.setLabel("'O' wins!");
		}
		else
		{
			string.setLabel("Draw!");
		}
		GLabel string1 = new GLabel("Try again?");
		string.setFont("Matura MT Script Capitals-70");
		string.setColor(Color.BLACK);
		string1.setFont("Matura MT Script Capitals-70");
		string1.setColor(Color.BLACK);
		add(string, (getWidth()-string.getWidth())/2,(getHeight()-2*string.getHeight())/2-25);
		add(string1, (getWidth()-string1.getWidth())/2,(getHeight()-string1.getHeight())/2);
	}
	public void buttons()
	{
		GLabel yes = new GLabel("YES");
		GLabel no = new GLabel("NO");
		yes.setFont("Matura MT Script Capitals-80");
		no.setFont("Matura MT Script Capitals-80");
		add(yes, (getWidth()-yes.getWidth())/4,(getHeight()+2*yes.getHeight())/2+25);
		add(no, (getWidth()-no.getWidth())/4*3,(getHeight()+2*no.getHeight())/2+25);
	}
	public void choice()
	{
		GLabel string = new GLabel("Choose your side");
		GLabel x = new GLabel("X");
		GLabel o = new GLabel("O");
		string.setFont("Matura MT Script Capitals-70");
		x.setFont("Matura MT Script Capitals-90");
		o.setFont("Matura MT Script Capitals-90");
		add(string, (getWidth()-string.getWidth())/2,(getHeight()-2*string.getHeight())/2-25);
		add(x, (getWidth()-x.getWidth())/4,(getHeight()+2*x.getHeight())/2+25);
		add(o, (getWidth()-o.getWidth())/4*3,(getHeight()+2*o.getHeight())/2+25);
		GRect frame1=new GRect(200, 200);
		add(frame1,  x.getX()-(frame1.getWidth()-x.getWidth())/2, x.getY()+(x.getWidth())/2-x.getHeight()-frame1.getHeight()/4);
		GRect frame2=new GRect(200, 200);
		add(frame2,  o.getX()-(frame2.getWidth()-o.getWidth())/2, o.getY()+(o.getWidth())/2-o.getHeight()-frame2.getHeight()/4);
	}
}
