/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package States.MenuStates;

/**
 *
 * @author Yoseph Alabdulwahab
 */

import States.State;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class MenuState extends State {
	
	public static int MENU_MAIN = 0;
	public static int MENU_NEW_LEVEL = 1;
	public static int MENU_LOAD_LEVEL = 2; 
	public static int SETTING_STATE = 3;
	
	private State[] state = {new MainMenuState(), new NewLevelState(), new LoadLevelState(), new SettingState()};
	
	
	public MenuState()
	{
		currentState = MENU_MAIN;
	}
	
	@Override
	public void update(Graphics2D g)
	{
		if(inited == false)
		{
			state[currentState].init();
			inited = true;
		}
		state[currentState].update(g);
	}
	
	@Override
	public void drawSelf(Graphics2D g)
	{
		state[currentState].drawSelf(g);
	}
	
	@Override
	public void tryClick(MouseEvent e)
	{
		state[currentState].tryClick(e);
	}

}
