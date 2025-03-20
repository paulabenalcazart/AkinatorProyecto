package ec.edu.uees.modelo;

public class AlertaSingleton {
    private static AlertaSingleton instancia;
    private String alerta;

    private AlertaSingleton() {
    }

    public static AlertaSingleton getInstance() {
        if (instancia == null) {
            instancia = new AlertaSingleton();
        }
        return instancia;
    }

    public String getAlerta() {
        return alerta;
    }

    public void setAlerta(String alerta) {
        this.alerta = alerta;
    }
}
