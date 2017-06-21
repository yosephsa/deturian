/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package States.Game.MainGame.GameObjects;

import Game.Tread;
import util.RL;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author Yoseph Alabdulwahab
 */
public abstract class BlockObject{
	
	
	/**
	 * make all private and make all objects have those and use get methods to change
	 */
	protected float posX, posY, width, height;
	protected int ID, strength;
	protected String texture;
	protected boolean isPassable;
	protected String name;
	private int count = 0;
	private static BufferedImage[] breakImages;
	private static int breakLength = 1;
	private boolean destroy = false;
	private boolean pressing = false;
	
	
	/**
	 * 
	 * @param x float position
	 * @param y float position
	 * @param w float width
	 * @param h float height
	 * @param t String texture file name
	 * @param ip
	 * @param s float strength level
	 */
	public BlockObject(float x, float y, float w, float h, String t, boolean ip,  int s)
	{
		posX = x;
		posY = y;
		width = w;
		height = h;
		texture = t;
		isPassable = ip;
		strength = s;
		
		if(breakImages == null)
		{
			breakImages = new BufferedImage[10];
			BufferedImage breakSprite = RL.loadImage("breakSprite.png");

			for(int i = 0; i < breakImages.length; i++)
			{
				breakImages[i] = breakSprite.getSubimage((int)(i * breakSprite.getWidth() / breakImages.length), (int)(0), (int)(breakSprite.getWidth() / breakImages.length), breakSprite.getHeight());
			}
		}
		
		makeImage("Blocks/" + t);
	}
	
	protected abstract void makeImage(String path);
	
	public void update(Graphics2D g, Camera c, int chunk)
	{
		
		if(pressing)
		{
			count++;
			System.out.println("tts");
		}
		
	}
	
	public abstract void drawSelf(Graphics2D g, Camera c, int pos);
	
	public void drawBlock(Graphics2D g, Camera c, float chunkNum, BufferedImage image)
	{
		float py = (c.getPosY() - posY) + Tread.HEIGHT / Map.getCellSize() / 2;
		float px = (posX + (chunkNum) * Chunk.WIDTH) - c.getPosX() + (Tread.WIDTH / Map.getCellSize() / 2);
		
		g.drawImage(image, (int)(px * Map.getCellSize() + 0.5), (int)(py * Map.getCellSize() + 0.5), (int)(width * Map.getCellSize()), (int)(height * Map.getCellSize()), null);
	}
	
	/**
	 * checks if this can move
	 * 
	 * @return returns whether it can or can not move
	 */
	private boolean canMove()
	{
		return false;
	}
	
	/**
	 * performs necessary move actions.
	 */
	private void move()
	{
		
	}
	
	/**
	 * returns the identifier of this block.
	 * 
	 * @return block id
	 */
	public int getID()
	{
		return ID;
	}
	
	/**
	 * returns a string that holds information on this block.
	 * 
	 * @return string identifier
	 */
	@Override
	public String toString()
	{
		return "(" + getPosX() + "," + getPosY() + ")";
	}
	
	/**
	 * gets the x position.
	 * @return x position
	 */
	public float getPosX()
	{
		return posX;
	}
	
	/**
	 * gets the y position.
	 * @return y position
	 */
	public float getPosY()
	{
		return posY;
	}
	
	/**
	 * gets the width
	 * @return width
	 */
	public float getWidth()
	{
		return width;
	}
	
	public int getStrength()
	{
		return strength;
	}
	
	/**
	 * gets the height
	 * @return height
	 */
	public float getHeight()
	{
		return height;
	}
	
	public String getTexture()
	{
		return texture;
	}
	public boolean tryClick(MouseEvent e, Camera c, int chunkNum)
	{
		return true;
	}
	
	public boolean tryPress()
	{
		pressing = true;
		return true;
	}
	
	

}
