/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package States.Game.MainGame.GameObjects;

import Game.Tread;
import Generator.GenerateChunk;
import Generator.SteppableRandom;
import Input.Mouse;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

/**
 *
 * @author Yoseph Alabdulwahab
 */
public class Planet {
	
	private float posX;
	private float posY;
	private float radius;
	private int planetNumber;
	private LinkedList<Chunk> chunks = new LinkedList<>();
	/**
	 * a string that says it is a planet, and holds the number of the planet.
	 * ex. "planet12".
	 * no planet has the same id as another.
	 */
	private String identification;
	private String name;
	private String imagePath;
	private String planetID;
	private long seed;
	
	/**
	 * initializes all the fields.
	 * 
	 * @param posX
	 * @param posY
	 * @param radius
	 * @param planetID
	 * @param name
	 * @param chunks
	 * @param image
	 */
	public Planet(float posX, float posY, float radius, int planetNumber, String planetID, String name, String image, LinkedList<Chunk> chunks, long seed)
	{
		this.posX = posX;
		this.posY = posY;
		this.radius = radius;
		this.planetID = planetID;
		this.name = name;
		this.chunks.clear();
		this.chunks.addAll(chunks);
		imagePath = image;
		this.planetNumber = planetNumber;
		this.seed = seed;
	}
	
	public void init()
	{
	}
	
	public void update(Graphics2D g, Camera c, Player p)
	{
		c.track(p);
		if(c.getLocation().toLowerCase().contains("planet"))
		{
			float camPosChunk = c.getPosX() / Chunk.WIDTH;
			
			int chunkStartPosition = chunks.get(0).getPos();
			int chunkEndPosition = chunks.get(chunks.size() - 1).getPos();
			int start = (int)(camPosChunk - chunkStartPosition - 4), end = (int)(camPosChunk - chunkStartPosition + 4);
			
			if(start <= 0)
			{
				System.out.println("Generating chunk left");
				GenerateChunk.setMasterSeed((new SteppableRandom((new SteppableRandom(seed)).longAt(planetNumber)).longAt(Math.abs(chunks.get(0).getPos() * 2))));
				chunks.add(0, GenerateChunk.makeChunk(chunkStartPosition - 1, chunks.get(0), GenerateChunk.LEFT));
				start = 0;
			}
			if(end >= chunks.size() - 1)
			{
				System.out.println("Generating chunk right");
				GenerateChunk.setMasterSeed((new SteppableRandom((new SteppableRandom(seed)).longAt(planetNumber)).longAt(Math.abs(chunks.get(0).getPos() * 2 - 1))));
				chunks.add(GenerateChunk.makeChunk(chunkEndPosition + 1, chunks.get(chunks.size() - 1), GenerateChunk.RIGHT));
				end = chunks.size() - 1;
			}

			for(int i = start; i < end; i++)
			{
				chunks.get(i).update(g, c);
			}
			p.update(g, c, this);
		}
	}
	
	public void drawSelf(Graphics2D g, Camera c, Player p)
	{
		if(c.getLocation().toLowerCase().contains("planet"))
		{
			float camPosChunk = c.getPosX() / Chunk.WIDTH;
			
			int chunkStartPosition = chunks.get(0).getPos();
			int chunkEndPosition = chunks.get(chunks.size() - 1).getPos();
			int start = (int)(camPosChunk - chunkStartPosition - 1), end = (int)(camPosChunk - chunkStartPosition + 2);
			
			if(start < 0)
				start = 0;
			if(end > chunks.size() - 1)
				end = chunks.size() - 1;

			for(int i = start; i < end; i++)
			{
				chunks.get(i).drawSelf(g, c);
			}
			p.drawSelf(g, c, this);
			Font f = g.getFont();
			g.setFont(new Font("name", Font.PLAIN, 20));
			g.drawString("'" + camPosChunk + "'", Tread.WIDTH / 10 * 4, Tread.HEIGHT / 10);
			g.setFont(f);
		}
	}

	public void reChunk(LinkedList<BlockObject> blocks)
	{
		
	}
	
	/**
	 * 
	 * @return Name String in game name.
	 */
	public String getName()
	{
		return name;
	}
	
	public int getNumber()
	{
		return planetNumber;
	}
	
	public String getPlanetID()
	{
		return planetID;
	}
	
	public LinkedList<Chunk> getChunks()
	{
		return chunks;
	}
	
	public Chunk getChunk(int pos)
	{
		int sPos = chunks.get(0).getPos();
		
		return chunks.get(pos - sPos);
	}
	
	public float getPosX()
	{
		return posX;
	}
	
	public float getPosY()
	{
		return posY;
	}
	
	public float getRadius()
	{
		return radius;
	}
	
	public String getImagePath()
	{
		return imagePath;
	}
	
	@Override
	public String toString()
	{
		return "planet" + planetID;
	}
	
	public void tryClick(MouseEvent e, Camera c)
	{
		float posMX = (Mouse.mousePos[Mouse.CORD_X] - Tread.WIDTH / 2) / (float)Map.getCellSize();
		posMX += c.getPosX();
		float posMY = (Tread.HEIGHT - Mouse.mousePos[Mouse.CORD_Y] + Map.getCellSize() - Tread.HEIGHT / 2) / (float)Map.getCellSize();
		posMY += c.getPosY();
		System.out.println(posMX + " " + posMY);
		
		float chunkNum = posMX / Chunk.WIDTH;
		
		if(chunkNum < 0)
			chunkNum -= 1;
		
		chunkNum = (int)chunkNum;
		
		float posBX = posMX - (chunkNum * Chunk.WIDTH);
		float posBY = posMY;
		
		chunks.get((int)chunkNum - chunks.get(0).getPos()).tryClick(e, c, (int)posBX, (int)posBY);
	}
	
	public void tryPress(Camera c)
	{
		float posMX = (Mouse.mousePos[Mouse.CORD_X] - Tread.WIDTH / 2) / (float)Map.getCellSize();
		posMX += c.getPosX();
		float posMY = (Tread.HEIGHT - Mouse.mousePos[Mouse.CORD_Y] + Map.getCellSize() - Tread.HEIGHT / 2) / (float)Map.getCellSize();
		posMY += c.getPosY();
		System.out.println(posMX + " " + posMY);
		
		float chunkNum = posMX / Chunk.WIDTH;
		
		if(chunkNum < 0)
			chunkNum -= 1;
		
		chunkNum = (int)chunkNum;
		
		float posBX = posMX - (chunkNum * Chunk.WIDTH);
		float posBY = posMY;
		
		chunks.get((int)chunkNum - chunks.get(0).getPos()).tryPress(c, (int)posBX, (int)posBY);
	}

}