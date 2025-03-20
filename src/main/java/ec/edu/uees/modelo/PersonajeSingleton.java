package ec.edu.uees.modelo;

public class PersonajeSingleton {
    private static PersonajeSingleton instancia;
    private String personaje;

    private PersonajeSingleton() {
    }

    public static PersonajeSingleton getInstance() {
        if (instancia == null) {
            instancia = new PersonajeSingleton();
        }
        return instancia;
    }

    public void setPersonaje(String personaje) {
        this.personaje = personaje;
    }

    public String getPersonaje() {
        return personaje;
    }

}