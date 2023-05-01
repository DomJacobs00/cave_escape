package age491.cave_escape;


import java.io.File;
import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
public class MusicHandler {
	private MediaPlayer mediaPlayer;
	
	public MusicHandler(String audioFile)
	{
		try
		{
			String audioFileURL = getClass().getResource(audioFile).toExternalForm();
			Media media = new Media(audioFileURL);
			mediaPlayer = new MediaPlayer(media);
			mediaPlayer.setVolume(0.5);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void play()
	{
		mediaPlayer.play();
	}
	public void pause()
	{
		mediaPlayer.pause();
	}
	public void stop()
	{
		mediaPlayer.stop();
	}
}
