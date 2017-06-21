/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import Game.Tread;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class RL {
	
	public static String SHADER_FILE = "MenuShader.png";
	
	public static final String PATH = "/resource/";
	
	/**
	 * creates an image from the specified file name, loads it and returns it for use.
	 * 
	 * @param file that is inside the resource folder
	 * @return image of the chosen file
	 */
	public static BufferedImage loadImage(String file)
	{
		BufferedImage image = new BufferedImage((int)Tread.WIDTH, (int)Tread.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		try
		{
			image = ImageIO.read(RL.class.getResourceAsStream(PATH + file));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return image;
	}
	
	/**
	 * creates an image from the specified file name, resizes the image based on
	 * given dimensions, then loads it and returns it for use.
	 * 
	 * @param w the desired width of the image
	 * @param h the desired height of the image
	 * @param file that is inside the resource folder
	 * @return image of the chosen file
	 */
	public static BufferedImage loadImage(String file, int w, int h)
	{
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		try
		{
			image = ImageIO.read(RL.class.getResourceAsStream(PATH + file));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return image;
	}
	
	public static BufferedImage makeImage(int w, int h)
	{
		return new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	}
	
	public static BufferedImage loadImageRepeating(String file, double w, double h)
	{
		BufferedImage image = new BufferedImage((int)w, (int)h, BufferedImage.TYPE_INT_ARGB);
		BufferedImage tempI = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D)image.getGraphics();
		
		try
		{
			tempI = ImageIO.read(RL.class.getResourceAsStream(PATH + file));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		for(int x = 0; x < (int)(w / tempI.getWidth() + 0.5); x++)
			for(int y = 0; y < (int)(h / tempI.getHeight() + 0.5); y++)
				g.drawImage(tempI, (int)(x * tempI.getWidth()), (int)(y * tempI.getHeight()), tempI.getWidth(), tempI.getHeight(), null);
		
		return image;
	}
	
	public static void addDepthShader(Graphics2D g, int w, int h)
	{
		BufferedImage shader = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
		try
		{
			shader = ImageIO.read(RL.class.getResourceAsStream(PATH + SHADER_FILE));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		g.drawImage(shader, 0, 0, (int)(w), (int)(h), null);
		//g.drawImage(shader, (int)(0), (int)(h / 2 - shader.getHeight() / 2), (int)(w), (int)(h / 9 * 7), null);
		
	}
	
	public static BufferedImage loadImageButton(double w, double h)
	{
		String backFile = "GrateMetalSilver.png";
		String corner11 = "ButtonCorner11.png";
		String corner12 = "ButtonCorner12.png";
		String corner21 = "ButtonCorner21.png";
		String corner22 = "ButtonCorner22.png";
		String sideUp = "ButtonSideUp.png";
		String sideDown = "ButtonSideDown.png";
		String sideRight = "ButtonSideRight.png";
		String sideLeft = "ButtonSideLeft.png";
		
		BufferedImage image = new BufferedImage((int)w, (int)h, BufferedImage.TYPE_INT_ARGB);
		BufferedImage tempI = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);

		BufferedImage Icorner11 = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
		BufferedImage Icorner12 = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
		BufferedImage Icorner21 = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
		BufferedImage Icorner22 = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
		BufferedImage IsideUp = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
		BufferedImage IsideDown = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
		BufferedImage IsideRight = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
		BufferedImage IsideLeft = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D)image.getGraphics();
		
		try
		{
			tempI = ImageIO.read(RL.class.getResourceAsStream(PATH + backFile));
			Icorner11 = ImageIO.read(RL.class.getResourceAsStream(PATH + corner11));
			Icorner12 = ImageIO.read(RL.class.getResourceAsStream(PATH + corner12));
			Icorner21 = ImageIO.read(RL.class.getResourceAsStream(PATH + corner21));
			Icorner22 = ImageIO.read(RL.class.getResourceAsStream(PATH + corner22));
			IsideUp = ImageIO.read(RL.class.getResourceAsStream(PATH + sideUp));
			IsideDown = ImageIO.read(RL.class.getResourceAsStream(PATH + sideDown));
			IsideRight = ImageIO.read(RL.class.getResourceAsStream(PATH + sideRight));
			IsideLeft = ImageIO.read(RL.class.getResourceAsStream(PATH + sideLeft));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		for(int x = 0; x < (int)(w / tempI.getWidth() + 0.5); x++)
			for(int y = 0; y < (int)(h / tempI.getHeight() + 0.5); y++)
				g.drawImage(tempI, (int)(x * tempI.getWidth()), (int)(y * tempI.getHeight()), tempI.getWidth(), tempI.getHeight(), null);
		
		for(int i = 0; i < w / IsideUp.getWidth(); i++)
			g.drawImage(IsideUp, i * IsideUp.getWidth(), 0, null);
		
		for(int i = 0; i < w / IsideDown.getWidth(); i++)
			g.drawImage(IsideDown, i * IsideDown.getWidth(), image.getHeight() - IsideDown.getHeight(), null);
		
		for(int i = 0; i < h / IsideLeft.getHeight(); i++)
			g.drawImage(IsideLeft, 0, i * IsideLeft.getHeight(), null);
		
		for(int i = 0; i < h / IsideRight.getHeight(); i++)
			g.drawImage(IsideRight, image.getWidth() - IsideRight.getWidth(), i * IsideRight.getHeight(), null);
		
		g.drawImage(Icorner11, 0, 0, null);
		g.drawImage(Icorner12, 0, image.getHeight() - Icorner12.getHeight(), null);
		g.drawImage(Icorner21, image.getWidth() - Icorner21.getWidth(), 0, null);
		g.drawImage(Icorner22, image.getWidth() - Icorner22.getWidth(), image.getHeight() - Icorner22.getHeight(), null);
		
		return image;
	}

}
