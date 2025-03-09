package ec.edu.uees.akinatorproyecto;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MenuController implements Initializable {

    @FXML private AnchorPane mainAnchor;
    private Stage stage;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML private Button minimizar, cerrar, botonPlay, botonOptions;
    @FXML private ImageView akinatorMenu;
    @FXML private Label tituloAkinator;
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("juego");
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
        
        // Animacion Akinator
        TranslateTransition transition = new TranslateTransition(Duration.seconds(2), akinatorMenu);
        transition.setByY(20);
        transition.setAutoReverse(true);
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.play();
        
        // Animacion titulo (con GPT porque estaba dificil)
        Platform.runLater(() -> {
            double labelWidth = tituloAkinator.getWidth();
            double labelHeight = tituloAkinator.getHeight();
            if (labelWidth == 0 || labelHeight == 0) {
                System.out.println("Label no esta en visible(true)");
                return;
            }
            Label tituloBrillo = new Label(tituloAkinator.getText());
            tituloBrillo.setFont(tituloAkinator.getFont());
            tituloBrillo.setStyle("-fx-text-fill: white; ");
            tituloBrillo.setAlignment(tituloAkinator.getAlignment());
            tituloBrillo.setPrefSize(tituloAkinator.getWidth(), tituloAkinator.getHeight());
            tituloBrillo.setLayoutX(tituloAkinator.getLayoutX());
            tituloBrillo.setLayoutY(tituloAkinator.getLayoutY());
            Rectangle brillo = new Rectangle(labelWidth + 50, labelHeight);
            brillo.setFill(new LinearGradient(
                0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.TRANSPARENT),
                new Stop(0.5, Color.WHITE),
                new Stop(1, Color.TRANSPARENT)
            ));
            brillo.setOpacity(0.85);
            tituloBrillo.setClip(brillo);
            TranslateTransition transicion = new TranslateTransition(Duration.seconds(2), brillo);
            transicion.setFromX(-labelWidth - 50);
            transicion.setToX(labelWidth + 50);
            transicion.setCycleCount(TranslateTransition.INDEFINITE);
            transicion.setAutoReverse(false);
            transicion.play();
            Parent parentContainer = tituloAkinator.getParent();
            ((Pane) parentContainer).getChildren().add(tituloBrillo);
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
