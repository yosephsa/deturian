/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package States.MenuStates;

import FileManagment.MapClearer;
import FileManagment.MapReader;
import States.State;
import java.awt.Graphics2D;
import States.MenuStates.MenuObjects.*;
import Game.Tread;
import util.RL;
import Input.Mouse;
import States.Game.GameStates;
import States.StateC;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Arrays;

public class LoadLevelState extends State {
	
	private int mapSelected = -1;
	
	private TextLabel title;
	private String background_file = "MetalFloorRusted.png";
	private LinkedList<ClickableObject> allClickableObjects = new LinkedList<ClickableObject>();
	private LinkedList<TextLabel> allMapLabels = new LinkedList<TextLabel>();
	private String[] maps;
	private BufferedImage savesImage;
	
	
	public LoadLevelState()
	{

		init();
	}
	
	/**
	 * jhjakhsdjkahkjashdkasd.
	 */
	public void init()
	{
		image = RL.loadImageRepeating(background_file, Tread.WIDTH, Tread.HEIGHT);
		savesImage = RL.loadImageRepeating(background_file, Tread.WIDTH, Tread.HEIGHT);
		RL.addDepthShader((Graphics2D)savesImage.getGraphics(),(int)Tread.WIDTH, (int)Tread.HEIGHT);
		MapReader.init();
		allClickableObjects.clear();
		allMapLabels.clear();
		maps = MapReader.getSaveNames();
		
		title = new TextLabel((int)(Tread.WIDTH / 2), (int)(Tread.HEIGHT / 25), "Load Save", 50, "Impact", Color.WHITE);
		
		ButtonObject[] btns;
		if((btns = makeButtons()) != null)
			allClickableObjects.addAll(Arrays.asList(makeButtons()));
		
		allMapLabels.add(new TextLabel((int)(Tread.WIDTH / 10), (int)(Tread.HEIGHT / 10), "Maps:", 50, "Arial", Color.WHITE));
		TextLabel[] svlbls;
		if((svlbls = makeSaveLabels()) != null)
			allMapLabels.addAll(Arrays.asList(svlbls));
		
		allClickableObjects.add(title);
		
	}
	
	private ButtonObject[] makeButtons()
	{
		ButtonObject[] b = 
		{
			new ButtonObject((int)(Tread.WIDTH / 13), (int)(Tread.HEIGHT / 6), 0, 0, "Load", 40, "Impact")
			{
				@Override
				public void performAction()
				{
					GameStates.setMap(MapReader.getMap(maps[mapSelected]));
					StateC.setState(StateC.STATE_GAME);
				}
			},
			
			new ButtonObject((int)(Tread.WIDTH / 13), (int)(Tread.HEIGHT / 3), 0, 0, "Delete", 40, "Impact")
			{
				@Override
				public void performAction()
				{
					MapClearer.clearDir(maps[mapSelected]);
					init();
					init();
				}
			},

			new ButtonObject((int)(Tread.WIDTH / 13), (int)(Tread.HEIGHT / 24 * 19), 0, 0, "Back", 40, "Impact")
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
	/**
	 * creates an array of labels for all existing maps.
	 * 
	 * @return array of all map labels.
	 */
	private TextLabel[] makeSaveLabels()
	{
		maps = MapReader.getSaveNames();
		if(maps != null)
		{
			TextLabel[] savesLabels = new TextLabel[maps.length];
			for(int i = 0; i < maps.length; i++)
			{
				savesLabels[i] = new TextLabel((int)(Tread.WIDTH / 5), (int)(i * Tread.HEIGHT / 11 + 130), maps[i], 40, "Arial", Color.WHITE)
				{
					@Override
					public void performAction()
					{
						color = Color.RED;
					}

					@Override
					public void tryClick(MouseEvent e)
					{
						if(e.getButton() == MouseEvent.BUTTON1)
							if(Mouse.isOver((posX + 170 - getWidth((Graphics2D)savesImage.getGraphics()) * 2), (posY + 70 - getHeight((Graphics2D)savesImage.getGraphics())), (posX + 170 + getWidth((Graphics2D)savesImage.getGraphics()) * 2), (int)(posY + 70 + getHeight((Graphics2D)savesImage.getGraphics()) * 2)))
							{
								performAction();
							}
							else
								color = Color.WHITE;
					}

				};
			}
			return savesLabels;
		}
		return null;
	}
	
	@Override
	public void update(Graphics2D g)
	{
		boolean[] selected = new boolean[allMapLabels.size()];
		for(int i = 0; i < allClickableObjects.size(); i++)
		{
			allClickableObjects.get(i).update(g);
		}
		for(int i = 0; i < allMapLabels.size(); i++)
		{
			selected[i] = allMapLabels.get(i).getSelected();
			
			allMapLabels.get(i).update(g);
		}
		for(int i = 0; i < selected.length; i++)
			if(selected[i])
			{
				mapSelected = i - 1;
				break;
			}
			else if(selected[selected.length - 1] == false)
				mapSelected = -1;
	}
	
	@Override
	public void drawSelf(Graphics2D g)
	{
		for(int i = 0; i < allClickableObjects.size(); i++)
				allClickableObjects.get(i).drawSelf((Graphics2D)image.getGraphics());
		for(int i = 0; i < allMapLabels.size(); i++)
				allMapLabels.get(i).drawSelf((Graphics2D)savesImage.getGraphics());
		image.getGraphics().drawImage(savesImage, 170, 70, (int)Tread.WIDTH, (int)Tread.HEIGHT, null);
		g.drawImage(image, 0, 0, (int)Tread.WIDTH, (int)Tread.HEIGHT, null);
	}
	
	@Override
	public void tryClick(MouseEvent e)
	{
		for(int i = 0; i < allClickableObjects.size(); i++)
		{
			allClickableObjects.get(i).tryClick(e);
		}
		for(int i = 0; i < allMapLabels.size(); i++)
			allMapLabels.get(i).tryClick(e);
	}

}
