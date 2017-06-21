/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Input;

public class Keyboard{
	
	public static boolean KEY_PRESSED = true;
	public static boolean KEY_NOT_PRESSED = false;
	private static boolean keys[] = new boolean[256];
	private static boolean pressed = false;
	
	/**
	 * initializes they lists to keep track of all keys
	 */
	public static void setup()
	{
		for(int i = 0; i < keys.length - 1; i++)
		{
			keys[i] = KEY_NOT_PRESSED;
		}
	}
	
	public static void keyPressed()
	{
		pressed = true;
	}
	
	public static void keyReleased()
	{
		pressed = false;
	}
	
	public static boolean isPressed()
	{
		return pressed;
	}
	
	/**
	 * checks weather the specified key is pressed or not.
	 * @param keyNum (ASCII value).
	 * @return key status (boolean)
	 */
	public static boolean isKeyDown(int keyNum)
	{
		return keys[keyNum];
	}
	
	public static void setKey(int keyASCII, boolean state)
	{
		keys[keyASCII] = state;
	}
	
	public static boolean[] getKeys()
	{
		return keys;
	}
	


}
