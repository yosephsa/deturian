/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package musicplayer;

import java.util.ArrayList;
import musicplayer.Music;


/**
 *
 * @author Yoseph Alabdulwahab
 */
public class MusicHandler {
	
	/**
	 * 
	 */
	private static ArrayList<Music> playingMusic;
	
	/**
	 * Creates the list of all playing songs if it exists
	 * @param filePath the music path in the current operating system.
	 * @return The music object that was just created.
	 */
	public static Music newMusic(String filePath)
	{
		if(playingMusic == null)
			playingMusic = new ArrayList<>();
		Music music = new Music(filePath);
		music.play();
		if(music.getPlayer() != null)	
		{
			playingMusic.add(music);
		}
		else
		{
			music = null;
		}
		return music;
	}
	
	/**
	 * Stops all songs from playing, and deletes it from que
	 */
	public static void stopAllMusic()
	{
		if(playingMusic == null)
			return;
		for(int i = 0; i < playingMusic.size(); i++)
		{
			playingMusic.get(i).stop();
		}
		playingMusic = null;
	}
	
	/**
	 * Stops and removes the last added song from the list of currently playing songs.
	 */
	public static void stopLastQuedSong()
	{
		playingMusic.get(playingMusic.size() - 1).stop();
		playingMusic.remove(playingMusic.size() - 1);
	}
	
	/**
	 * deletes songs in que if and only if the song is finished and is not on repeat
	 */
	public static void updateQue()
	{
		for(int i = 0; i < playingMusic.size(); i++)
			if(playingMusic.get(i).isFinished())
			{
				playingMusic.remove(i);
				i--;
			}
				
	}

}