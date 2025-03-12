package ec.edu.uees.opciones;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;

public class MusicPlayer {
    private static MediaPlayer mediaPlayer;

    public static void startMusic() {
        if (mediaPlayer == null) {
            URL resource = MusicPlayer.class.getResource("/audio/musica.mp3");
            Media media = new Media(resource.toString());
            mediaPlayer = new MediaPlayer(media);

            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        }
    }

    public static void setVolume(double volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
        }
    }

    public static void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}

