/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package States.Game.MainGame.GameObjects;

import util.RL;
import Game.Tread;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 *
 * @author Yoseph Alabdulwahab
 */
public class Space {
	BufferedImage image;
	LinkedList<Chunk> chunks = new LinkedList<>();
	
	public Space(LinkedList<Chunk> chunks)
	{
		this.chunks.clear();
		this.chunks.addAll(chunks);
		image = RL.loadImageRepeating("BlankSpace.png", (int)Tread.WIDTH, (int)Tread.HEIGHT);
	}
	
	public void update(Graphics2D g, Camera c, Player p)
	{
		
	}
	
	public void drawSelf(Graphics2D g, Camera c, Player p)
	{
		
	}
	
	public LinkedList<Chunk> getChunks()
	{
		return chunks;
	}

}
