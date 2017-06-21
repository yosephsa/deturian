/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package States.MenuStates;

import States.State;
import java.awt.Graphics2D;
import States.MenuStates.MenuObjects.*;
import Game.Tread;
import States.StateC;
import util.RL;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Arrays;

public class MainMenuState extends State {
	
	public static final int NEW_LEVEL_BUTTON = 0;
	public static final int LOAD_LEVEL_BUTTON = 1;
	public static final int QUIT_BUTTON = 2;
        
	private final String background_file = "BackgroundEarthSpace.png";
	
	private ButtonObject[] buttons;
	
	protected LinkedList<ClickableObject> allClickableObjects = new LinkedList<ClickableObject>();
	
	private TextLabel title = new TextLabel((int)(Tread.WIDTH / 2), (int)(Tread.HEIGHT / 13),"Deturian", 75, "titleFont.ttf", Color.WHITE);;
	
	private EarthObject earth = new EarthObject((int)(Tread.WIDTH / 2), (int)(Tread.HEIGHT / 2), (int)(Tread.WIDTH / 3),  10);
	//private EarthObject earth = new EarthObject((int)(Tread.WIDTH / 2), (int)(Tread.HEIGHT / 2), 200,  45);
		
	public MainMenuState()
	{
		buttons = makeButtons();
		image = RL.loadImage(background_file);
		allClickableObjects.add(earth);
		allClickableObjects.add(title);
		allClickableObjects.addAll(Arrays.asList(buttons));
	}
	
	private ButtonObject[] makeButtons()
	{
		
		ButtonObject[] b = 
		{
			new ButtonObject((int)(Tread.WIDTH / 6), (int)(Tread.HEIGHT / 3), 0, 0, " New Save ", 40, "Impact")
			{
				@Override
				public void performAction()
				{
					StateC.getState(StateC.STATE_MENU).setState(MenuState.MENU_NEW_LEVEL);
				}
			},

			new ButtonObject((int)(Tread.WIDTH / 6), (int)(Tread.HEIGHT / 3 * 2), 0, 0, " Load Save ", 40, "Impact")
			{
				@Override
				public void performAction()
				{
					StateC.getState(StateC.STATE_MENU).setState(MenuState.MENU_LOAD_LEVEL);
				}
			},

			new ButtonObject((int)(Tread.WIDTH / 6 * 5), (int)(Tread.HEIGHT / 3), 0, 0, " Settings ", 40, "Impact")
			{
				@Override
				public void performAction()
				{
					StateC.getState(StateC.STATE_MENU).setState(MenuState.SETTING_STATE);
				}
			},

			new ButtonObject((int)(Tread.WIDTH / 6 * 5), (int)(Tread.HEIGHT / 3 * 2), 0, 0, " Quit ", 40, "Impact")
			{
				@Override
				public void performAction()
				{
					Tread.endGame();
				}
			}
		};
		return b;
	}
	
	public void update(Graphics2D g)
	{
		for(int i = 0; i < allClickableObjects.size(); i++)
		{
			allClickableObjects.get(i).update(g);
		}
	}
	
	public void drawSelf(Graphics2D g)
	{
		g.drawImage(image, 0, 0, (int)Tread.WIDTH, (int)Tread.HEIGHT, null);
		for(int i = 0; i < allClickableObjects.size(); i++)
			allClickableObjects.get(i).drawSelf(g);
	}
	
	public void tryClick(MouseEvent e)
	{
		for(int i = 0; i < allClickableObjects.size(); i++)
		{
			allClickableObjects.get(i).tryClick(e);
		}
	}

}
