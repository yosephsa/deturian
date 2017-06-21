/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States.MenuStates.MenuObjects;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 *
 * @author jon
 */
public abstract class ClickableObject 
{
	/**
	 * perform needed actions to this object when mouse is clicked
	 * 
	 */
    public abstract void tryClick(MouseEvent e);
	
	/**
	 * draw object to given graphics2d
	 * 
	 * @param g graphics2d
	 */
	public abstract void drawSelf(Graphics2D g);
	
	/**
	 * updates the current object relative to given graphics
	 * 
	 * @param g graphics2d 
	 */
	public abstract void update(Graphics2D g);
	
	/**
	 * perform needed actions to this object when asked.
	 * 
	 */
	public abstract void performAction();
}
