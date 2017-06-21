/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package States.MenuStates.MenuObjects;

import Game.Tread;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import util.RL;
import util.TextInfo;
import Input.Mouse;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

public class ButtonObject extends ClickableObject
{
	
	public static int CORD_X = 0;
	public static int CORD_Y = 1;
	
	private int buttonWidth = 400; 
	private int buttonHeight = 40;
	
	private TextLabel bText;
	
	private int currentButton;
	
	public static final int BUTTON_CLICKED = 0;
	public static final int BUTTON_NEUTURAL = 1;
	public static final int BUTTON_HOVERED = 2;
	public static final int BUTTON_ALL = 3;
	
	public int pos[] = {0, 0};
	private int xBuffer, yBuffer;
	
	protected String IMAGE_PATH;
	protected String HOVERED_HIGHLIGHT_PATH;
	protected String CLICKED_HIGHLIGHT_PATH;
	
	
	private BufferedImage[] image = new BufferedImage[4];
	
	public ButtonObject(int x, int y, int xBuf, int yBuf, String text, int fontSize, String fontType)
	{
		xBuffer = xBuf;
		yBuffer = yBuf;
		IMAGE_PATH = "GrateMetalSilver.png";
		HOVERED_HIGHLIGHT_PATH = "ShadeBlue.png";
		CLICKED_HIGHLIGHT_PATH = "ShadeBlack.png";
		
		image[BUTTON_ALL] = RL.loadImage(IMAGE_PATH);
		Graphics2D g = (Graphics2D)image[BUTTON_ALL].getGraphics();
		g.setFont(new Font("Impact", Font.BOLD, fontSize));
		g.setColor(new Color(0, 0, 0));
		
		buttonWidth = (int)(TextInfo.getWidth(g, text) + 40); 
		buttonHeight = (int)(TextInfo.getHeight(g, text) + 15);
		
		pos[CORD_X] = (int)(x - buttonWidth / 2);
		pos[CORD_Y] = (int)(y - buttonHeight / 2);
		
		image[BUTTON_CLICKED] = RL.loadImageButton(buttonWidth, buttonHeight);
		image[BUTTON_NEUTURAL] = RL.loadImageButton(buttonWidth, buttonHeight);
		image[BUTTON_HOVERED] = RL.loadImageButton(buttonWidth, buttonHeight);
		
		image[BUTTON_CLICKED].getGraphics().drawImage(RL.loadImageRepeating(CLICKED_HIGHLIGHT_PATH, image[BUTTON_NEUTURAL].getWidth(), image[BUTTON_NEUTURAL].getHeight()), 0, 0, null);
		image[BUTTON_HOVERED].getGraphics().drawImage(RL.loadImageRepeating(HOVERED_HIGHLIGHT_PATH, image[BUTTON_NEUTURAL].getWidth(), image[BUTTON_NEUTURAL].getHeight()), 0, 0, null);
		
		for(int i = 0; i < image.length - 1; i++)
		{
			Graphics2D tempG = (Graphics2D)image[i].getGraphics();
			bText = new TextLabel(buttonWidth / 2, buttonHeight / 3, text, fontSize, fontType, Color.BLACK);
			bText.drawSelf(tempG);
		}
	}
	
	/**
	 * returns the position of this button
	 * 
	 * @param c cord axis
	 * @return coordinate value
	 */
	public int getCord(int c)
	{
		return pos[c];
	}
	
	@Override
	public void update(Graphics2D g)
	{
		if(Mouse.isRightPressed() && Mouse.isOver(pos[CORD_X] + xBuffer, pos[CORD_Y] + yBuffer, pos[CORD_X] + buttonWidth + xBuffer, pos[CORD_Y] + buttonHeight + yBuffer))
		{
			currentButton = BUTTON_CLICKED;
		}
		else if(Mouse.isOver(pos[CORD_X] + xBuffer, pos[CORD_Y] + yBuffer, pos[CORD_X] + buttonWidth + xBuffer, pos[CORD_Y] + buttonHeight + yBuffer))
		{
			currentButton = BUTTON_HOVERED;
		}
		else
		{
			currentButton = BUTTON_NEUTURAL;
		}
	}
	
	@Override
	public void drawSelf(Graphics2D g)
	{
		g.drawImage(image[currentButton], pos[CORD_X], pos[CORD_Y], (int)buttonWidth, (int)buttonHeight, null);
	}
	
	@Override
	public void tryClick(MouseEvent e)
	{
		if(e.getButton() == MouseEvent.BUTTON1)
			if(Mouse.isOver(pos[CORD_X] + xBuffer, pos[CORD_Y] + yBuffer, pos[CORD_X] + image[BUTTON_CLICKED].getWidth() + xBuffer, pos[CORD_Y] + image[BUTTON_CLICKED].getHeight() + yBuffer))
				performAction();
	}
	
	@Override
	public void performAction()
	{
            if(Tread.debug)
                System.out.println("Button Pressed at Location = " + this.getClass().getPackage());
	}
}
