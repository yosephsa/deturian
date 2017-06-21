/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package States.Game.MainGame.GameObjects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Yoseph Alabdulwahab
 */
public class BlockDummy extends BlockObject{
	
	private static BufferedImage image;
	
	public BlockDummy(float x, float y, float w, float h, boolean c)
	{
		super(x, y, w, h, "dummy", c, 20);
	}

	
	protected void makeImage(String path)
	{
	}
	
	@Override
	public void update(Graphics2D g, Camera c, int chunk)
	{
		super.update(g, c, chunk);
	}
	@Override
	public void drawSelf(Graphics2D g, Camera c, int p)
	{
		
	}
}
