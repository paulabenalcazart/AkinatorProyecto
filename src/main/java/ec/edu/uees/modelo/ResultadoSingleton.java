package ec.edu.uees.modelo;

public class ResultadoSingleton {
   private static ResultadoSingleton instancia;
    private boolean resultado = false;

    private ResultadoSingleton() {
    }

    public static ResultadoSingleton getInstance() {
        if (instancia == null) {
            instancia = new ResultadoSingleton();
        }
        return instancia;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }

    public boolean getResultado() {
        return resultado;
    }

}