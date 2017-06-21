/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package States;

/**
 *
 * @author Yoseph Alabdulwahab
 */

import States.Game.GameStates;
import States.MenuStates.MenuState;
import musicplayer.Music;
import musicplayer.MusicHandler;
import util.RL;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class StateC {
	
	public static int STATE_MENU = 0;
	public static int STATE_GAME = 1;
	
	private static int currentState = STATE_MENU;
	private static State[] states = {new MenuState(), new GameStates()};
	
	public StateC()
	{
		setState(STATE_MENU);
	}
	
	/**
	 * returns the number of the current state.
	 * 
	 * @return current state
	 */
	public static int getState()
	{
		return currentState;
	}
	
	public static State getState(int state)
	{
		return states[state];
	}
	
	/**
	 * updates current state.
	 * 
	 * @param g graphics
	 */
	public void updateState(Graphics2D g)
	{
		states[currentState].update(g);
	}
	
	/**
	 * draws current state to given graphics
	 * 
	 * @param g 
	 */
	public void drawState(Graphics2D g)
	{
		states[currentState].drawSelf(g);
	}
	
	/**
	 * set the current state to given number.
	 * 
	 * @param state number
	 */
	public static void setState(int state)
	{
		currentState = state;
		states[currentState].init();
	}
	
	/**
	 * calls the current state's tryClick() method.
	 */
	public static void tryClick(MouseEvent e)
	{
		states[currentState].tryClick(e);
	}
	
	public static void tryPress(KeyEvent e)
	{
		states[currentState].tryPress(e);
	}


}
