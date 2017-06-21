/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package States.Game.MainGame.GameObjects;

import util.RL;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Yoseph Alabdulwahab
 */
public class BlockCobble extends BlockObject{
	
	private static boolean hasNotLoaded = true;
	private static BufferedImage image;
	
	public BlockCobble(float x, float y, int s)
	{
		super(x, y, 1F, 1F, "BlockCobble.png", false, s);
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
			}
	}
	
	@Override
	public void update(Graphics2D g, Camera c, int chunk)
	{
		super.update(g, c, chunk);
	}
	
	@Override
	public void drawSelf(Graphics2D g, Camera c, int p)
	{
		drawBlock(g, c, p, image);
	}

}
