public class ConcreteObservador implements Observador {
    @Override
    public void actualizar() {
        // Lógica de actualización específica
        System.out.println("Observador actualizado con nueva información.");
    }
}