public class EmergenciaFactory {
    public static Emergencia crearEmergencia(String tipo, Ubicacion ubicacion, String alta1) {
        switch (tipo) {
            case "Incendio" -> {
                String nivelGravedad = "Alta";
                return new Incendio(ubicacion, nivelGravedad, 10);
            }

            case "Accidente Vehicular" -> {
                String nivelGravedad = "Media";
                return new AccidenteVehicular(ubicacion, nivelGravedad, 20);
            }

            case "Robo" -> {
                String nivelGravedad = "Baja";
                return new Robo(ubicacion, nivelGravedad, 30);
            }

            default -> throw new IllegalArgumentException("Tipo de emergencia desconocido: " + tipo);
        }
    }
}



