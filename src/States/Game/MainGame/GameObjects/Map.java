/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package States.Game.MainGame.GameObjects;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

/**
 *
 * @author Yoseph Alabdulwahab
 */
public class Map {
	
	private Space space;
	private LinkedList<Planet> planets = new LinkedList<>();
	
	private long seed;
	
	private static int cellSize = 30;
	
	private Player player;
	private Camera camera;
	private String mapName;
	private boolean tutorial;
	
	

	
	public Map(String mapName, long seed, boolean tutorial, LinkedList<Planet> planets, Space space, Player player)
	{
		this.planets.addAll(planets);
		this.space = space;
		this.player = player;
		camera = new Camera(player.getPosX(), player.getPosY(), player.getLocation());
		this.mapName = mapName;
		this.tutorial = tutorial;
		this.seed = seed;
	}
	
	/**
	 * initializes the map
	 */
	public Map()
	{
		
	}
	
	/**
	 * calls the init() method for all players and blocks.
	 */
	public void init()
	{
		
	}
	
	/**
	 * calls the update method for all players and blocks.
	 * 
	 * @param g Graphics2D
	 */
	public void update(Graphics2D g)
	{
		camera.track(player);
		String location = camera.getLocation();
		if(location.contains("planet"))
		{
			getPlanet(Integer.parseInt(location.substring(location.indexOf("-") + 1))).update(g, camera, player);
		}
		else if(location.contains("space"))
		{
			space.update(g, camera, player);
		}
		else
		{
			System.out.println("Game versions might not compatible, missing " + location);
		}
	}
	
	/**
	 * calls the drawSelf method for all blocks and players
	 * @param g 
	 */
	public void drawSelf(Graphics2D g)
	{
		camera.track(player);
		String location = camera.getLocation();
		if(location.contains("planet"))
		{
			getPlanet(Integer.parseInt(location.substring(location.indexOf("-") + 1))).drawSelf(g, camera, player);
		}
		else if(location.contains("space"))
		{
			space.drawSelf(g, camera, player);
		}
		else
		{
			System.out.println("Game versions might not compatible, missing " + location);
		}
	}
	
	public static void setCellSize(int c)
	{
		cellSize = c;
	}
	
	public static int getCellSize()
	{
		return cellSize;
	}
	
	/**
	 * gets the name of the current Map.
	 * 
	 * @return map name
	 */
	public String getMapName()
	{
		return mapName;
	}
	
	public LinkedList<Planet> getChunks()
	{
		return planets;
	}
	
	public Planet getPlanet(int i)
	{
		return planets.get(i);
	}
	
	/**
	 * gets the player of the current map.
	 * 
	 * @return player info
	 */
	public Player getPlayer()
	{
		return player;
	}
	
	/**
	 * Gets the array list of all the planets in this map.
	 * 
	 * @return ArrayList of all planets.
	 */
	public LinkedList<Planet> getPlanets()
	{
		return planets;
	}
	
	public long getSeed()
	{
		return seed;
	}
	
	/**
	 * gets weather this map has tutorial enabled.
	 * 
	 * @return tutorial status.
	 */
	public boolean isTutorial()
	{
		return tutorial;
	}

	public Space getSpace()
	{
		return space;
	}
	
	public void tryClick(MouseEvent e)
	{
		String location = camera.getLocation();
		getPlanet(Integer.parseInt(location.substring(location.indexOf("-") + 1))).tryClick(e, camera);
	}
	
	public void tryPress(KeyEvent e)
	{
		if(e == null)
		{
			String location = camera.getLocation();
			getPlanet(Integer.parseInt(location.substring(location.indexOf("-") + 1))).tryPress(camera);
		}
	}

}
