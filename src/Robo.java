public class Robo extends Emergencia {
    public Robo(Ubicacion ubicacion, String nivelGravedad, int tiempoRespuesta) {
        super("Robo", nivelGravedad, tiempoRespuesta, ubicacion);
    }

    public void asegurarArea() {
        System.out.println("Policía asegurando el área en " + getUbicacion());
    }
}

