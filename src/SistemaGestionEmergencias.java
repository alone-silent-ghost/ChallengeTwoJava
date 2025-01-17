import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SistemaGestionEmergencias {

    private final List<Emergencia> recursos = new ArrayList<>();
    private int vehiculosDisponibles = 10;
    private int personalDisponible = 20;
    private int combustibleDisponible = 1000; // en litros
    private int emergenciasAtendidas = 0;
    private int totalTiempoRespuesta = 0;

    public void agregarEmergencia(Emergencia emergencia) {
        recursos.add(emergencia);
    }

    public String verEstadoRecursos() {
        StringBuilder estadoRecursos = new StringBuilder();
        estadoRecursos.append("Estado de los recursos:\n");
        estadoRecursos.append("Vehículos disponibles: ").append(vehiculosDisponibles).append("\n");
        estadoRecursos.append("Personal disponible: ").append(personalDisponible).append("\n");
        estadoRecursos.append("Combustible disponible: ").append(combustibleDisponible).append(" litros\n");

        estadoRecursos.append("\nEmergencias registradas:\n");
        for (Emergencia emergencia : recursos) {
            estadoRecursos.append(emergencia.toString()).append("\n");
        }

        return estadoRecursos.toString();
    }

    public String atenderEmergencia() {
        if (recursos.isEmpty()) {
            return "No hay emergencias para atender.";
        }

        // Ordenar las emergencias por prioridad y cercanía
        recursos.sort(Comparator.comparingInt(Emergencia::getPrioridad)
                .thenComparing(Emergencia::getUbicacion));

        StringBuilder resultado = new StringBuilder();
        Emergencia emergencia = recursos.remove(0); // Atender la emergencia de mayor prioridad y más cercana

        switch (emergencia.getTipo()) {
            case "Incendio" -> {
                if (vehiculosDisponibles > 0 && personalDisponible >= 5) {
                    vehiculosDisponibles--;
                    personalDisponible -= 5;
                    combustibleDisponible -= 50;
                    resultado.append("Bomberos atendiendo un incendio en ").append(emergencia.getUbicacion()).append("\n");
                } else {
                    resultado.append("No hay suficientes recursos para atender el incendio.\n");
                }
            }
            case "Accidente Vehicular" -> {
                if (vehiculosDisponibles > 0 && personalDisponible >= 3) {
                    vehiculosDisponibles--;
                    personalDisponible -= 3;
                    combustibleDisponible -= 30;
                    resultado.append("Ambulancia atendiendo un accidente vehicular en ").append(emergencia.getUbicacion()).append("\n");
                } else {
                    resultado.append("No hay suficientes recursos para atender el accidente vehicular.\n");
                }
            }
            case "Robo" -> {
                if (vehiculosDisponibles > 0 && personalDisponible >= 2) {
                    vehiculosDisponibles--;
                    personalDisponible -= 2;
                    combustibleDisponible -= 20;
                    resultado.append("Policía atendiendo un robo en ").append(emergencia.getUbicacion()).append("\n");
                } else {
                    resultado.append("No hay suficientes recursos para atender el robo.\n");
                }
            }
            default -> resultado.append("Tipo de emergencia desconocido.\n");
        }

        emergenciasAtendidas++;
        totalTiempoRespuesta += emergencia.getTiempoRespuesta();

        return resultado.toString();
    }

    public String mostrarEstadisticas() {
        StringBuilder estadisticas = new StringBuilder();
        estadisticas.append("Estadísticas del sistema:\n");
        estadisticas.append("Emergencias atendidas: ").append(emergenciasAtendidas).append("\n");
        estadisticas.append("Tiempo promedio de respuesta: ");
        if (emergenciasAtendidas > 0) {
            estadisticas.append(totalTiempoRespuesta / emergenciasAtendidas).append(" minutos\n");
        } else {
            estadisticas.append("N/A\n");
        }
        estadisticas.append("Vehículos disponibles: ").append(vehiculosDisponibles).append("\n");
        estadisticas.append("Personal disponible: ").append(personalDisponible).append("\n");
        estadisticas.append("Combustible disponible: ").append(combustibleDisponible).append(" litros\n");

        return estadisticas.toString();
    }

    public List<Emergencia> getRecursos() {
        return recursos;
    }
}


