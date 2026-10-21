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
    private JTextField textFieldMostrarkilometraje;
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

        setContentPane(mainJPanel);

        JLayeredPane layeredPane = new JLayeredPane();
        panelPonerCarro.setLayout(new BorderLayout());
        panelPonerCarro.add(layeredPane, BorderLayout.CENTER);

        panelCarro = new PanelCarro(miVehiculo);

        JPanel panelBotonesFlotantes = new JPanel();
        panelBotonesFlotantes.setOpaque(false);
        panelBotonesFlotantes.add(btnEncender);
        panelBotonesFlotantes.add(btnApagar);
        panelBotonesFlotantes.add(btnAcelerar);
        panelBotonesFlotantes.add(btnFreno);
        panelBotonesFlotantes.add(btnBrusco);

        layeredPane.add(panelCarro, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(panelBotonesFlotantes, JLayeredPane.PALETTE_LAYER);

        panelPonerCarro.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                int ancho = panelPonerCarro.getWidth();
                int alto = panelPonerCarro.getHeight();

                panelCarro.setBounds(0, 0, ancho, alto);

                // Los botones se posicionado en la franja superior sobre el cielo
                panelBotonesFlotantes.setBounds(0, 30, ancho, 50);
            }
        });

        setLocationRelativeTo(null);
        textFieldMostrarEstado.setText("APAGADO");

        btnEncender.addActionListener(e -> {
            try {
                miVehiculo.encender();
                textFieldMostrarEstado.setText("");
                textFieldMostrarEstado.setText("ENCENDIDO");
            } catch (ExcepcionVehiculo ex) {
                mostrarError(ex.getMessage());
            }
        });

        btnApagar.addActionListener(e -> {
            try {
                miVehiculo.apagar();
                textFieldMostrarEstado.setText("");
                textFieldMostrarEstado.setText("APAGADO");
            } catch (ExcepcionVehiculo ex) {
                mostrarError(ex.getMessage());
                textFieldMostrarEstado.setText("ACCIDENTADO");

            }
        });

        btnAcelerar.addActionListener(e -> {
            try {
                if (miVehiculo.isEncendido() == true) {
                    String input = JOptionPane.showInputDialog(this, "¿Cuántos Km/h deseas acelerar?");
                    if (input != null && !input.isEmpty()) {
                        double magnitud = Double.parseDouble(input);
                        miVehiculo.acelerar(magnitud);
                        textFieldMostrarEstado.setText("ACELERADO");
                        textFieldMostrarkilometraje.setText(input + " KM/H");
                    }
                }

            } catch (ExcepcionVehiculo ex) {
                mostrarError(ex.getMessage());
            } catch (NumberFormatException ex) {
                mostrarError("Por favor ingresa un número válido.");
            }
        });

        btnFreno.addActionListener(e -> {
            try {
                if (miVehiculo.isEncendido() == true) {
                    String input = JOptionPane.showInputDialog(this, "¿Cuántos Km/h deseas frenar?");
                    if (input != null && !input.isEmpty()) {
                        double magnitud = Double.parseDouble(input);
                        miVehiculo.frenar(magnitud);
                        textFieldMostrarEstado.setText("FRENANDO");
                        textFieldMostrarkilometraje.setText("FRENANDO");

                    }
                }

            } catch (ExcepcionVehiculo ex) {
                mostrarError(ex.getMessage());
            } catch (NumberFormatException ex) {
                mostrarError("Por favor ingresa un número válido.");
            }
        });

        btnBrusco.addActionListener(e -> {
            try {
                if (miVehiculo.isEncendido() == true) {
                    String input = JOptionPane.showInputDialog(this, "¿Cuántos Km/h deseas frenar bruscamente?");
                    if (input != null && !input.isEmpty()) {
                        double magnitud = Double.parseDouble(input);
                        miVehiculo.frenarBruscamente(magnitud);
                        textFieldMostrarEstado.setText("FRENANDO BRUSCO");
                        textFieldMostrarkilometraje.setText("FRENO BRUSCO");
                    }
                }

            } catch (ExcepcionVehiculo ex) {
                mostrarError(ex.getMessage());
                textFieldMostrarEstado.setText("PATINANDO");
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
            FormularioSimulador ventana = new FormularioSimulador();
            ventana.setVisible(true);
    }
}