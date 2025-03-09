package ec.edu.uees.akinatorproyecto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("menu"));
        scene.getStylesheets().add(getClass().getResource("/css/estilos.css").toExternalForm());
        stage.initStyle(javafx.stage.StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        Parent nuevaVista = loadFXML(fxml);
        Scene escena = scene;
        Parent root = escena.getRoot();
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), root);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0.2);
        fadeOut.setOnFinished(event -> {
            escena.setRoot(nuevaVista);
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.8), nuevaVista);
            fadeIn.setFromValue(0.5);
            fadeIn.setToValue(1);
            fadeIn.play();
        });
        fadeOut.play();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}