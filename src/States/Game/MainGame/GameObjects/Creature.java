/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package States.Game.MainGame.GameObjects;

import Game.Tread;
import util.Collision;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Yoseph Alabdulwahab
 */
public abstract class Creature extends BlockObject{
	
	
	
	private static boolean hasNotLoaded = true;
	
	protected float volocityY = 0;
	protected float volocityX = 0;
	protected String location;
	protected boolean canJump = true;
	
	/**
	 * 
	 * @param x position
	 * @param y position
	 * @param s health level
	 * @param location
	 * @param inventory
	 */
	public Creature(float x, float y, float w, float h, int s, String n, String l, boolean ip, String t)
	{
		super(x, y, w, h, t, true, s);
		location = l;
	}
	
	public int getChunkNum()
	{
		float chunkNum = posX / Chunk.WIDTH;
		if(chunkNum < 0)
			chunkNum -= 1;
		return (int)chunkNum;
	}
	
	public void drawSelf(Graphics2D g, Camera c, int pos)
	{
		System.out.println("ERROR, CORRUpt CODE");
	}
	
	public void update(Graphics2D g, Camera c)
	{
		System.out.println("ERROR, CORRUpt CODE");
	}
		
	/**
	 * draws the player to the given graphics
	 * 
	 * @param g graphics2d
	 */
	public void update(Graphics2D g, Camera c, Planet p)
	{
		move(p);
		fixMove(p);
		
		posX += volocityX;
		posY += volocityY;
	}
	
	public abstract void drawSelf(Graphics2D g, Camera c, Planet p);
	
	protected void drawCreature(Graphics2D g, Camera c, BufferedImage image)
	{
		float py = (c.getPosY() - posY) + Tread.HEIGHT / Map.getCellSize() / 2;
		float px = (posX - c.getPosX()) + (Tread.WIDTH / Map.getCellSize() / 2);
		
		g.drawImage(image ,(int)(px * Map.getCellSize() + 0.5), (int)(py * Map.getCellSize() + 0.5), (int)(width * Map.getCellSize()), (int)(height * Map.getCellSize()), null);
	}
	
	/**
	 * checks whether player can move with specified actions.
	 * 
	 * @return capability to move.
	 */
	protected boolean fixMove(Planet p)
	{
		int wd = (int)(width + 1);
		int ht = (int)(height + 1);
		//Write collision detection based off of blocks around player.
		float pPosX = posX;
		float pPosY = posY;
		BlockObject[] blocks = new BlockObject[(int)(wd + 4) * ((int)ht + 4)];
		
		for(int y = -2; y < ht + 2; y++)
			for(int x = -2; x < wd + 2; x++)
			{
				float bPosX = pPosX + x;
				float bPosY = pPosY + y;
				float chunkNum = bPosX / Chunk.WIDTH;
				
				if(chunkNum < 0)
					chunkNum -= 1;
				
				bPosX = bPosX - (int)chunkNum * Chunk.WIDTH;
				
				Chunk chunk = p.getChunk((int)chunkNum);
				
				if(bPosY >= 0 && bPosY < Chunk.HEIGHT && bPosX >= 0 && bPosX < Chunk.WIDTH)
					blocks[(int)(((y + 2) * (wd + 4)) + x + 2)] = chunk.getBlock((int)bPosX, (int)bPosY);
			}
		
		for(int i = 0; i < blocks.length; i++)
		{
			
			if(blocks[i] != null )
			{
				int chunkNum = (int)(posX / Chunk.WIDTH);
				
				if(posX / Chunk.WIDTH < 0)
					chunkNum -= 1;
				if((posX - chunkNum * Chunk.WIDTH) - blocks[i].getPosX() > 10)
					chunkNum += 1;
				else if((posX - chunkNum * Chunk.WIDTH) - blocks[i].getPosX() < -10)
					chunkNum -= 1;
				
				if(volocityX > 0F && Collision.isColliding("right", this, blocks[i], chunkNum))
				{
					volocityX = 0;
				}
				if(volocityX < 0F && Collision.isColliding("left", this, blocks[i], chunkNum))
				{
					volocityX = 0;
				}
				
				if(volocityY > 0F && Collision.isColliding("up", this, blocks[i], chunkNum))
				{
					volocityY = 0;
				}
				if(volocityY < 0F && Collision.isColliding("down", this, blocks[i], chunkNum))
				{
					volocityY = 0;
					canJump = true;
				}
			}
		}
			
		for(int i = 0; i < blocks.length; i++)
		{
			
			if(blocks[i] != null )
			{
				int chunkNum = (int)(posX / Chunk.WIDTH);
				
				if(posX / Chunk.WIDTH < 0)
					chunkNum -= 1;
				if((posX - chunkNum * Chunk.WIDTH) - blocks[i].getPosX() > 10)
					chunkNum += 1;
				else if((posX - chunkNum * Chunk.WIDTH) - blocks[i].getPosX() < -10)
					chunkNum -= 1;
				
				if(volocityX > 0F && Collision.isGoingToCollide("right", volocityX, this, blocks[i], chunkNum))
				{
					volocityX = 0;
					posX = blocks[i].getPosX() + chunkNum * Chunk.WIDTH - width;
				}
				if(volocityX < 0F && Collision.isGoingToCollide("left", volocityX, this, blocks[i], chunkNum))
				{
					volocityX = 0;
					posX = blocks[i].getPosX() + chunkNum * Chunk.WIDTH + blocks[i].getWidth();
				}
				if(volocityY > 0F && Collision.isGoingToCollide("up", volocityY, this, blocks[i], chunkNum))
				{
					volocityY = 0;
					posY = blocks[i].getPosY() - blocks[i].getHeight();
				}
				if(volocityY < 0F && Collision.isGoingToCollide("down", volocityY, this, blocks[i], chunkNum))
				{
					volocityY = 0;
					posY = blocks[i].getPosY() + height;
					
				}
			}
		}
		return true;
	}
	
	/**
	 * performs specified movement actions.
	 */
	protected abstract void move(Planet p);
	
	public String getName()
	{
		return name;
	}
	
	public String getLocation()
	{
		return location;
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

}
