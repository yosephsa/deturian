/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package States.MenuStates.MenuObjects;

import Game.Tread;
import util.RL;
import Input.Mouse;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author Yoseph Alabdulwahab
 */
public class EarthObject extends ClickableObject{
	
	private static final int X = 0;
	private static final int Y = 1;
	private int currentEarth;
	
	private int speed;
	private int pos[] = new int[2];
	
	private int eTime;
	
	private int width;
	private int height;
	
	private BufferedImage[] i = new BufferedImage[19]; 
	
	public EarthObject(int xCord, int yCord, int w, int speed)
	{
		BufferedImage master = RL.loadImage("EarthSprite.png");
		width = w;
		height = w;
		pos[X] = xCord - width / 2;
		pos[Y] = yCord - height / 2;
		
		this.speed = speed;
		currentEarth = 0;
		
		 for(int y = 0; y < 4; y++)
			for(int x = 0; x < 4; x++)
			{
				if(x + (y * 4) == 12)
				{
					break;
				}
				if(Tread.debug)
					System.out.println(x + (y * 4));
				i[x + (y * 4)] = master.getSubimage(x * (master.getWidth() / 4), y * (master.getHeight() / 4), master.getWidth() / 4, master.getHeight() / 4);
			}
			
	}
	
	@Override
	public void update(Graphics2D g)
	{
		eTime++;
		if(eTime == speed)
		{
			turnEarth();
			eTime = 0;
		}
	}
	
	@Override
	public void drawSelf(Graphics2D g)
	{
		g.drawImage(i[currentEarth], pos[X], pos[Y], width, height, null);
	}
	/**
	 * set to next image.<!-- --> if at limit start back from image 0.
	 */
	private void turnEarth()
	{
		currentEarth++;
		if(currentEarth == 12)
			currentEarth = 0;
	}
	
	@Override
	public void tryClick(MouseEvent e)
	{
		if(Mouse.isOver(pos[X], pos[Y], pos[X] + width, pos[Y] + height))
		{
			performAction();
		}
	}
	
	@Override
	public void performAction()
	{
		System.out.println("Hello Fewllow thing I am Detrus, How are you?");
	}

}
