package org.clases;

/**
 * Define el motor del vehículo y establece la velocidad máxima permitida
 * según su cilindraje.
 *
 * @author Brayan Estiven Ortega Soto
 * @version 1.0
 */
public class Motor {
    private int cilindraje;
    private double velocidadMaxima;

    /**
     * Construye un motor calculando su velocidad límite internamente.
     *
     * @param cilindraje Capacidad del motor (ej. 1000, 2000, 3000).
     */
    public Motor(int cilindraje) {
        this.cilindraje = cilindraje;
        switch (cilindraje) {
            case 1000:
                this.velocidadMaxima = 100;
                break;
            case 2000:
                this.velocidadMaxima = 160;
                break;
            case 3000:
                this.velocidadMaxima = 220;
                break;
            default:
                System.out.println("Motor sin registro");
        }
    }

    /**
     * @return El cilindraje del motor.
     */
    public int getCilindraje() {
        return cilindraje;
    }

    /**
     * @return La velocidad máxima permitida por este motor en km/h.
     */
    public double getVelocidadMaxima() {
        return velocidadMaxima;
    }
}