/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package States.Game;

import States.Game.MainGame.GameObjects.Map;
import States.Game.MainGame.Game;
import States.Game.MainGame.Menu;
import States.State;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public class GameStates extends State{
	
	public static int STT_GAME = 0;
	public static int STT_MENU = 1;
	private boolean init = false;
	private static Map map;
	private static State[] states = new State[2];
	private static int currentState;
	
	/**
	 * initilizes the map, game, and menu.
	 * 
	 * @param m Map object 
	 */
	public static void setMap(Map m)
	{
		map = m;
		states[STT_GAME] = new Game(m);
		states[STT_MENU] = new Menu();
		currentState = 0;
	}
	
	public void setState(int state)
	{
		currentState = state;
		init();
	}
	
	@Override
	public void init()
	{
		states[currentState].init();
	}
	
	@Override
	public void update(Graphics2D g)
	{
		states[currentState].update(g);	
	}
	
	public static void saveGame()
	{
		((Game)states[STT_GAME]).saveGame();
	}
	
	@Override
	public void drawSelf(Graphics2D g)
	{
		states[currentState].drawSelf(g);
	}
	
	@Override
	public void tryPress(KeyEvent e)
	{
		states[currentState].tryPress(e);
	}
	
	@Override
	public void tryClick(MouseEvent e)
	{
		states[currentState].tryClick(e);
	}


}
