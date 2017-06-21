/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Game;

/**
 *
 * @author Yoseph Alabdulwahab
 */

import Generator.MapMaker;
import FileManagment.MapReader;
import FileManagment.MapWriter;
import Generator.SteppableRandom;
import States.Game.MainGame.GameObjects.Map;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
public class Main {
	
	/**
	 * First method of program, calls the tread class
	 * 
	 * @param args 
	 */
	private static JFrame frame = new JFrame("Deturian");
	private static JPanel panel;
	
	public static void main(String[] args)
	{
		//testSeedGenerator();
		runGame();
		//testMapWriter();
		//testMapMaker();
	}
	
	public static JPanel getPanel()
	{
		return panel;
	}
	
	public static void runGame()
	{
		frame.setContentPane((panel = new Tread(frame)));
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
	
	public static void testMapWriter()
	{
		Map map = MapMaker.MakeMap("Example", "");
		MapWriter.WriteMap(map);
	}
	
	public static void testMapMaker()
	{
		MapMaker.MakeMap(null, null);
	}
	
	public static JFrame getFrame()
	{
		return frame;
	}
	
	public static void testSeedGenerator()
	{
		SteppableRandom sr = new SteppableRandom(64789L);
		System.out.println(sr.floatAt(207));
		for(int i = 0; i < 207; i++)
			sr.nextFloat();
		System.out.println(sr.nextFloat());
	}

}
