package ec.edu.uees.akinatorproyecto;

import ec.edu.uees.opciones.AnimationManager;
import ec.edu.uees.opciones.MusicPlayer;
import ec.edu.uees.opciones.SFXPlayer;
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
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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
    @FXML private Button minimizar, cerrar, btnRegresar;
    @FXML private Label labelOptions;
    @FXML private ImageView imagenFondo;
    @FXML private MediaView fondoPergamino;
    @FXML private HBox hboxMusica, hboxSFX, hboxBrillo, hboxAnimaciones;
    @FXML private Slider sliderMusica, sliderSFX;
    @FXML private ToggleButton toggleAnimaciones;
    private MediaPlayer mediaPlayer;
    private Media media;
    private String filePath = getClass().getResource("/imagenes/pergaminoVideo.mp4").toExternalForm();
    private double videoOffset = 0;

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
        
        if(AnimationManager.getInstance().areAnimationsEnabled()) {
            Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.7 - videoOffset), e -> animarNodo(labelOptions)),
                new KeyFrame(Duration.seconds(0.85 - videoOffset), e -> animarNodo(hboxMusica)),
                new KeyFrame(Duration.seconds(0.9 - videoOffset), e -> animarNodo(hboxSFX)),
                new KeyFrame(Duration.seconds(1.05 - videoOffset), e -> animarNodo(hboxBrillo)),
                new KeyFrame(Duration.seconds(1.2 - videoOffset), e -> animarNodo(hboxAnimaciones)),    
                new KeyFrame(Duration.seconds(1.35 - videoOffset), e -> animarNodo(btnRegresar))
            );
            videoOffset = 0.2;

            Platform.runLater(() -> {
                try {
                    if(mediaPlayer != null) {
                        mediaPlayer.dispose();
                        System.gc();
                    }
                    if(imagenFondo.isVisible()){
                        imagenFondo.setVisible(false);
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
        } else {
            hboxMusica.setVisible(true);
            hboxSFX.setVisible(true);
            hboxBrillo.setVisible(true);
            hboxAnimaciones.setVisible(true);
            labelOptions.setVisible(true);
            btnRegresar.setVisible(true);
            imagenFondo.setVisible(true);
        }
        
        // OPCIÓN MUSICA
        sliderMusica.setValue(MusicPlayer.getMediaPlayer().getVolume());
        sliderMusica.valueProperty().addListener((observable, oldValue, newValue) -> {
            MusicPlayer.setVolume(newValue.doubleValue());
        });
        
        // OPCIÓN SFX
        sliderSFX.setValue(SFXPlayer.getVolume());
        sliderSFX.valueProperty().addListener((observable, oldValue, newValue) -> {
            SFXPlayer.setVolume(newValue.doubleValue());
        });
        
        // OPCIÓN BRILLO
        
        
        //OPCIÓN ANIMACIONES
        toggleAnimaciones.setSelected(AnimationManager.getInstance().areAnimationsEnabled());
        toggleAnimaciones.selectedProperty().addListener((observable, oldValue, newValue) -> {
            AnimationManager.getInstance().setAnimationsEnabled(newValue);
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
