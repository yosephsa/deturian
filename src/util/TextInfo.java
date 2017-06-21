package util;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.font.FontRenderContext;

public class TextInfo {

	private static Font font;
	private static FontRenderContext context;

	public static Rectangle2D getBounds(Graphics2D g, String message)
	{
		font = g.getFont();
		context = g.getFontRenderContext();
		return font.getStringBounds(message, context);
	}
	
	public static double getWidth(Graphics2D g, String message) 
	{
		font = g.getFont();
		context = g.getFontRenderContext();
		Rectangle2D bounds = getBounds(g, message);
		return bounds.getWidth();
	}

	public static double getHeight(Graphics2D g, String message) 
	{
		font = g.getFont();
		context = g.getFontRenderContext();
		Rectangle2D bounds = getBounds(g, message);
		return bounds.getHeight();
	}
	
}