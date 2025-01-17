import java.util.List;

public class PrioridadCercania implements EstrategiaPrioridad {
    @Override
    public void priorizarEmergencias(List<Emergencia> emergencias) {
        emergencias.sort((e1, e2) -> e1.getUbicacion().compareTo(e2.getUbicacion()));
        System.out.println("Emergencias priorizadas por cercanía:");
        for (Emergencia e : emergencias) {
            System.out.println(e);
        }
    }
}

