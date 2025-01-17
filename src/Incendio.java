public class Incendio extends Emergencia {
    public Incendio(Ubicacion ubicacion, String nivelGravedad, int tiempoRespuesta) {
        super("Incendio", nivelGravedad, tiempoRespuesta, ubicacion);
    }

    public void apagarFuego() {
        System.out.println("Bomberos apagando un incendio en " + getUbicacion());
    }
}

