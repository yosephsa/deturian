/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package States.Game.MainGame.GameObjects;

import java.awt.Graphics2D;

/**
 *
 * @author Yoseph Alabdulwahab
 */
public class ItemObject {
	
	protected float width, height, posX, posY;;
	protected int strength;
	protected String texture;
	
	public ItemObject(float x, float y, String t, int s)
	{
		posX = x;
		posY = y;
		texture = t;
		strength = s;
		width = height = 1F;
	}
	
	public void update(Graphics2D g)
	{
		
	}
	
	public void drawSelf(Graphics2D g, Camera c)
	{
		
	}
	
	public float getWidth()
	{
		return width;
	}
	
	public float getHeight()
	{
		return height;
	}
	
	public float getPosX()
	{
		return posX;
	}
	
	public float getPosY()
	{
		return posY;
	}
	
	public int getStrength()
	{
		return strength;
	}
	
	public String getTexture()
	{
		return texture;
	}

}
