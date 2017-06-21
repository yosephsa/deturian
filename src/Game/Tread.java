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

import Input.Keyboard;
import Input.Mouse;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Graphics2D;
import States.StateC;
import musicplayer.MusicHandler;

public class Tread extends JPanel implements Runnable, KeyListener, MouseListener{
	
	private static Thread tread;
	private static volatile boolean running;
	
	
	public static final float WIDTH = 1080;
	public static final float HEIGHT = WIDTH / 16 * 9;
	public static float wScale = 1;
	public static float hScale = 1;
    public static boolean debug = false;
	
	private static JFrame frame;
	private final int FPS = 60;
    private final long target = 1000 / FPS;
	
	private static BufferedImage i = new BufferedImage((int)WIDTH, (int)HEIGHT, BufferedImage.TYPE_INT_ARGB);
	private Graphics2D ig = (Graphics2D)i.getGraphics();
	private StateC sc = new StateC();
	//private Music[] songs = {MusicHandler.newMusic("n6.mp3")};
	//private Music[] songs = {MusicHandler.newMusic("n1.mp3"), MusicHandler.newMusic("n2.mp3"), MusicHandler.newMusic("n3.mp3"), MusicHandler.newMusic("n4.mp3"), MusicHandler.newMusic("n5.mp3"), MusicHandler.newMusic("n6.mp3"), MusicHandler.newMusic("n7.mp3")};
	private int songIndex = 0;
	private boolean changeSong = true;

	public Tread(JFrame frame)
	{
		this.frame = frame;
		frame.setSize((int)WIDTH, (int)HEIGHT);
		this.setFocusable(true);
		this.requestFocus();
	}
	/**
	 * called whenever an action is taken to dispose of current application.
	 */
	public static void endGame()
	{
		frame.dispose();
		running = false;
	}
	
	/**
	 * initial setup of the program, called once.
	 */
	private void setup()
	{
		Keyboard.setup();
		Mouse.setup();
		sc.updateState(ig);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if(StateC.getState() == StateC.STATE_MENU)
				{
					endGame();
				}
				else if (JOptionPane.showConfirmDialog(frame, "Are you sure to close the game? \nprogress may be lost if in game.", "EXIT GAME", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
				{
					endGame();
				}
			}
		});
	}
	
	/**
	 * sets the screen scale ratio(relative to initial size).<!-- --> refreshes the mouse information.<!-- --> updates all objects.
	 */
	private void update()
	{
		/*if((songs[songIndex].isFinished() || changeSong)) {
			changeSong = false;
			songs[songIndex].pause();
			int newIn = (int)(Math.random() * songs.length);
			if(newIn == songIndex && newIn != 0)
				newIn /= 2;
			else if(newIn == songIndex)
				newIn = songs.length - 1;
			songIndex = newIn;
			songs[songIndex].play();
		}*/
		wScale = frame.getWidth() / WIDTH;
		hScale = frame.getHeight() / HEIGHT;
		Mouse.refreashMouseInfo(this);
		sc.updateState(ig);
	}
	
	/**
	 * calls the render methods of all objects, then displays it on the current JPanel
	 */
	private void render()
	{
		sc.drawState(ig);
		this.getGraphics().drawImage(i, 0, 0, (int)(WIDTH * wScale), (int)(HEIGHT * hScale), this);
	}
	
	/**
	 * last actions in the current iteration.
	 */
	private void finalUpdate()
	{
		this.getGraphics().dispose();
		MusicHandler.stopAllMusic();
	}
	
	/**
	 * The main loop body.
	 * 
	 * loops while boolean running == true.
	 * 
	 * pauses the thread every cycle for 15 milliseconds to allow other threads to run.
	 * 
	 * calls the setup method once, then goes through a while loop and calls the update
	 * method every 15 milliseconds so that the refresh rate of moving blocks is the same on all machines.
	 * 
	 * calls the render method every cycle
	 */
	@Override
	public void run()
	{
		setup();
		
		long tBegin;
        long tTime;
        long tWait;
		
		long uBegin = System.nanoTime();
        long uTime;
        long uWait;
		
		while(running)
		{
			tBegin = System.nanoTime();
			
			uTime = System.nanoTime();
			

			
			tTime = System.nanoTime() - tBegin;
                        
            tWait = target - tTime / 1000000;
            if(debug)
                System.out.println("Thread Time: " + tTime/1000000 + " " + tWait);
            if(tWait < 0)
			{
                tWait = 5;
			}
            
            try
            {
                Thread.sleep(tWait);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
			
			if((uTime - uBegin) / 1000000.0 >= 15)
			{
				try {
					SwingUtilities.invokeAndWait(new Runnable() {
						@Override
						public void run() {
							if (Mouse.isLeftPressed() || Mouse.isRightPressed())
								StateC.tryPress(null);
						}
					});
				} catch (Exception e) {
					System.err.println("Error running swing helper");
					e.printStackTrace();
				}
				
				update();
				
				if(debug)
					System.out.println("Update Time: " + (uTime - uBegin)/1000000.0);
				
				uBegin = System.nanoTime();
			}
			
			render();
			
			finalUpdate();
		}
	}
	
	@Override
	public void addNotify()
	{
		super.addNotify();
		if(tread == null)
		{
			tread = new Thread(this);
			addKeyListener(this);
			addMouseListener(this);
			tread.start();
		}
		
		running = true;
	}
	
	public static BufferedImage getImage()
	{
		return i;
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
    }

	@Override
	public void keyPressed(KeyEvent e)
	{
		Keyboard.setKey(e.getKeyCode(), Keyboard.KEY_PRESSED);
		Keyboard.keyPressed();
	}

	@Override
    public void keyReleased(KeyEvent e) 
    {
        Keyboard.setKey(e.getKeyCode(), Keyboard.KEY_NOT_PRESSED);
		Keyboard.keyReleased();
		StateC.tryPress(e);
		if(e.getKeyCode() == KeyEvent.VK_Z)
			changeSong = true;
    }
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		//Mouse.setRightClicked(true);
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		if(e.getButton() == MouseEvent.BUTTON1)
			Mouse.setRightPressed(Mouse.PRESSED);
		else if(e.getButton() == MouseEvent.BUTTON3)
			Mouse.setLeftPressed(Mouse.PRESSED);
	}
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
		Mouse.setRightPressed(Mouse.RELEASED);
		StateC.tryClick(e);
	}
	
	@Override
	public void mouseEntered(MouseEvent e)
	{
		Mouse.setInScreen(true);
	}
	
	@Override
	public void mouseExited(MouseEvent e)
	{
		Mouse.setInScreen(false);
	}
	
			

}
