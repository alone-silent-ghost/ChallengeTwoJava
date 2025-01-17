public class SistemaEvaluacion extends Emergencia {
    public SistemaEvaluacion(Ubicacion ubicacion, String nivelGravedad, int tiempoRespuesta) {
        super("Robo", nivelGravedad, tiempoRespuesta, ubicacion);
    }

    public void asegurarArea() {
        System.out.println("Policía asegurando el área en " + getUbicacion());
    }
}

