package ec.edu.uees.akinatorproyecto;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class OptionsController implements Initializable{
    @FXML private AnchorPane mainAnchor;
    private Stage stage;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML private Button minimizar, cerrar, btnOpcion1, btnOpcion2, btnOpcion3, btnRegresar;
    @FXML private Label labelOptions;
    @FXML private MediaView fondoPergamino;
    private MediaPlayer mediaPlayer;
    private Media media;
    private String filePath = getClass().getResource("/imagenes/pergaminoVideo.mp4").toExternalForm();
    private int videoOffset = 0;

    @FXML
    private void switchToMenu() throws IOException {
        App.setRootSinAnimar("menu");
    }
    
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
        
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(0.7 - videoOffset), e -> animarNodo(labelOptions)),
            new KeyFrame(Duration.seconds(0.9 - videoOffset), e -> animarNodo(btnOpcion1)),
            new KeyFrame(Duration.seconds(1.1 - videoOffset), e -> animarNodo(btnOpcion2)),
            new KeyFrame(Duration.seconds(1.3 - videoOffset), e -> animarNodo(btnOpcion3)),
            new KeyFrame(Duration.seconds(1.5 - videoOffset), e -> animarNodo(btnRegresar))
        );
        videoOffset = 2;
        
        Platform.runLater(() -> {
            try {
                if(mediaPlayer != null) {
                    mediaPlayer.dispose();
                    System.gc();
                }
                media = new Media(filePath);
                mediaPlayer = new MediaPlayer(media);
                fondoPergamino.setMediaPlayer(mediaPlayer);
                mediaPlayer.setOnReady(() -> {
                    mediaPlayer.play();
                });
                mediaPlayer.setOnError(() -> {
                    System.out.println(mediaPlayer.getError());
                    restart();
                });
                timeline.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
    }
    
    private void animarNodo(Node nodo) {
        nodo.setVisible(true);
        nodo.setOpacity(0);
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.75), nodo);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();
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
    
    private void restart() {
        try {
            Stage stage = (Stage) mainAnchor.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("options.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
