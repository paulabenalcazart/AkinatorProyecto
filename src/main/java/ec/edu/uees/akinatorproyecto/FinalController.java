package ec.edu.uees.akinatorproyecto;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FinalController implements Initializable{
    private String searchText;
    private String imageUrl;
    @FXML private ImageView imagenResultado;
    @FXML private AnchorPane mainAnchor;
    private Stage stage;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML private Button cerrar, minimizar;
    
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        searchText = "Brad Pitt";
        imageUrl = getWikipediaImage(searchText);
        if (imageUrl != null) {
            Image image = new Image(imageUrl, true);
            imagenResultado.setImage(image);
        }
        
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
        System.out.println("No me gustó, yo quiero poner chao");
    }
    
    private String getWikipediaImage(String actorName) {
        try {
            String searchUrl = "https://es.wikipedia.org/api/rest_v1/page/summary/" + actorName.replace(" ", "_");
            URL url = new URL(searchUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();
            String jsonResponse = response.toString();
            System.out.println("Wikipedia JSON: " + jsonResponse); // Depuración
            int startIndex = jsonResponse.indexOf("\"source\":\"") + 10;
            int endIndex = jsonResponse.indexOf("\"", startIndex);
            if (startIndex > 10 && endIndex > startIndex) {
                return jsonResponse.substring(startIndex, endIndex).replace("\\", "");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
