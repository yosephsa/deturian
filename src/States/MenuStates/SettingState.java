/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package States.MenuStates;

import States.State;
import java.awt.Graphics2D;	
import Game.Tread;
import util.RL;
import States.MenuStates.MenuObjects.ButtonObject;
import States.MenuStates.MenuObjects.ClickableObject;
import States.MenuStates.MenuObjects.TextLabel;
import States.StateC;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Arrays;

public class SettingState extends State {
	
	private TextLabel title;
	
	protected ButtonObject[] buttons;
	
	protected String background_file = "MetalFloorRusted.png";
	
	protected LinkedList<ClickableObject> allClickableObjects = new LinkedList<ClickableObject>();
	
	public SettingState()
	{
		buttons = makeButtons();
		title = new TextLabel((int)(Tread.WIDTH / 2), (int)(Tread.HEIGHT / 25), "Settings", 70, "titleFo", Color.WHITE);
		image = RL.loadImageRepeating(background_file, Tread.WIDTH, Tread.HEIGHT);
		allClickableObjects.addAll(Arrays.asList(buttons));
		allClickableObjects.add(title);
	}
	
	private ButtonObject[] makeButtons()
	{
		ButtonObject[] b = {
		
			new ButtonObject((int)(Tread.WIDTH / 17 * 2), (int)(Tread.HEIGHT / 8 * 6.6), 0, 0, "Back", 40, "Impact")
			{
				@Override
				public void performAction()
				{
					StateC.getState(StateC.STATE_MENU).setState(MenuState.MENU_MAIN);
				}
			}
		};
		
		return b;
	}
	
	@Override
	public void update(Graphics2D g)
	{
		for(int i = 0; i < allClickableObjects.size(); i++)
			{
				allClickableObjects.get(i).update(g);
			}
	}
	
	@Override
	public void drawSelf(Graphics2D g)
	{
		g.drawImage(image, 0, 0, (int)Tread.WIDTH, (int)Tread.HEIGHT, null);
		for(int i = 0; i < allClickableObjects.size(); i++)
				allClickableObjects.get(i).drawSelf(g);
	}
	
	@Override
	public void tryClick(MouseEvent e)
	{
		for(int i = 0; i < allClickableObjects.size(); i++)
		{
			allClickableObjects.get(i).tryClick(e);
		}
	}


}
