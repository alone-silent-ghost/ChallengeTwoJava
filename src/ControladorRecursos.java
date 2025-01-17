public class ControladorRecursos {
    private static ControladorRecursos instancia;

    private ControladorRecursos() { }

    public static ControladorRecursos getInstancia() {
        if (instancia == null) {
            instancia = new ControladorRecursos();
        }
        return instancia;
    }

    // Métodos para gestionar recursos
    public void gestionarRecurso(String recurso) {
        System.out.println("Gestionando recurso: " + recurso);
    }
}

