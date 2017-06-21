/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package States.Game.MainGame.GameObjects;

import Game.Tread;
import Input.Keyboard;
import java.awt.event.KeyEvent;

/**
 *
 * @author Yoseph Alabdulwahab
 */
public class Camera {
	
	private float posX, posY;
	String place;
	private float shiftX = 0, shiftY = 2;
	
	public Camera(float x, float y, String loc)
	{
		posX = x;
		posY = y;
		place = loc;
	}
	
	public void track(Player p)
	{
		place = p.getLocation();
		//posX = p.getPosX();
		//posY = p.getPosY();s
		
		float moveX = (p.getPosX() - posX - p.getWidth() / 2) / (Tread.WIDTH / Map.getCellSize()) * 1.5F;
		float moveY = (p.getPosY() - posY - p.getHeight() / 2) / (Tread.HEIGHT / Map.getCellSize()) * 1.5F;
		
		posX += moveX;
		posY += moveY;
		
	}
	
	public float getPosX()
	{
		return posX;
	}
	
	public float getPosY()
	{
		return posY;
	}
	
	public String getLocation()
	{
		return place;
	}
	
	

}
