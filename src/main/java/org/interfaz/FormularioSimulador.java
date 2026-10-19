package org.interfaz;

import org.clases.ConfiguradorVehiculo;
import org.clases.Vehiculo;
import org.excepciones.ExcepcionVehiculo;

import javax.swing.*;
import java.awt.*;

public class FormularioSimulador extends JFrame {
    private JButton btnEncender;
    private JButton btnApagar;
    private JButton btnAcelerar;
    private JButton btnFreno;
    private JButton btnBrusco;
    private JPanel mainJPanel;
    private PanelCarro panelCarro;
    private Vehiculo miVehiculo; // TU vehículo

    public FormularioSimulador() {
        setTitle("Simulador de Vehículo - Excepciones");
        setSize(850, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // 1. ABRIMOS EL ARCHIVO Y ARMAMOS EL CARRO CON TU LÓGICA
        ConfiguradorVehiculo configurador = new ConfiguradorVehiculo();
        miVehiculo = configurador.cargarConfiguracion("src/main/java/org/lector_archivo/config.txt");

        setLayout(new BorderLayout());
        add(mainJPanel, BorderLayout.WEST);

        // 2. LE PASAMOS TU CARRO AL LIENZO DE DIBUJO
        panelCarro = new PanelCarro(miVehiculo);
        add(panelCarro, BorderLayout.CENTER);

        // 3. BOTONES CON TRY-CATCH (Capturando tus excepciones)
        btnEncender.addActionListener(e -> {
            try {
                miVehiculo.encender();
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

    // Método auxiliar para no repetir código en los catch
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Alerta del Vehículo", JOptionPane.WARNING_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FormularioSimulador ventana = new FormularioSimulador();
            ventana.setVisible(true);
        });
    }
}