/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package States.MenuStates.MenuObjects;

import Game.Tread;
import Input.Mouse;
import static States.MenuStates.MenuObjects.ButtonObject.BUTTON_CLICKED;
import static States.MenuStates.MenuObjects.ButtonObject.CORD_X;
import static States.MenuStates.MenuObjects.ButtonObject.CORD_Y;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author Yoseph Alabdulwahab
 */
public class TextLabel extends ClickableObject
{
	
	public static int X = 0;
	public static int Y = 1;
	
	protected int width, height;
	protected int posX, posY;
	protected Color color;
	private String text, fontType;
	private Font font;
	private float fontSize;
	private final String directory = "/resource/fonts/";
	
	public TextLabel(int x, int y, String text, float fontSize, String fontType, Color c)
	{
		posX = x;
		posY = y;
		this.text = text;
		this.fontSize = fontSize;
		this.fontType = fontType;
		width = height = 0;
		color = c;
		
		//so if u put a font name the does not end with .ttf it loads the computer fonts instead of making a new one
		
		if(fontType.substring(fontType.length() - 4).equals(".ttf"))
		{
			try
			{
				font = Font.createFont(Font.TRUETYPE_FONT,getClass().getResourceAsStream(directory+fontType)).deriveFont(fontSize);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void update(Graphics2D g)
	{
		
	}
	
	@Override
	public void drawSelf(Graphics2D g)
	{
		if(font != null)
			g.setFont(font);
		else
			g.setFont(new Font(fontType, 1, (int)fontSize));
		g.setColor(color);
		g.drawString(text, posX - getWidth(g) / 2, posY + getHeight(g) / 2);
		g.setFont(null);
    }

	/**
	 * returns the width of the text in this label based on given graphics specifications.
	 * 
	 * @param g graphics
	 * @return width of text
	 */
    public int getWidth(Graphics2D g)
    {
        width = g.getFontMetrics().stringWidth(text);
        return width;
    }
       
	/**
	 * returns the height of the text in this label based on given graphics specifications.
	 * 
	 * @param g graphics
	 * @return height of text
	 */
    public int getHeight(Graphics2D g)
    {
        height = g.getFontMetrics().getHeight();
        return height;
    }
	
	/**
	 * returns weather this label is currently clicked (if color is red)
	 * @return selected status
	 */
	public boolean getSelected()
	{
		return color == Color.RED;
	}

    @Override
    public void tryClick(MouseEvent e) 
    {
        if(Mouse.isOver(posX, posY, posX + width, posY + height))
		{
			performAction();
		}
		
    }
	
	@Override
	public void performAction()
	{
	}

}
