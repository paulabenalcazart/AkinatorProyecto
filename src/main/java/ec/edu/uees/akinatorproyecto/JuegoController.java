package ec.edu.uees.akinatorproyecto;

import ec.edu.uees.modelo.BT;
import ec.edu.uees.modelo.PersonajeSingleton;
import ec.edu.uees.opciones.SFXPlayer;
import ec.edu.uees.opciones.ScreenManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class JuegoController implements Initializable{

    @FXML private AnchorPane mainAnchor;
    private Stage stage;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML private Button minimizar, cerrar, botonSi, botonNo;
    private BT<String> arbol = armarArbol();
    @FXML private Label labelNum, labelPregunta;
    private int contador = 1;
    private boolean pregunta = false;
    private char direccion = 'N';

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(!ScreenManager.getInstance().isScreenLocked()) {
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

        labelPregunta.setText(arbol.getCurrent());    
    }
    
    @FXML
    private void botonSi() throws IOException {
        SFXPlayer.playSFX();
        pregunta = arbol.irIzquierda();
        direccion = 'I';
        aumentarPregunta();
    }
    
    @FXML
    private void botonNo() throws IOException {
        SFXPlayer.playSFX();
        pregunta = arbol.irDerecha();
        direccion = 'D';
        aumentarPregunta();
    }
    
    private void aumentarPregunta() throws IOException {
        if(!pregunta) {
            if(direccion == 'I') {
                arbol.irHojaIzq();
            } else {
                arbol.irHojaDer();
            }
            PersonajeSingleton.getInstance().setPersonaje(arbol.getCurrent());
            irAFinal();
            return;
        }
        labelPregunta.setText(arbol.getCurrent());
        contador++;
        labelNum.setText("Pregunta N°"+Integer.toString(contador));
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

    private void irAFinal() throws IOException {
        App.setRoot("final");
    }

    public ArrayList<String> leerArchivo() {
        ArrayList<String> lista = new ArrayList<>();
        String archivo = "arbolActores.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lista.add(linea);
            }
        } catch (IOException ex) {
            System.out.println("Error de lectura: " + ex.getMessage());
        }
        return lista;
    }
    
    private BT<String> armarArbol() {
        BT<String> arbol = new BT<>();
        ArrayList<String> texto = leerArchivo();
        
        arbol.armarPostOrder(texto);
        
        return arbol;
    } 
}
