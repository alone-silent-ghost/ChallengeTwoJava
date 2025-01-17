import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Main {
    private final SistemaGestionEmergencias sistema = new SistemaGestionEmergencias();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Sistema de Gestión de Emergencias");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton buttonRegistrar = new JButton("Registrar Nueva Emergencia");
        JButton buttonVerRecursos = new JButton("Ver Estado de Recursos");
        JButton buttonAtender = new JButton("Atender Emergencia");
        JButton buttonEstadisticas = new JButton("Mostrar Estadísticas");

        buttonRegistrar.addActionListener((@SuppressWarnings("unused") ActionEvent e) -> registrarEmergencia());
        buttonVerRecursos.addActionListener((@SuppressWarnings("unused") ActionEvent e) -> verRecursos());
        buttonAtender.addActionListener((@SuppressWarnings("unused") ActionEvent e) -> atenderEmergencia());
        buttonEstadisticas.addActionListener((@SuppressWarnings("unused") ActionEvent e) -> mostrarEstadisticas());

        panel.add(buttonRegistrar);
        panel.add(buttonVerRecursos);
        panel.add(buttonAtender);
        panel.add(buttonEstadisticas);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void registrarEmergencia() {
        JFrame frame = new JFrame("Registrar Nueva Emergencia");
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel labelTipo = new JLabel("Tipo de Emergencia:");
        JComboBox<String> comboBoxTipo = new JComboBox<>(new String[]{"Incendio", "Accidente Vehicular", "Robo"});

        JLabel labelLatitud = new JLabel("Latitud:");
        JTextField textFieldLatitud = new JTextField();

        JLabel labelLongitud = new JLabel("Longitud:");
        JTextField textFieldLongitud = new JTextField();

        JLabel labelGravedad = new JLabel("Nivel de Gravedad:");
        JComboBox<String> comboBoxGravedad = new JComboBox<>(new String[]{"Alta", "Media", "Baja"});

        JButton buttonAgregar = new JButton("Agregar Emergencia");

        buttonAgregar.addActionListener((@SuppressWarnings("unused") ActionEvent e) -> {
            String tipo = (String) comboBoxTipo.getSelectedItem();
            double latitud = Double.parseDouble(textFieldLatitud.getText());
            double longitud = Double.parseDouble(textFieldLongitud.getText());
            String gravedad = (String) comboBoxGravedad.getSelectedItem();
            Ubicacion ubicacion = new Ubicacion(latitud, longitud);
            Emergencia emergencia = EmergenciaFactory.crearEmergencia(tipo, ubicacion, gravedad);
            sistema.agregarEmergencia(emergencia);
            JOptionPane.showMessageDialog(frame, "Emergencia registrada exitosamente.");
            frame.dispose();
        });

        panel.add(labelTipo);
        panel.add(comboBoxTipo);
        panel.add(labelLatitud);
        panel.add(textFieldLatitud);
        panel.add(labelLongitud);
        panel.add(textFieldLongitud);
        panel.add(labelGravedad);
        panel.add(comboBoxGravedad);
        panel.add(buttonAgregar);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void verRecursos() {
        JFrame frame = new JFrame("Estado de Recursos");
        frame.setSize(400, 300);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText(sistema.verEstadoRecursos());

        frame.add(new JScrollPane(textArea));
        frame.setVisible(true);
    }

    private void atenderEmergencia() {
        List<Emergencia> emergencias = sistema.getRecursos();
        if (emergencias.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay emergencias para atender.", "Atender Emergencia", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Ordenar las emergencias por prioridad
        emergencias.sort((e1, e2) -> {
            int prioridad1 = e1.getPrioridad();
            int prioridad2 = e2.getPrioridad();
            if (prioridad1 != prioridad2) {
                return Integer.compare(prioridad1, prioridad2);
            }
            return e1.getUbicacion().compareTo(e2.getUbicacion());
        });

        // Crear un array de opciones para el cuadro de diálogo
        String[] opciones = emergencias.stream().map(Emergencia::toString).toArray(String[]::new);

        // Mostrar el cuadro de diálogo para seleccionar una emergencia
        String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione una emergencia para atender:", "Atender Emergencia", JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

        if (seleccion != null) {
            // Encontrar la emergencia seleccionada
            Emergencia emergenciaSeleccionada = emergencias.stream().filter(e -> e.toString().equals(seleccion)).findFirst().orElse(null);
            if (emergenciaSeleccionada != null) {
                String resultado = sistema.atenderEmergencia(emergenciaSeleccionada);
                JOptionPane.showMessageDialog(null, resultado, "Resultado de Atender Emergencia", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void mostrarEstadisticas() {
        JFrame frame = new JFrame("Estadísticas del Sistema");
        frame.setSize(400, 300);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText(sistema.mostrarEstadisticas());

        frame.add(new JScrollPane(textArea));
        frame.setVisible(true);
    }
}

