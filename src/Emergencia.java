public class Emergencia {
    private final String tipo;
    private final String nivelGravedad;
    private final int tiempoRespuesta;
    protected Ubicacion ubicacion;

    public Emergencia(String tipo, String nivelGravedad, int tiempoRespuesta, Ubicacion ubicacion) {
        if (nivelGravedad == null) {
            throw new IllegalArgumentException("El nivel de gravedad no puede ser nulo");
        }
        this.tipo = tipo;
        this.nivelGravedad = nivelGravedad;
        this.tiempoRespuesta = tiempoRespuesta;
        this.ubicacion = ubicacion;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNivelGravedad() {
        return nivelGravedad;
    }

    public int getTiempoRespuesta() {
        return tiempoRespuesta;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public int getPrioridad() {
        return switch (tipo) {
            case "Incendio" -> 1;
            case "Accidente Vehicular" -> 2;
            case "Robo" -> 3;
            default -> Integer.MAX_VALUE;
        };
    }

    @Override
    public String toString() {
        return "Emergencia{" +
                "tipo='" + tipo + '\'' +
                ", nivelGravedad='" + nivelGravedad + '\'' +
                ", tiempoRespuesta=" + tiempoRespuesta +
                ", ubicacion=" + ubicacion +
                '}';
    }
}
