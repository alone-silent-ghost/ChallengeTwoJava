import java.util.List;

public class PrioridadGravedadAlta implements EstrategiaPrioridad {
    @Override
    public void priorizarEmergencias(List<Emergencia> emergencias) {
        emergencias.sort((e1, e2) -> e2.getNivelGravedad().compareTo(e1.getNivelGravedad()));
        System.out.println("Emergencias priorizadas por gravedad alta:");
        for (Emergencia e : emergencias) {
            System.out.println(e);
        }
    }
}

