/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package States.Game.MainGame.GameObjects;

import Input.Keyboard;
import util.RL;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 *
 * @author Yoseph Alabdulwahab
 */
public class Player extends Creature{
	
	private String location;
	private LinkedList<ItemObject> inventory = new LinkedList<>(); 
	private String name;
	
	public static BufferedImage image;
	
	private static boolean hasNotLoaded = true;
	
	
	
	
	/**
	 * 
	 * @param x position
	 * @param y position
	 * @param s health level
	 * @param location
	 * @param inventory
	 */
	public Player(float x, float y, int s, String l, LinkedList<ItemObject> i)
	{
		super(x, y, 1.0F, 2.0F, s, "Player", l, true, "PlayerSprite.png");
		this.inventory.addAll(inventory);
	}
	
	@Override
	protected void makeImage(String path)
	{
		if(hasNotLoaded)
			try
			{
				image = RL.loadImage(path, (int)(width * Map.getCellSize()), (int)(height * Map.getCellSize()));
				hasNotLoaded = false;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	}
		
	/**
	 * draws the player to the given graphics
	 * 
	 * @param g graphics2d
	 */
	@Override
	public void update(Graphics2D g, Camera c, Planet p)
	{
		super.update(g, c, p);
	}
	
	@Override
	public void drawSelf(Graphics2D g, Camera c, Planet p)
	{
		this.drawCreature(g, c, image);
	}
	
	@Override
	protected void move(Planet p)
	{
		if(Keyboard.isKeyDown(KeyEvent.VK_D))
		{
			volocityX += 0.09;
		}
		else if(Keyboard.isKeyDown(KeyEvent.VK_A))
		{
			volocityX -= 0.09;
		}
		else
		{
			volocityX = 0;
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_SPACE) && canJump)
		{
			volocityY = 0.5f;
			canJump = false;
		}
		else
		{
			volocityY -= 0.09;
		}
		
		if(volocityX >= 0.5F)
			volocityX = 0.5F;
		else if(volocityX <= -0.5F)
			volocityX = -0.5F;
		
		if(volocityY >= 0.9F)
			volocityY = 0.9F;
		else if(volocityY <= -0.9F)
			volocityY = -0.9F;
	}
	
	protected BufferedImage getImage()
	{
		return image;
	}

	
	public LinkedList<ItemObject> getInventory()
	{
		return inventory;
	}

}
