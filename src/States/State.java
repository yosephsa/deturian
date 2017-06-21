/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package States;

import Game.Tread;
import States.MenuStates.MenuObjects.ButtonObject;
import States.MenuStates.MenuObjects.ClickableObject;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class State {
	
	protected static boolean inited = false;
	protected static int currentState = 0;
	protected BufferedImage image;
	protected static int cellSize;
	
	/**
	 * sets the current state.
	 * 
	 * @param state 
	 */
	public void setState(int state)
	{
		inited = false;
		currentState = state;
	}
	
	/**
	 * initializes current state/.
	 */
	public void init()
	{
		
	}
	
	/**
	 * updates current states relative to specified graphics2d.
	 * 
	 * @param g 
	 */
	public void update(Graphics2D g)
	{
	}

	/**
	 * draws all objects to the specified graphics2d.
	 * 
	 * @param g 
	 */
	public void drawSelf(Graphics2D g)
	{
		
	}
	
	/**
	 * performs what is needed when mouse is clicked.
	 */
	public void tryClick(MouseEvent e)
	{
		
	}
	
	public void tryPress(KeyEvent e)
	{
	}


}
