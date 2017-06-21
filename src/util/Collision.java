package util;

import States.Game.MainGame.GameObjects.*;

/**
 *
 * @author Yoseph Alabdulwahab
 */
public class Collision {
	
	public static boolean isColliding(String d, BlockObject b1, BlockObject b2, int chunkNum)
	{
		BlockDummy block1, block2;

		float height = b1.getHeight(), width = b1.getWidth(), posX = b1.getPosX(), posY = b1.getPosY();
		if(b1.getWidth() == b2.getWidth())
		{
			width = b1.getWidth() - 1/(float)Map.getCellSize() * 2F;
			posX = b1.getPosX() + 1/(float)Map.getCellSize() * 1F;
		}
		if(b1.getHeight() == b2.getHeight())
		{
			height = b1.getWidth() - 1/(float)Map.getCellSize() * 2F;
			posY = b1.getPosY() - 1/(float)Map.getCellSize() * 1F;
		}
			
		if(b1 instanceof Creature)
			block1 = new BlockDummy(posX, posY, width, height, true);
		else
			block1 = new BlockDummy(posX + chunkNum * Chunk.WIDTH, posY, width, height, true);

		if(b2 instanceof Creature)
			block2 = new BlockDummy(b2.getPosX(), b2.getPosY(), b2.getWidth(), b2.getHeight(), true);
		else
			block2 = new BlockDummy(b2.getPosX() + chunkNum * Chunk.WIDTH, b2.getPosY(), b2.getWidth(), b2.getHeight(), true);
		
		
		if(d.equals("down"))
		{
			BlockObject focal;
			BlockObject block;
			if(block1.getWidth() > block2.getWidth())
			{
				focal = block1;
				block = block2;
			}
			else
			{
				focal = block2;
				block = block1;
			}
			System.out.println("focal " + focal + "; block " + block);
			if(block1.getPosY() - block1.getHeight() == block2.getPosY())
				if((block.getPosX() > focal.getPosX() && block.getPosX() < focal.getPosX() + focal.getWidth()) || (block.getPosX() + block.getWidth() > focal.getPosX() && block.getPosX() + block.getWidth() < focal.getPosX() + focal.getWidth()))
				{
					return true;
				}
			System.out.print("");
		}
		if(d.equals("up"))
		{
			BlockObject focal;
			BlockObject block;
			if(block1.getWidth() > block2.getWidth())
			{
				focal = block1;
				block = block2;
			}
			else
			{
				focal = block2;
				block = block1;
			}
			if(block1.getPosY() == block2.getPosY() - block2.getHeight())
				if((block.getPosX() > focal.getPosX() && block.getPosX() < focal.getPosX() + focal.getWidth()) || (block.getPosX() + block.getWidth() > focal.getPosX() && block.getPosX() + block.getWidth() < focal.getPosX() + focal.getWidth()))
				{
					return true;
				}
		}
		
		if(d.equals("left"))
		{
			BlockObject focal;
			BlockObject block;
			if(block1.getHeight() > block2.getHeight())
			{
				focal = block1;
				block = block2;
			}
			else
			{
				focal = block2;
				block = block1;
			}
			
			if(block1.getPosX() == block2.getPosX() + block2.getWidth())
				if((block.getPosY() < focal.getPosY() && block.getPosY() > focal.getPosY() - focal.getHeight()) || (block.getPosY() - block.getHeight() < focal.getPosY() && block.getPosY() - block.getHeight() > focal.getPosY() - focal.getHeight()))
				{
					return true;
				}
		}
		
		if(d.equals("right"))
		{
			BlockObject focal;
			BlockObject block;
			if(block1.getHeight() > block2.getHeight())
			{
				focal = block1;
				block = block2;
			}
			else
			{
				focal = block2;
				block = block1;
			}
			
			if(block1.getPosX() + block1.getWidth() == block2.getPosX())
				if((block.getPosY() < focal.getPosY() && block.getPosY() > focal.getPosY() - focal.getHeight()) || (block.getPosY() - block.getHeight() < focal.getPosY() && block.getPosY() - block.getHeight() > focal.getPosY() - focal.getHeight()))
				{
					return true;
				}
				
		}
		return false;
	}

	public static boolean isGoingToCollide(String d, double v, BlockObject b1, BlockObject b2, int chunkNum)
	{
		BlockDummy block1, block2;
		if(b1.getWidth() != b1.getWidth() && b1.getHeight() != b2.getHeight())
		{
			if(b1 instanceof Creature)
				block1 = new BlockDummy(b1.getPosX(), b1.getPosY(), b1.getWidth(), b1.getHeight(), true);
			else
				block1 = new BlockDummy(b1.getPosX() + chunkNum * Chunk.WIDTH, b1.getPosY(), b1.getWidth(), b1.getHeight(), true);

			if(b2 instanceof Creature)
				block2 = new BlockDummy(b2.getPosX(), b2.getPosY(), b2.getWidth(), b2.getHeight(), true);
			else
				block2 = new BlockDummy(b2.getPosX() + chunkNum * Chunk.WIDTH, b2.getPosY(), b2.getWidth(), b2.getHeight(), true);
		}
		else
		{
			float height = b1.getHeight(), width = b1.getWidth(), posX = b1.getPosX(), posY = b1.getPosY();
			if(b1.getWidth() == b2.getWidth())
			{
				width = b1.getWidth() - 1/(float)Map.getCellSize() * 4F;
				posX = b1.getPosX() + 1/(float)Map.getCellSize() * 2F;
			}
			if(b1.getHeight() == b2.getHeight())
			{
				height = b1.getWidth() - 1/(float)Map.getCellSize() * 4F;
				posY = b1.getPosY() - 1/(float)Map.getCellSize() * 2F;
			}
			
			if(b1 instanceof Creature)
				block1 = new BlockDummy(posX, posY, width, height, true);
			else
				block1 = new BlockDummy(posX + chunkNum * Chunk.WIDTH, posY, width, height, true);

			if(b2 instanceof Creature)
				block2 = new BlockDummy(b2.getPosX(), b2.getPosY(), b2.getWidth(), b2.getHeight(), true);
			else
				block2 = new BlockDummy(b2.getPosX() + chunkNum * Chunk.WIDTH, b2.getPosY(), b2.getWidth(), b2.getHeight(), true);
		}
		if(d.equals("down"))
		{
			BlockObject focal;
			BlockObject block;
			if(block1.getWidth() > block2.getWidth())
			{
				focal = block1;
				block = block2;
			}
			else
			{
				focal = block2;
				block = block1;
			}
			if(block1.getPosY() - block1.getHeight() + v < block2.getPosY() && block1.getPosY() + v > block2.getPosY())
				if((block.getPosX() > focal.getPosX() && block.getPosX() < focal.getPosX() + focal.getWidth()) || (block.getPosX() + block.getWidth() > focal.getPosX() && block.getPosX() + block.getWidth() < focal.getPosX() + focal.getWidth()))
				{
					return true;
				}
		}
		if(d.equals("up"))
		{
			BlockObject focal;
			BlockObject block;
			if(block1.getWidth() > block2.getWidth())
			{
				focal = block1;
				block = block2;
			}
			else
			{
				focal = block2;
				block = block1;
			}
			if(block1.getPosY() + v > block2.getPosY() - block2.getHeight() && block1.getPosY() - block1.getHeight() + v < block2.getPosY() - block2.getHeight())
				if((block.getPosX() > focal.getPosX() && block.getPosX() < focal.getPosX() + focal.getWidth()) || (block.getPosX() + block.getWidth() > focal.getPosX() && block.getPosX() + block.getWidth() < focal.getPosX() + focal.getWidth()))
				{
					return true;
				}
		}
		if(d.equals("left"))
		{
			BlockObject focal;
			BlockObject block;
			if(block1.getHeight() > block2.getHeight())
			{
				focal = block1;
				block = block2;
			}
			else
			{
				focal = block2;
				block = block1;
			}
			
			if(block1.getPosX() + v < block2.getPosX() + block2.getWidth() && block1.getPosX() + block1.getWidth() + v > block2.getPosX() + block2.getWidth())
				if((block.getPosY() < focal.getPosY() && block.getPosY() > focal.getPosY() - focal.getHeight()) || (block.getPosY() - block.getHeight() < focal.getPosY() && block.getPosY() - block.getHeight() > focal.getPosY() - focal.getHeight()))
				{
					return true;
				}
		}
		
		if(d.equals("right"))
		{
			BlockObject focal;
			BlockObject block;
			if(block1.getHeight() > block2.getHeight())
			{
				focal = block1;
				block = block2;
			}
			else
			{
				focal = block2;
				block = block1;
			}
			
			if(block1.getPosX() + block1.getWidth() + v > block2.getPosX() && block1.getPosX() + v < block2.getPosX())
				if((block.getPosY() < focal.getPosY() && block.getPosY() > focal.getPosY() - focal.getHeight()) || (block.getPosY() - block.getHeight() < focal.getPosY() && block.getPosY() - block.getHeight() > focal.getPosY() - focal.getHeight()))
				{
					return true;
				}
		}
		
		
		return false;
	}

}
