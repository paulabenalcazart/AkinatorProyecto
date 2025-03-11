package ec.edu.uees.akinatorproyecto;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class OptionsController implements Initializable{
    @FXML private AnchorPane mainAnchor;
    private Stage stage;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML private Button minimizar, cerrar, botonPlay, botonOptions;
    
    @FXML private MediaView fondoPergamino;
    private MediaPlayer mediaPlayer;
    private String filePath = getClass().getResource("/imagenes/pergaminoVideo.mp4").toExternalForm();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mainAnchor.setOnMousePressed(e -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });
        
        mainAnchor.setOnMouseDragged(e -> {
            stage = (Stage) cerrar.getScene().getWindow();
            if(!stage.isFullScreen()){
                stage.setX(e.getScreenX() - xOffset);
                stage.setY(e.getScreenY() - yOffset);
            }
        });
        
        Media media = new Media(filePath);
        mediaPlayer = new MediaPlayer(media);
        fondoPergamino.setMediaPlayer(mediaPlayer);
        
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> { // Hacerlo que se pause cuando esté en el último fotograma
            mediaPlayer.pause();
        });
    }
    
     @FXML
    private void cerrarStage() {
        stage = (Stage) cerrar.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void minimizarStage() {
        stage = (Stage) minimizar.getScene().getWindow();
        stage.setIconified(true);
    }
    
}
