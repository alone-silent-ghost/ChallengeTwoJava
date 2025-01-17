public class Ubicacion implements Comparable<Ubicacion> {
    private final double latitud;
    private final double longitud;

    public Ubicacion(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    @Override
    public int compareTo(Ubicacion otra) {
        double distanciaEsta = Math.sqrt(latitud * latitud + longitud * longitud);
        double distanciaOtra = Math.sqrt(otra.latitud * otra.latitud + otra.longitud * otra.longitud);
        return Double.compare(distanciaEsta, distanciaOtra);
    }

    @Override
    public String toString() {
        return "Latitud: " + latitud + ", Longitud: " + longitud;
    }
}

