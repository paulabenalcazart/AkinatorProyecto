package ec.edu.uees.opciones;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;

public class SFXPlayer {
    private static MediaPlayer sfxPlayer;
    private static double volume = 1;

    public static void playSFX() {
        URL resource = SFXPlayer.class.getResource("/audio/sfx.mp3");
        Media media = new Media(resource.toString());
        sfxPlayer = new MediaPlayer(media);
        sfxPlayer.setVolume(volume);
        sfxPlayer.play();
    }

    public static void setVolume(double newVolume) {
        volume = newVolume;
    }
    
    public static double getVolume() {
        return SFXPlayer.volume;
    }
            
}
