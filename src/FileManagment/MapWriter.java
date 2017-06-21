/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FileManagment;

import States.Game.MainGame.GameObjects.BlockObject;
import States.Game.MainGame.GameObjects.Chunk;
import States.Game.MainGame.GameObjects.ItemObject;
import States.Game.MainGame.GameObjects.Map;
import States.Game.MainGame.GameObjects.Planet;
import States.Game.MainGame.GameObjects.Player;
import States.Game.MainGame.GameObjects.Space;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;

/**
 *
 * @author Yoseph Alabdulwahab
 */
public class MapWriter {

	private static final String PATH =  System.getProperty("user.home")  + "\\.Deturian\\saves\\";
	
	public static boolean WriteMap(Map map)
	{
		String mapName = map.getMapName();
		LinkedList<Planet> planets = new LinkedList<>();
		map.getPlanets();
		planets.addAll(map.getPlanets());
		
		Player player = map.getPlayer();
		Space space = map.getSpace();
		long seed = map.getSeed();
		
		File directory = new File(PATH + mapName + "/");
		directory.mkdir();
		MapClearer.clearDir(directory.getAbsoluteFile());
		for(Planet planet: planets)
			try
			{
				File curFile = new File(directory.getAbsolutePath() + "\\" + planet.getPlanetID() + "-" + planet.getNumber() + ".planet");
				curFile.createNewFile();
				BufferedWriter br = new BufferedWriter(new FileWriter(curFile.getAbsolutePath()));
				br.write("name=\"" + planet.getName() + "\";");
				br.newLine();
				br.write("position={" + planet.getPosX() + "," + planet.getPosY() + "};");
				br.newLine();
				br.write("radius=" + planet.getRadius() + ";");
				br.newLine();
				br.write("image=\"" + planet.getImagePath() + "\";");
				LinkedList<Chunk> chunks = new LinkedList<Chunk>();
				chunks.addAll(planet.getChunks());
				
				for(Chunk chunk: chunks)
				{
					br.newLine();
					String line = "chunk(" + chunk.getPos() + ")={";
					for(BlockObject block: chunk.getBlocksList())
					{
						if(block != null)
						{
							String blockName = block.getClass().getName().substring(33);
							line += "(";
							line += blockName + ",";
							line += block.getPosX() + ",";
							line += block.getPosY() + ",";
							line += block.getStrength() + "),";
						}
					}
					
					for(ItemObject item: chunk.getItems())
					{
						if(item != null)
						{
							String itemName = item.getClass().getName().substring(33);
							line += "(";
							line += itemName + ",";
							line += item.getPosX() + ",";
							line += item.getPosY() + ",";
							line += item.getStrength() + "),";
						}
					}
					if(line.contains(","))
						line = line.substring(0, line.length() - 1);
					line += "};";
					br.write(line);
				}
				br.close();
			}
			catch(Exception e)
			{
				System.out.println("Could not make File with name \n" + directory.getAbsolutePath() + "/" + planet.getPlanetID() + "-" + planet.getNumber() + ".planet");
				e.printStackTrace();
			}
			
		try
		{
			File curFile = new File(directory.getAbsolutePath() + "\\" + "player.player");
			curFile.createNewFile();
			BufferedWriter br = new BufferedWriter(new FileWriter(curFile.getAbsolutePath()));
			br.write("name=\"" + player.getName() + "\";");
			br.newLine();
			br.write("location=\"" + player.getLocation() + "\";");
			br.newLine();
			br.write("position={" + player.getPosX() + "," + player.getPosY() + "};");
			br.newLine();
			br.write("health=" + player.getStrength() + ";");
			br.newLine();
			String line = "items={";
			for(ItemObject item: player.getInventory())
			{
				String itemName = item.getClass().getName().substring(33);
				line += "(";
				line += itemName + ",";
				line += item.getPosX() + ",";
				line += item.getPosY() + ",";
				line += item.getStrength() + "),";
			}
			line = line.substring(0, line.length() - 1);
			line += "};";
			br.write(line);
			br.close();
		}
		catch(Exception e)
		{
			System.out.println("Could not make player.player file");
		}
		
		try
		{
			File curFile = new File(directory.getAbsolutePath() + "\\" + "space.space");
			curFile.createNewFile();
			BufferedWriter br = new BufferedWriter(new FileWriter(curFile.getAbsolutePath()));
			LinkedList<Chunk> chunks = new LinkedList<Chunk>();
			chunks.addAll(space.getChunks());
				
				for(int i = 0; i < chunks.size(); i++)
				{
					if(i != 0)
						br.newLine();
					String line = "chunk(" + chunks.get(i).getPos() + ")={";
					for(BlockObject block: chunks.get(i).getBlocksList())
					{
						String blockName = block.getClass().getName().substring(33);
						line += "(";
						line += blockName + ",";
						line += block.getPosX() + ",";
						line += block.getPosY() + ",";
						line += block.getStrength() + "),";
					}
					
					for(ItemObject item: chunks.get(i).getItems())
					{
						String itemName = item.getClass().getName().substring(33);
						line += "(";
						line += itemName + ",";
						line += item.getPosX() + ",";
						line += item.getPosY() + ",";
						line += item.getStrength() + "),";
					}
					line = line.substring(0, line.length() - 1);
					line += "};";
					br.write(line);
				}
				
				br.newLine();
				br.write("seed=" + seed + ";");
				br.close();
			
		}
		catch(Exception e)
		{
			System.out.println("Could not make space.space file");
		}
		
		System.out.println("Map Saved!");
		
		return true;
	}
	


}
