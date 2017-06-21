package States.Game.MainGame;

import Game.Tread;
import States.Game.GameStates;
import States.MenuStates.MenuObjects.*;
import States.MenuStates.MenuState;
import States.State;
import States.StateC;
import util.RL;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.LinkedList;



/**
 *
 * @author Yoseph Alabdulwahab
 */
public class Menu extends State{
	
	private BufferedImage background;
	private BufferedImage menuBack = new BufferedImage((int)(Tread.WIDTH / 5 * 3), (int)(Tread.HEIGHT / 4 * 3), BufferedImage.TYPE_INT_ARGB);
	private LinkedList<ClickableObject> objects;
	Graphics2D go;
	
	public Menu()
	{
		init();
		objects = new LinkedList<>();
		menuBack = RL.loadImageRepeating("ShadeBlack.png", menuBack.getWidth(), menuBack.getHeight());
		ButtonObject[] b = 
		{
			new ButtonObject((int)(menuBack.getWidth() / 2), (int)(menuBack.getHeight() / 10 * 2), (int)(Tread.WIDTH / 2 - menuBack.getWidth() / 2), (int)(Tread.HEIGHT / 2 - menuBack.getHeight() / 2), " Resume Game ", 40, "Impact")
			{
				@Override
				public void performAction()
				{
					StateC.getState(StateC.STATE_GAME).setState(GameStates.STT_GAME);
					System.out.println("resuming");
				}
				
			},

			new ButtonObject((int)(menuBack.getWidth() / 2), (int)(menuBack.getHeight() / 10 * 4), (int)(Tread.WIDTH / 2 - menuBack.getWidth() / 2), (int)(Tread.HEIGHT / 2 - menuBack.getHeight() / 2), " Save Game ", 40, "Impact")
			{
				@Override
				public void performAction()
				{
					GameStates.saveGame();
				}
			},

			new ButtonObject((int)(menuBack.getWidth() / 2), (int)(menuBack.getHeight() / 10 * 6), (int)(Tread.WIDTH / 2 - menuBack.getWidth() / 2), (int)(Tread.HEIGHT / 2 - menuBack.getHeight() / 2), " Main Menu ", 40, "Impact")
			{
				@Override
				public void performAction()
				{
					StateC.setState(StateC.STATE_MENU);
					StateC.getState(StateC.STATE_MENU).setState(MenuState.MENU_MAIN);
				}
			},

			new ButtonObject((int)(menuBack.getWidth() / 2), (int)(menuBack.getHeight() / 10 * 8), (int)(Tread.WIDTH / 2 - menuBack.getWidth() / 2), (int)(Tread.HEIGHT / 2 - menuBack.getHeight() / 2), " Quit Game ", 40, "Impact")
			{
				@Override
				public void performAction()
				{
					Tread.endGame();
				}
			}
		};
		objects.addAll(Arrays.asList(b));
	}
	
	@Override
	public void init()
	{
		background = new BufferedImage((int)(Tread.WIDTH), (int)(Tread.HEIGHT), BufferedImage.TYPE_INT_ARGB);
		background.getGraphics().drawImage(Tread.getImage(), 0, 0, (int)Tread.WIDTH, (int)Tread.HEIGHT, null);
	}
	
	@Override
	public void update(Graphics2D g)
	{
		for(int i = 0; i < objects.size(); i++)
			objects.get(i).update((Graphics2D)menuBack.getGraphics());
	}
	
	@Override
	public void drawSelf(Graphics2D g)
	{
		g.drawImage(background, 0, 0, null);
		for(int i = 0; i < objects.size(); i++)
			objects.get(i).drawSelf((Graphics2D)menuBack.getGraphics());
		g.drawImage(menuBack, (int)(Tread.WIDTH / 2 - menuBack.getWidth() / 2), (int)(Tread.HEIGHT / 2 - menuBack.getHeight() / 2), menuBack.getWidth(), menuBack.getHeight(), null);
	}
	
	@Override
	public void tryPress(KeyEvent e)
	{
		if(e != null && e.getKeyCode() == KeyEvent.VK_ESCAPE)
			StateC.getState(StateC.STATE_GAME).setState(GameStates.STT_GAME);
	}
	
	@Override
	public void tryClick(MouseEvent e)
	{
		System.out.println("testing");
		for(ClickableObject o: objects)
			o.tryClick(e);
	}
	

}
