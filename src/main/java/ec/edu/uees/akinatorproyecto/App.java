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
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
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
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.3), root);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0.2);
        fadeOut.setOnFinished(event -> {
            escena.setRoot(nuevaVista);
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.6), nuevaVista);
            fadeIn.setFromValue(0.5);
            fadeIn.setToValue(1);
            fadeIn.play();
        });
        fadeOut.play();
    }
    
    static void setRootSinAnimar(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent root = loader.load();
        if (primaryStage == null) {
            System.out.println("Error: el Stage es null, no se puede cambiar la escena.");
            return;
        }
        Scene newScene = new Scene(root);
        scene = newScene;
        primaryStage.setScene(newScene);
        primaryStage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}