package org.interfaz;

import org.clases.Vehiculo;
import org.excepciones.ExcepcionVehiculo;
import org.lector_archivo.ConfiguradorVehiculo;

import javax.swing.*;
import java.awt.*;

/**
 * Representa la ventana principal del simulador gráfico construida en Java Swing.
 * Gestiona la interacción del usuario mediante botones de control (encender, apagar,
 * acelerar, frenar) y captura las excepciones del modelo lógico para mostrarlas
 * mediante alertas interactivas.
 *
 * @author Jacobo Lengua Amesquita
 * @version 1.0
 */
public class FormularioSimulador extends JFrame {
    private JButton btnEncender;
    private JButton btnApagar;
    private JButton btnAcelerar;
    private JButton btnFreno;
    private JButton btnBrusco;
    private JPanel mainJPanel;
    private JPanel panelPonerCarro;
    private JTextField textFieldMostrarEstado;
    private PanelCarro panelCarro;
    private Vehiculo miVehiculo;

    /**
     * Construye e inicializa los componentes visuales de la ventana,
     * ejecuta el configurador para cargar el vehículo desde el archivo de texto
     * y vincula las acciones de los botones con su respectivo manejo de excepciones.
     */
    public FormularioSimulador() {
        setTitle("Simulador de Vehículo - Excepciones");
        setSize(850, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        ConfiguradorVehiculo configurador = new ConfiguradorVehiculo();
        miVehiculo = configurador.cargarConfiguracion("src/main/java/org/lector_archivo/config.txt");

        setLayout(new BorderLayout());
        add(mainJPanel, BorderLayout.WEST);

        panelCarro = new PanelCarro(miVehiculo);
        add(panelCarro, BorderLayout.CENTER);


        btnEncender.addActionListener(e -> {
            try {
                miVehiculo.encender();
                textFieldMostrarEstado.setText("ENCENDIDO");
            } catch (ExcepcionVehiculo ex) {
                mostrarError(ex.getMessage());
            }
        });

        btnApagar.addActionListener(e -> {
            try {
                miVehiculo.apagar();
            } catch (ExcepcionVehiculo ex) {
                mostrarError(ex.getMessage());
            }
        });

        btnAcelerar.addActionListener(e -> {
            try {
                String input = JOptionPane.showInputDialog(this, "¿Cuántos Km/h deseas acelerar?");
                if (input != null && !input.isEmpty()) {
                    double magnitud = Double.parseDouble(input);
                    miVehiculo.acelerar(magnitud);
                }
            } catch (ExcepcionVehiculo ex) {
                mostrarError(ex.getMessage());
            } catch (NumberFormatException ex) {
                mostrarError("Por favor ingresa un número válido.");
            }
        });

        btnFreno.addActionListener(e -> {
            try {
                String input = JOptionPane.showInputDialog(this, "¿Cuántos Km/h deseas frenar?");
                if (input != null && !input.isEmpty()) {
                    double magnitud = Double.parseDouble(input);
                    miVehiculo.frenar(magnitud);
                }
            } catch (ExcepcionVehiculo ex) {
                mostrarError(ex.getMessage());
            } catch (NumberFormatException ex) {
                mostrarError("Por favor ingresa un número válido.");
            }
        });

        btnBrusco.addActionListener(e -> {
            try {
                String input = JOptionPane.showInputDialog(this, "¿Cuántos Km/h deseas frenar bruscamente?");
                if (input != null && !input.isEmpty()) {
                    double magnitud = Double.parseDouble(input);
                    miVehiculo.frenarBruscamente(magnitud);
                }
            } catch (ExcepcionVehiculo ex) {
                mostrarError(ex.getMessage());
            } catch (NumberFormatException ex) {
                mostrarError("Por favor ingresa un número válido.");
            }
        });
    }

    /**
     * Muestra una ventana emergente de advertencia con el mensaje de la excepción controlada.
     *
     * @param mensaje El detalle del error lanzado por el vehículo.
     */
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Alerta del Vehículo", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Método principal (punto de entrada) que despliega la interfaz gráfica
     * de forma segura dentro del hilo de eventos de Swing (EventQueue).
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FormularioSimulador ventana = new FormularioSimulador();
            ventana.setVisible(true);
        });
    }
}