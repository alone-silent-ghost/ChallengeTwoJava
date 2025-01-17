public class AccidenteVehicular extends Emergencia {
    public AccidenteVehicular(Ubicacion ubicacion, String nivelGravedad, int tiempoRespuesta) {
        super("Accidente Vehicular", nivelGravedad, tiempoRespuesta, ubicacion);
    }

    public void proporcionarPrimerosAuxilios() {
        System.out.println("Ambulancia proporcionando primeros auxilios en " + getUbicacion());
    }
}
