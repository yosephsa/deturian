package States.Game.MainGame;

import FileManagment.MapWriter;
import States.Game.MainGame.GameObjects.Map;
import util.RL;
import Game.Tread;
import States.Game.GameStates;
import States.State;
import States.StateC;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Yoseph Alabdulwahab
 */
public class Game extends State{
	
	Map map;
	
	/**
	 * creates the Map object with the given map
	 * @param m Map object
	 */
	public Game(Map m)
	{
		map = m;
	}
	
	/**
	 * calls the init() method in the map class to initialize all blocks
	 */
	@Override
	public void init()
	{
		map.init();
	}
	
	/**
	 * calls the update() method of the mad in order to update all blocks in game
	 * @param g 
	 */
	@Override
	public void update(Graphics2D g)
	{
		map.update(g);
		
		
	}
	
	/**
	 * calls the drawSelf() method in the map class to draw all blocks
	 * @param g 
	 */
	@Override
	public void drawSelf(Graphics2D g)
	{
		g.drawImage(RL.loadImage("Blue.png"), 0, 0, (int)Tread.WIDTH, (int)Tread.HEIGHT, null);
		map.drawSelf(g);
	}
	
	@Override
	public void tryClick(MouseEvent e)
	{
		map.tryClick(e);
	}
	
	public void saveGame()
	{
		MapWriter.WriteMap(map);
	}
	
	@Override
	public void tryPress(KeyEvent e)
	{
		map.tryPress(e);
		if(e != null && e.getKeyCode() == KeyEvent.VK_ESCAPE)
			StateC.getState(StateC.STATE_GAME).setState(GameStates.STT_MENU);
	}

}
