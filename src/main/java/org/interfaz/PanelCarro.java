package org.interfaz;

import org.clases.Vehiculo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel gráfico encargado de renderizar la animación del vehículo y su entorno
 * en tiempo real utilizando un temporizador (Timer). Lee directamente los estados
 * y la velocidad de la instancia de {@link Vehiculo} para actualizar los gráficos.
 *
 * @author Jacobo Lengua
 * @version 1.0
 */
public class PanelCarro extends JPanel {
    private Vehiculo vehiculo;
    private int posX = 100;
    private Timer timerAnimacion;

    /**
     * Construye el panel de animación vinculándolo al vehículo recibido por parámetro
     * e inicia el bucle de actualización visual cada 40 milisegundos.
     *
     * @param vehiculo El vehículo configurado que se va a animar y monitorear.
     */
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

    /**
     * Actualiza la posición horizontal del vehículo en el lienzo según su velocidad actual
     * y solicita un redibujado de la pantalla (repintado).
     */
    private void actualizarMovimiento() {
        if (vehiculo.getVelocidad() > 0) {
            posX += Math.max(1, vehiculo.getVelocidad() / 8);
        }
        if (posX > getWidth()) {
            posX = -240;
        }
        repaint();
    }

    /**
     * Método sobreescrito del ciclo de vida de Swing encargado de dibujar
     * el escenario (cielo, pasto, carretera), la estructura geométrica del carro,
     * los colores condicionales (según estado) y el panel informativo superior.
     *
     * @param g El contexto gráfico utilizado para dibujar las formas en 2D.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int ancho = getWidth();

        // 1. Cielo
        g.setColor(new Color(135, 206, 235));
        g.fillRect(0, 0, ancho, 200);

        // 2. Pasto
        g.setColor(new Color(34, 139, 34));
        g.fillRect(0, 200, ancho, 80);

        // 3. Carretera y líneas
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 280, ancho, 180);
        g.setColor(Color.YELLOW);
        for (int x = 10; x < ancho; x += 60) {
            g.fillRect(x, 365, 35, 10);
        }

        // Selección de color del chasis según los estados del vehículo
        Color colorCarro = Color.RED;
        if (vehiculo.isAccidentado()) colorCarro = Color.BLACK;
        else if (vehiculo.isPatinando()) colorCarro = Color.ORANGE;

        // Estructura del vehículo
        g.setColor(colorCarro);
        g.fillRect(posX + 50, 240, 140, 50);
        g.setColor(new Color(173, 216, 230));
        g.fillRect(posX + 60, 248, 55, 35);
        g.fillRect(posX + 125, 248, 55, 35);
        g.setColor(colorCarro);
        g.fillRect(posX, 290, 240, 50);

        // Luces delanteras y traseras
        g.setColor(vehiculo.isEncendido() ? Color.YELLOW : Color.GRAY);
        g.fillRect(posX + 235, 302, 5, 15);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(posX - 5, 302, 8, 15); // Corregido con guion '-'

        // Llantas y rines
        g.setColor(Color.BLACK);
        g.fillOval(posX + 30, 320, 50, 50);
        g.fillOval(posX + 160, 320, 50, 50);
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(posX + 45, 335, 20, 20);
        g.fillOval(posX + 175, 335, 20, 20);
    }
}