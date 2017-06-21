/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Input;


import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.MouseInfo;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import Game.Tread;

/**
 *
 * @author Yoseph Alabdulwahab
 */
public class Mouse {
	
	public static int mousePos[] = new int[2];
	public static int CORD_X = 0;
	public static int CORD_Y = 1;
	
	public static final boolean PRESSED = true;
	public static final boolean RELEASED = false;
	
	private static boolean mouseRightClicked;
	private static boolean mouseRightPressed;
	
	private static boolean mouseInScreen;
	
	private static boolean mouseLeftClicked;
	private static boolean mouseLeftPressed;
	
	/**
	 * initializes the mouse positions.
	 */
	public static void setup()
	{
		mousePos[CORD_X] = 0;
		mousePos[CORD_Y] = 1;
	}
	
	/**
	 * return whether the mouse is inside the game screen.
	 * 
	 * @return if is in JPanel
	 */
	public static boolean inScreen()
	{
		return mouseInScreen;
	}
	/**
	 * return whether the mouse is pressed.
	 * 
	 * @return if is pressed
	 */
	public static boolean isRightPressed()
	{
		return mouseRightPressed;
	}
	
	public static boolean isLeftPressed()
	{
		return mouseRightPressed;
	}
	
	/**
	 * sets the position of the mouse relative to the JPanel, and weather it is in it.
	 * 
	 * @param panel (JPanel)
	 */
	public static void refreashMouseInfo(JPanel panel)
	{
		PointerInfo pointI = MouseInfo.getPointerInfo();
		Point point = pointI.getLocation();
		SwingUtilities.convertPointFromScreen(point, panel);
		if((point.getX() < Tread.WIDTH * Tread.wScale && point.getX() > 0) && (point.getY() < Tread.HEIGHT * Tread.hScale && point.getY() > 0))
		{
			setMousePos(CORD_X, (int)point.getX());
			setMousePos(CORD_Y, (int)point.getY());
		}
	}
	
	/**
	 * checks weather the mouse is over the specified bounds.
	 * 
	 * @param xi top left x position of item
	 * @param yi top left position of item
	 * @param xf bottom right x position of item
	 * @param yf bottom right y position of item
	 * @return whether is over(boolean).
	 */
	public static boolean isOver(int xi, int yi, int xf, int yf)
	{
		xi *= Tread.wScale;
		xf *= Tread.wScale;
		yi *= Tread.hScale;
		yf *= Tread.hScale;
		if(mousePos[CORD_X] >= xi && mousePos[CORD_X] <= xf && mousePos[CORD_Y] >= yi && mousePos[CORD_Y] <= yf)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * sets the mouse's x/y position (specified in param) to the given cord.
	 * 
	 * @param xy (weather it is x or w that is being set)
	 * @param cord coordinate values 
	 */
	public static void setMousePos(int xy, int cord)
	{
		mousePos[xy] = cord;
	}
	
	/**
	 * sets the tracker to whether the mouse is right pressed.
	 * 
	 * @param b (if mouse pressed)
	 */
	public static void setRightPressed(boolean b)
	{
		mouseRightPressed  = b;
	}
	
	/**
	 * sets the tracker to whether the left mouse is pressed.
	 * 
	 * @param b (if mouse pressed)
	 */
	public static void setLeftPressed(boolean b)
	{
		mouseLeftPressed  = b;
	}
	
	/**
	 * sets whether the mouse is in the bounds of the screen.
	 * 
	 * @param b (if mouse is in screen)
	 */
	public static void setInScreen(boolean b)
	{
		mouseInScreen = b;
	}
	

}
