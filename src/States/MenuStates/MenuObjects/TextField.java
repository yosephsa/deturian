/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package States.MenuStates.MenuObjects;

import util.RL;
import Input.Keyboard;
import Input.Mouse;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author Yoseph Alabdulwahab
 */
public class TextField extends ClickableObject {
	
	private String directory = "/resources/fonts/";
	protected int posX, posY, width, height;
	private Font font;
	protected boolean selected = false;
	private String text = "";
	private long[] keyLock = new long[256];
	int option = 1;
	
	BufferedImage[] image = {new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB)};
	
	public TextField(int x, int y, int width, String fontType)
	{
		posX = x;
		posY = y;
		this.width = width;
		
		if(fontType.substring(fontType.length() - 4).equals(".ttf"))
		{
			try
			{
				font = Font.createFont(Font.TRUETYPE_FONT,getClass().getResourceAsStream(directory + fontType)).deriveFont(35);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		font = new Font(fontType, 1, 35);
		height = 50;
		
		image[0] = RL.loadImage("TextField.png", width, height);
		image[1] = RL.loadImage("TextFieldUnselected.png", width, height);
	}

	@Override
	public void update(Graphics2D g)
	{
		if(selected)
			option = 0;
		else
			option = 1;
		
		boolean[] keys = Keyboard.getKeys();
		if(selected)
		{
			for(int i = 0; i < keys.length; i++)
			{
				if(keys[i] == true)
				{
					if(i == 8 || (i >= 65 && i <= 90) || (i >= 48 && i <= 57) || i == 32)
						if(i == 8 && text.length() >= 1 && (keyLock[i] % 7 == 0 || keyLock[i] == -1))
						{
							text = text.substring(0, text.length() - 1);
							if(keyLock[i] > 7)
								keyLock[i] = -1;
						}
						else if(i != 8)
							try
							{
								if(keyLock[i] % 7 == 0 || keyLock[i] == -1)
								{
									int value = i;
									text += Character.toString((char)i);
									if(text.length()> width / 30 - 1)
										text = text.substring(0, width / 30 - 1);
									if(keyLock[value] > 7 )
										keyLock[value] = -1;
								}
							}
							catch(Exception e)
							{
								System.out.println("Unexpected Error for textfield, Could not a Character with an ascii value of " + i);
							}
					if(keyLock[i] != -1)
					keyLock[i]++;
				}
				else
					keyLock[i] = 0;
			}
		}
		
	}
	@Override
	public void drawSelf(Graphics2D g)
	{
		g.setFont(font);
		g.drawImage(image[option], posX, posY, width, height, null);
		g.drawString(text,  posX + 17, posY + height - 10);
		image[option].getGraphics().dispose();
	}

	/**
	 * performs needed actions for textfield.
	 */
	@Override
	public void performAction()
	{
		selected = !selected;
	}
	
	@Override
	public void tryClick(MouseEvent e)
	{
		if(e.getButton() == MouseEvent.BUTTON1)
			if(Mouse.isOver(posX, posY, posX + width, posY + height))
				performAction();
	}
	
	public String getText()
	{
		return text;
	}

}
