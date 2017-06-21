/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Generator;

import States.Game.MainGame.GameObjects.BlockObject;
import States.Game.MainGame.GameObjects.Chunk;
import States.Game.MainGame.GameObjects.ItemObject;
import States.Game.MainGame.GameObjects.Map;
import States.Game.MainGame.GameObjects.Planet;
import States.Game.MainGame.GameObjects.Player;
import States.Game.MainGame.GameObjects.Space;
import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author Yoseph Alabdulwahab
 */
public class MapMaker {
	
	private static final int CHUNK_NUM = 6;
	private static final int NUM_PLANETS = 6;
	private static final int PLANET_DISTANCE = 1000;
	private static final int MAX_PLANET_SIZE = 500;
	
	public static Map MakeMap(String name, String s)
	{
		long seed;
		try
		{
			if(s == null || s.equals(""))
				seed = Long.valueOf(new SteppableRandom().longAt(0));
			else
			{
				int m =  0;
				String sn = "";
				for(int i = 0; i < s.length(); i++)
				{
					if(i == 0)
						m = (int)(s.charAt(i));
					else
						sn += (int)(s.charAt(i));
				}
				if(sn.length() >= 18)
					sn = sn.substring(sn.length() - 18);
				seed = Long.parseLong(sn);
				seed *= m;
				System.out.println(seed);
			}
			
			SteppableRandom random = new SteppableRandom(seed);
			
			LinkedList<Planet> planets = new LinkedList<>();
			Player player;
			Space space;
			
			for(int p = 0; p < NUM_PLANETS; p++)
			{
				GenerateChunk.setMasterSeed(random.longAt(p));
				
				
				float pposX = p * PLANET_DISTANCE;
				float pposY = 0;
				float pradius = (new SteppableRandom(random.longAt(p)).floatAt(0) * MAX_PLANET_SIZE);
				LinkedList<Chunk> pchunks = new LinkedList<>();
				String pname = "Planet";
				String pimagePath = "planet.png";
				String pplanetID = "planet";
				
				int arrCount = 0;
				
				for(int i = (CHUNK_NUM / -2); i < 0; i++)
				{
					if(i == (CHUNK_NUM / -2))
						pchunks.add(GenerateChunk.makeChunk(i));
					else
						pchunks.add(GenerateChunk.makeChunk(i, pchunks.get(arrCount - 1), false));
					arrCount++;
				}
				
				int start = pchunks.size() - 1;
				
				for(int i = 0; i < CHUNK_NUM / 2; i++)
				{
						pchunks.add( GenerateChunk.makeChunk(i, pchunks.get(arrCount - 1), true));
						arrCount++;
				}
				planets.add(new Planet(pposX, pposY, pradius, p, pplanetID, pname, pimagePath, pchunks, seed));
			}
			
			player = new Player(0F, (float)(GenerateChunk.findHeighstBlock(0, planets.get(0).getChunks().get(CHUNK_NUM/2).getBlocks()) + 2), 20, "planet-0", new LinkedList<ItemObject>());
			space = new Space(new LinkedList<Chunk>());
			
			return new Map(name, seed, false, planets, space, player);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
