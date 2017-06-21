/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package States.MenuStates;

import Generator.MapMaker;
import FileManagment.MapReader;
import FileManagment.MapWriter;
import Game.Main;
import States.State;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import States.MenuStates.MenuObjects.*;
import Game.Tread;
import util.RL;
import Input.Mouse;
import States.Game.GameStates;
import States.Game.MainGame.GameObjects.Map;
import States.StateC;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class NewLevelState extends State {
	
	private TextLabel title;
	private String background_file = "MetalFloorRusted.png";
	private LinkedList<ClickableObject> allClickableObjects = new LinkedList<>();
	private LinkedList<ClickableObject> allOptionObjects = new LinkedList<>();
	private TextField nameField;
	private TextField seedField;
	private BufferedImage optionsImage;
	
	
	public NewLevelState()
	{
		init();
		
	}
	
	@Override
	public void init()
	{
		title = new TextLabel((int)(Tread.WIDTH / 2), (int)(Tread.HEIGHT / 25), "New Save", 50, "Impact", Color.WHITE);
		image = RL.loadImageRepeating(background_file, Tread.WIDTH, Tread.HEIGHT);
		allClickableObjects.clear();
		allClickableObjects.addAll(Arrays.asList(makeButtons()));
		allClickableObjects.add(title);
		optionsImage = RL.loadImageRepeating(background_file, Tread.WIDTH, Tread.HEIGHT);
		RL.addDepthShader((Graphics2D)optionsImage.getGraphics(),(int)Tread.WIDTH, (int)Tread.HEIGHT);
		makeOptionObjects();
	}
	
	private void makeOptionObjects()
	{
		nameField = new TextField((optionsImage.getWidth() / 13 * 4), (optionsImage.getHeight() / 10), 500, "Impact")
		{
			@Override
			public void tryClick(MouseEvent e)
			{
				if(e.getButton() == MouseEvent.BUTTON1)
					if(Mouse.isOver(posX + 170, posY + 70, posX + width + 170, posY + height + 70))
						performAction();
					else
						selected = false;
			}
		};
		
		seedField = new TextField((optionsImage.getWidth() / 13 * 4), (optionsImage.getHeight() / 10 * 3), 500, "Impact")
		{
			@Override
			public void tryClick(MouseEvent e)
			{
				if(e.getButton() == MouseEvent.BUTTON1)
					if(Mouse.isOver(posX + 170, posY + 70, posX + width + 170, posY + height + 70))
						performAction();
					else 
						selected = false;
			}
		};
		
		allOptionObjects.add(new TextLabel((optionsImage.getWidth() / 8), (optionsImage.getHeight() / 9), "Map Name: ", 40, "Impact", Color.WHITE));
		allOptionObjects.add(new TextLabel((optionsImage.getWidth() / 6), (optionsImage.getHeight() / 9 * 3), "Seed (Optional): ", 40, "Impact", Color.WHITE));
		
	}
	
	/**
	 * creates an array of buttons to be drawn on the screen.
	 * 
	 * @return array buttons
	 */
	private ButtonObject[] makeButtons()
	{
		ButtonObject[] b = {
		
			new ButtonObject((int)(Tread.WIDTH / 13), (int)(Tread.HEIGHT / 24 * 5), 0, 0, "Create", 40, "Impact")
			{
				@Override
				public void performAction()
				{
					boolean canMake = true;
					for(String name: MapReader.getSaveNames())
						if(nameField.getText().equalsIgnoreCase(name))
							canMake = false;
					if(nameField.getText().equals(""))
					{
						JOptionPane.showMessageDialog(Main.getFrame(), "Please enter a name for a new Save");
						return;
					}
					else if(!canMake)
						if (JOptionPane.showConfirmDialog(Main.getFrame(), "A save with the same name already exists \nDo you want to ovveride it?", "New Save Error:", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION)
							return;
					Map map = MapMaker.MakeMap(nameField.getText(), seedField.getText());
					MapWriter.WriteMap(map);
					GameStates.setMap(map);
					StateC.setState(StateC.STATE_GAME);
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
	
	@Override
	public void update(Graphics2D g)
	{
		for(ClickableObject allClickableObject : allClickableObjects)
			allClickableObject.update(g);
		for(ClickableObject allOptionObject : allOptionObjects)
			allOptionObject.update((Graphics2D)optionsImage.getGraphics());
		nameField.update((Graphics2D)optionsImage.getGraphics());
		seedField.update((Graphics2D)optionsImage.getGraphics());
	}
	
	@Override
	public void drawSelf(Graphics2D g)
	{
		image.getGraphics().drawImage(optionsImage, 170, 70, null);
		g.drawImage(image, 0, 0, (int)Tread.WIDTH, (int)Tread.HEIGHT, null);
		for (ClickableObject allClickableObject : allClickableObjects)
			allClickableObject.drawSelf(g);
		for(ClickableObject allOptionObject : allOptionObjects)
			allOptionObject.drawSelf((Graphics2D)optionsImage.getGraphics());
		nameField.drawSelf((Graphics2D)optionsImage.getGraphics());
		seedField.drawSelf((Graphics2D)optionsImage.getGraphics());
	}
	
	@Override
	public void tryClick(MouseEvent e)
	{
		for(int i = 0; i < allClickableObjects.size(); i++)
		{
			allClickableObjects.get(i).tryClick(e);
		}
		nameField.tryClick(e);
		seedField.tryClick(e);
	}
}
