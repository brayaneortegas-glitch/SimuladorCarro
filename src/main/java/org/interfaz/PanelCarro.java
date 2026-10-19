package org.interfaz;

import org.clases.Vehiculo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelCarro extends JPanel {
    private Vehiculo vehiculo;
    private int posX = 100;
    private Timer timerAnimacion;

    // Ahora exige TU vehículo por parámetro
    public PanelCarro(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        setPreferredSize(new Dimension(800, 450));

        timerAnimacion = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarMovimiento();
            }
        });
        timerAnimacion.start();
    }

    private void actualizarMovimiento() {
        // El carro avanza visualmente según la velocidad real de TU lógica
        if (vehiculo.getVelocidad() > 0) {
            posX += Math.max(1, vehiculo.getVelocidad() / 8);
        }
        if (posX > getWidth()) {
            posX = -240;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int ancho = getWidth();

        g.setColor(new Color(135, 206, 235));
        g.fillRect(0, 0, ancho, 200);
        g.setColor(new Color(34, 139, 34));
        g.fillRect(0, 200, ancho, 80);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 280, ancho, 180);
        g.setColor(Color.YELLOW);
        for (int x = 10; x < ancho; x += 60) {
            g.fillRect(x, 365, 35, 10);
        }

        // Leemos TUS estados para cambiar los colores
        Color colorCarro = Color.RED;
        if (vehiculo.isAccidentado()) colorCarro = Color.BLACK;
        else if (vehiculo.isPatinando()) colorCarro = Color.ORANGE;

        g.setColor(colorCarro);
        g.fillRect(posX + 50, 240, 140, 50);
        g.setColor(new Color(173, 216, 230));
        g.fillRect(posX + 60, 248, 55, 35);
        g.fillRect(posX + 125, 248, 55, 35);
        g.setColor(colorCarro);
        g.fillRect(posX, 290, 240, 50);

        g.setColor(vehiculo.isEncendido() ? Color.YELLOW : Color.GRAY);
        g.fillRect(posX + 235, 302, 5, 15);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(posX - 5, 302, 8, 15);

        g.setColor(Color.BLACK);
        g.fillOval(posX + 30, 320, 50, 50);
        g.fillOval(posX + 160, 320, 50, 50);
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(posX + 45, 335, 20, 20);
        g.fillOval(posX + 175, 335, 20, 20);

        // Textos informativos
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 13));
        String estado = vehiculo.isAccidentado() ? "Accidentado" : (vehiculo.isPatinando() ? "Patinando" : (vehiculo.isEncendido() ? "Encendido" : "Apagado"));
        g.drawString("Estado: " + estado, 20, 25);
        g.drawString("Velocidad actual: " + vehiculo.getVelocidad() + " km/h", 20, 45);
    }
}