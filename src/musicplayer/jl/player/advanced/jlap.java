/*
 * 11/19/04		1.0 moved to LGPL. 
 *-----------------------------------------------------------------------
 *   This program is free software; you can redistribute it and/or modify
 *   it under the terms of the GNU Library General Public License as published
 *   by the Free Software Foundation; either version 2 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU Library General Public License for more details.
 *
 *   You should have received a copy of the GNU Library General Public
 *   License along with this program; if not, write to the Free Software
 *   Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *----------------------------------------------------------------------
 */

package musicplayer.jl.player.advanced;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import musicplayer.jl.decoder.JavaLayerException;

/**
 * This class implements a sample player using Playback listener.
 */
public class jlap
{

  public static void main(String[] args)
  {
    jlap test = new jlap();
      try
      {
        test.play(new java.io.File(".").getCanonicalPath() + "/src/resources/Music/musicplayer.mp3");
      }
      catch (Exception ex)
      {
        System.err.println(ex.getMessage());
        System.exit(0);
      }
  }

  public void play(String filename) throws JavaLayerException, IOException
  {
    InfoListener lst = new InfoListener();
    playMp3(new File(filename), lst);
  }

  public void showUsage()
  {
    System.out.println("Usage: jla <filename>");
    System.out.println("");
    System.out.println(" e.g. : java musicplayer.jl.player.advanced.jlap localfile.mp3");
  }

  public static AdvancedPlayer playMp3(File mp3, PlaybackListener listener) throws IOException, JavaLayerException
  {
    return playMp3(mp3, 0, Integer.MAX_VALUE, listener);
  }

  public static AdvancedPlayer playMp3(File mp3, int start, int end, PlaybackListener listener) throws IOException, JavaLayerException
  {
    return playMp3(new BufferedInputStream(new FileInputStream(mp3)), start, end, listener);
  }

  public static AdvancedPlayer playMp3(final InputStream is, final int start, final int end, PlaybackListener listener) throws JavaLayerException
  {
    final AdvancedPlayer player = new AdvancedPlayer(is);
    player.setPlayBackListener(listener);
    // run in new thread
    new Thread()
    {
      public void run()
      {
        try
        {
          player.play(start, start);
          System.out.println("HIT");
          //player.setVolume(0f);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    }.start();
    return player;
  }

  public class InfoListener extends PlaybackListener
  {
    public void playbackStarted(PlaybackEvent evt)
    {
      System.out.println("Play started from frame " + evt.getFrame());
    }

    public void playbackFinished(PlaybackEvent evt)
    {
      System.out.println("Play completed at frame " + evt.getFrame());
      //System.exit(0);
    }
  }
}