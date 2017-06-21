/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package musicplayer;

import musicplayer.jl.player.advanced.AdvancedPlayer;
import musicplayer.jl.player.advanced.PlaybackEvent;
import musicplayer.jl.player.advanced.PlaybackListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 *
 * @author Yoseph Alabdulwahab
 */
public class Music extends PlaybackListener implements Runnable
{
	/**
	 * the location of the music file.
	 */
    private String filePath;
	
	/**
	 * The name of the song. The name that can be displayed if it is played.
	 * Is defined by the file name.
	 */
	private String songName;
	
	private boolean finished = false;
	
	/**
	 * AdvancedPlayer associated with this song
	 */
    private AdvancedPlayer player;
	/**
	 * a seperate thread dedicated to playing the music on its own
	 */
    private Thread playerThread;
	/**
	 * Signifies weather the music playing is paused or on going 
	 */
    private static boolean paused;
	/**
	 * The position playback is currently on.
	 */
    private int tempFrame;
	/**
	 * signifies weather the song should start from beginning when it reaches the end (loop)
	 */
    private boolean looping;
	/**
	 * details how load the song is to be played
	 * # Not Implemented yet.
	 */
    private float volume;
    
	/**
	 * initializes fields of this class.
	 * @param filePath 
	 */
	public Music(String filePath)
	{
        paused = false;
        this.filePath = filePath;
        tempFrame = 0;
        looping = false;
    }
    
	/**
	 * Starts playing the song. If already playing it will cut it and play again.
	 */
    public void play()
    {
        try
        {
            //this.player = new AdvancedPlayer(getClass().getResourceAsStream("/resources/Music/" + filePath),musicplayer.jl.player.FactoryRegistry.systemRegistry().createAudioDevice());
            this.player = new AdvancedPlayer(new FileInputStream(new File("").getAbsolutePath() + "/src/resource/Music/" + filePath),musicplayer.jl.player.FactoryRegistry.systemRegistry().createAudioDevice());
            this.player.setPlayBackListener(this);
            this.playerThread = new Thread(this, filePath);
            playerThread.setPriority(Thread.NORM_PRIORITY);
            this.playerThread.start();
            //System.out.println("Making thread");
        }
        catch (Exception e)
        {
        }
    }
    
	/**
	 * Sets the music to loop if true, and vice versa.
	 * @param b 
	 */
    public void setLooping(boolean b)
    {
        looping = b;
    }

    /**
	 * @Override PlaybackListener.playbackStarted()
	 * 
	 * Is called when playback starts.
	 * 
	 * @param playbackEvent 
	 */
	@Override
    public void playbackStarted(PlaybackEvent playbackEvent)
    {
        //System.out.println("Playing");
    }
    
	/**
	 * changes the file path of the song.
	 * Will not take effect
	 * @param filePath 
	 */
    public void setSong(String filePath)
    {
        this.filePath = filePath;
    }
    
	/**
	 * returns the name of the song currently playing.
	 * @return filePath
	 */
    public String getSong()
    {
        return filePath;
    }
    
	/**
	 * returns the AdvancedPlayer object of this song.
	 * @return player
	 */
    public AdvancedPlayer getPlayer()
    {
        return player;
    }
    
	/**
	 * returns the frame the song is currently at. the frame is like the position where the playback is currently at.
	 * @return tempFrame
	 */
    public int getPosition()
    {
        return tempFrame;
    }
    
	/**
	 * Changes the song filePath, then restarts the playback.
	 * This does not modify any settings the song was set at. (eg. looping, paused, etc...)
	 * @param filePath 
	 */
    public void changeSong(String filePath)
    {
        this.filePath = filePath;
        this.play();
    }
    
    public void stop()
    {
        paused = false;
        looping = false;
        try
        {
            player.stop();
        }
        catch(Exception e)
        {
        }
    }
    
	/**
	 * Temporarily pauses the playback until it is started.
	 */
    public void pause()
    {
        paused = true;
        try
        {
            player.stop();
		   //System.out.println("PAUSING SUCESSFULL FRAME AT: " + tempFrame);

        }
        catch(Exception e)
        {
            System.out.println("Failed To Pause");
            e.printStackTrace();
            return;
        }
    }
    
    public String getFilePath()
    {
        return this.filePath;
    }

	/**
	 * @Override PlaybackListener.playbackFinished()
	 * Take necessary actions when playback finishes.
	 * @param playbackEvent 
	 */
	@Override
    public void playbackFinished(PlaybackEvent playbackEvent)
    {
        if(paused)
        {
            //System.out.println("Responce: " +playbackEvent.getFrame());
            tempFrame = playbackEvent.getFrame();
        }
        else if(looping)
        {
            //System.out.println("Trying to Loop");
            play();
        }
		else
		{
			finished = true;
			MusicHandler.updateQue();
		}

    }
    
    public void setVolume(float f)
    {
        volume = f;
    }

    /**
	 * @Override Runnable.run()
	 * UnPauses the song and calls the play method
	 */    
	@Override
    public void run()
    {
        try
        {
            if(paused)
            {
                paused = false;
                //System.out.println("RESUME PLAY AT: " + tempFrame);
                this.player.play(tempFrame, Integer.MAX_VALUE);
            }
            else
            {
                //System.out.println("PLAYING");
                this.player.play();
            }
        }
        catch (musicplayer.jl.decoder.JavaLayerException e)
        {
			
        }

    }
	
	/**
	 * Returns weather the music is set on a loop or not.
	 * 
	 * @return looping
	 */
	public boolean isLooping()
	{
		return looping;
	}
	
	/**
	 * Weather the playback is finished (not true if paused or looping)
	 * 
	 * @return finished
	 */
	public boolean isFinished()
	{
		return finished;
	}
}

