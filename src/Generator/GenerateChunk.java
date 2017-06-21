/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Generator;

import States.Game.MainGame.GameObjects.*;
import java.util.LinkedList;

/**
 *
 * @author Yoseph Alabdulwahab
 */
public class GenerateChunk {
	
	private static long masterSeed;
	private static SteppableRandom random;
	private static final int HEIGHT_AVERAGE = 40;
	private static final int RANGE = 20;
	private static final int GRASS_HEIGHT = 6;
	private static final int TOTAL_IRON = 35;
	
	public static final boolean RIGHT = true, LEFT = false;
	
	public static void setMasterSeed(long seed)
	{
		masterSeed = seed;
		random = new SteppableRandom(seed);
	}
	
	public static Chunk makeChunk(int pos)
	{
		int seedIndex;
		
		if(pos > 0)
			seedIndex = pos * 2 - 1;
		else
			seedIndex = pos * (-2);
		
		SteppableRandom chunkRan = new SteppableRandom(random.longAt(seedIndex));
		
		int height = (int)(chunkRan.nextFloat() * RANGE) + HEIGHT_AVERAGE;
		
		BlockObject[][] blocks = new BlockObject[Chunk.HEIGHT][Chunk.WIDTH];
		
		fill(0, blocks[0].length - 1, 0, height, blocks, BlockStone.class.getName());
		fill(0, blocks[0].length - 1, height - GRASS_HEIGHT, height, blocks, BlockDirt.class.getName());
		fill(0, blocks[0].length - 1, height, height, blocks, BlockGrass.class.getName());
		
		int numberOfIron = (int)(chunkRan.nextFloat() * TOTAL_IRON);
		
		for(int i = 0; i < numberOfIron; i++)
		{
			int top =  height - GRASS_HEIGHT;
			int bottom = 15;
			int right = blocks[0].length - 1;
			int left = 0;
			
			int posX = (int)(random.nextFloat() * (right - left)) + left;
			int posY = (int)(random.nextFloat() * (top - bottom)) + bottom;
			
			fill(posX, posX, posY, posY, blocks, BlockIronOre.class.getName());
			
		}
		
		return new Chunk(blocks, new LinkedList<ItemObject>(), pos);
	}
	
	public static Chunk makeChunk(int pos, Chunk lastChunk, boolean rightChunk)
	{
		int seedIndex;
		int height;
		
		if(pos > 0)
			seedIndex = pos * 2 - 1;
		else
			seedIndex = pos * (-2);
		
		SteppableRandom chunkRan = new SteppableRandom(random.longAt(seedIndex));
		
		BlockObject[][] blocks = new BlockObject[Chunk.HEIGHT][Chunk.WIDTH];
		BlockObject[][] pastBlocks = lastChunk.getBlocks();
			
		
		if(rightChunk)
			height = findHeighstBlock(0, pastBlocks) + (int)(chunkRan.nextFloat() * 3) - 1;
		else
			height = findHeighstBlock(pastBlocks[0].length - 1, pastBlocks) + (int)(chunkRan.nextFloat() * 3) - 1;
		
		fill(0, blocks[0].length - 1, 0, height, blocks, BlockStone.class.getName());
		fill(0, blocks[0].length - 1, height - GRASS_HEIGHT, height, blocks, BlockDirt.class.getName());
		fill(0, blocks[0].length - 1, height, height, blocks, BlockGrass.class.getName());
		
		int numberOfIron = (int)(chunkRan.nextFloat() * TOTAL_IRON);
		
		for(int i = 0; i < numberOfIron; i++)
		{
			int top =  height - GRASS_HEIGHT * 2;
			int bottom = 15;
			int right = blocks[0].length - 1;
			int left = 0;
			
			int posX = (int)(random.nextFloat() * (right - left)) + left;
			int posY = (int)(random.nextFloat() * (top - bottom)) + bottom;
			
			fill(posX, posX, posY, posY, blocks, BlockIronOre.class.getName());
			
		}
		
		return new Chunk(blocks, new LinkedList<ItemObject>(), pos);
	}
	
	/**
	 * returns the highest block in an x coordinate from a 2d array
	 * @param x
	 * @param bloc
	 * @return 
	 */
	public static int findHeighstBlock(int x, BlockObject[][] bloc)
	{
		for(int i = bloc.length - 1; i >= 0; i--)
		{
			if(bloc[i][x] != null)
				return i;
		}
		System.out.println("Could Not Locate Previos Chunk");
		return HEIGHT_AVERAGE;
		
	}
	
	/**
	 * fills an area from left (inclusive) to right (inclusive) and from bottom (inclusive) to top (inclusive).
	 * @param startX
	 * @param endX
	 * @param startY
	 * @param endY
	 * @param blocks
	 * @param name 
	 */
	private static void fill(int startX, int endX, int startY, int endY, BlockObject[][] blocks, String name)
	{
		LinkedList<BlockObject> bloc = new LinkedList<>();
		for(int y = startY; y <= endY; y++)
			for(int x = startX; x <= endX; x++)
			{
				try
				{
					blocks[y][x] = (BlockObject)(Class.forName(name).getConstructor(float.class, float.class, int.class).newInstance(x, y, 20));
				}
				catch(Exception e)
				{
					System.out.println("Failed to fill chunks, look in Generator.GenerateChunk > fill(int, int, int, int, BlockObject[][], String)");
				}
			}
	}

}
