/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package States.Game.MainGame.GameObjects;

import Game.Tread;
import Input.Mouse;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author Yoseph Alabdulwahab
 */
public class Chunk {
	
	public static final int WIDTH = 20;
	public static final int HEIGHT = 100;
	
	LinkedList<BlockObject> rawBlocks = new LinkedList<>();
	BlockObject[][] blocks;
	LinkedList<ItemObject> items = new LinkedList<>();
	private int pos;
	
	public Chunk(LinkedList<BlockObject> blocks, LinkedList<ItemObject> it, int pos)
	{
		this.rawBlocks.clear();
		this.rawBlocks.addAll(blocks);
		this.blocks = new BlockObject[HEIGHT][WIDTH];
		for(int i = 0; i < blocks.size(); i++)
		{
			this.blocks[(int)blocks.get(i).getPosY()][(int)blocks.get(i).getPosX()] = blocks.get(i);
		}
		this.pos = pos;
		this.items.clear();
		this.items.addAll(it);
	}
	
	public Chunk(BlockObject[][] blocks, LinkedList<ItemObject> it, int pos)
	{
		this.rawBlocks.clear();
		this.blocks = new BlockObject[HEIGHT][WIDTH];
		this.blocks = Arrays.copyOf(blocks, blocks.length);
		for(int y = 0; y < blocks.length; y++)
			for(int x = 0; x < blocks[y].length; x++)
			{
				rawBlocks.add(blocks[y][x]);
			}
		this.pos = pos;
		this.items.clear();
		this.items.addAll(it);
	}
	
	public void update(Graphics2D g, Camera c)
	{
		float cx = c.getPosX();
		float cy = c.getPosY();
		int startX = (int)(cx - (Tread.WIDTH / Map.getCellSize() / 2)), endX = (int)(cx + (Tread.WIDTH / Map.getCellSize() / 2));
		int startY = (int)(cy - (Tread.HEIGHT / Map.getCellSize() / 2)), endY = (int)(cy + (Tread.HEIGHT / Map.getCellSize() / 2));
		
		if(startX < 0 )
			startX = 0;
		if(startY < 0)
			startY = 0;
		if(endX >= blocks[0].length)
			endX = blocks[0].length - 1;
		if(endY >= blocks.length)
			endY = blocks.length - 1;
		
		for(int y = startY; y < endY; y++)
		{
			for(int x = startX; x < endX; x++)
			{
				if(blocks[y][x] != null)
				{
					blocks[y][x].update(g, c, pos);
				}
			}
		}
		for(int i = 0; i < items.size(); i++)
			items.get(i).update(g);
	}

	
	public void drawSelf(Graphics2D g, Camera c)
	{
		float cx = c.getPosX();
		float cy = c.getPosY();
		int startX = (int)(cx - Tread.WIDTH / Map.getCellSize()), endX = (int)((cx + Tread.WIDTH / Map.getCellSize()));
		int startY = (int)(cy - Tread.HEIGHT / Map.getCellSize()), endY = (int)(cy + Tread.HEIGHT / Map.getCellSize());
		
		if(startX < 0 )
			startX = 0;
		if(startY < 0)
			startY = 0;
		if(endX >= blocks[0].length)
			endX = blocks[0].length - 1;
		if(endY >= blocks.length)
			endY = blocks.length - 1;
		
		
		for(int y = 0; y < blocks.length; y++)
		{
			for(int x = 0; x < blocks[0].length; x++)
			{
				if(blocks[y][x] != null)
				{
					blocks[y][x].drawSelf(g, c, pos);
				}
			}
		}
		
		for(int i = 0; i < items.size(); i++)
			items.get(i).update(g);
	}
	
	public void drawSelf(Graphics2D g, Camera c, Player p) {}
	
	public LinkedList<ItemObject> getItems()
	{
		return items;
	}
	
	public LinkedList<BlockObject> getBlocksList()
	{
		return rawBlocks;
	}
	
	public BlockObject[][] getBlocks()
	{
		return blocks;
	}
	
	public BlockObject getBlock(int x, int y)
	{
		return blocks[y][x];
	}
	
	public int getPos()
	{
		return pos;
	}
	
	public void tryClick(MouseEvent e, Camera c, int posX, int posY)
	{
		if(posY >= 0 && posY < Chunk.HEIGHT && blocks[posY][posX] != null)
		{
			blocks[posY][posX] = null;
			for(int i = 0; i < rawBlocks.size(); i++)
			{
				if(rawBlocks.get(i) != null && rawBlocks.get(i).getPosX() == posX && rawBlocks.get(i).getPosY() == posY)
				{
					rawBlocks.remove(i);
				}
			}
		}
	}
	
	public void tryPress(Camera c, int posX, int posY)
	{
		if(posY >= 0 && posY < Chunk.HEIGHT && blocks[posY][posX] != null)
		{
			for(int i = 0; i < rawBlocks.size(); i++)
			{
				if(rawBlocks.get(i) != null && rawBlocks.get(i).getPosX() == posX && rawBlocks.get(i).getPosY() == posY)
				{
					blocks[posY][posX].tryPress();
				}
			}
		}
	}

}
