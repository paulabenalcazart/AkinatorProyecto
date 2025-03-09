package ec.edu.uees.akinatorproyecto;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class JuegoController implements Initializable{

    @FXML private AnchorPane mainAnchor;
    private Stage stage;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML private Button minimizar, cerrar, botonSi, botonNo;

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
    
    @FXML
    private void irAFinal() throws IOException {
        App.setRoot("final");
    }
}
