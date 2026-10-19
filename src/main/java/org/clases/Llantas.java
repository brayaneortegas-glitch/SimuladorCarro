package org.clases;

/**
 * Define las llantas del vehículo y establece la velocidad máxima
 * que soportan antes de patinar en un frenado brusco.
 *
 * @author Brayan Estiven Ortega Soto
 * @version 1.0
 */
public class Llantas {
    private String tipo;
    private double velocidadMaxima;

    /**
     * Construye las llantas calculando su tolerancia de velocidad internamente.
     *
     * @param tipo El tipo de llanta ("Buenas", "Bonitas", "Baratas").
     */
    public Llantas(String tipo) {
        this.tipo = tipo;
        switch (tipo) {
            case "Buenas":
                this.velocidadMaxima = 110;
                break;
            case "Bonitas":
                this.velocidadMaxima = 70;
                break;
            case "Baratas":
                this.velocidadMaxima = 50;
                break;
            default:
                System.out.println("Llantas sin registro");
        }
    }

    /**
     * @return El tipo de las llantas.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @return La velocidad máxima soportada por las llantas en km/h.
     */
    public double getVelocidadMaxima() {
        return velocidadMaxima;
    }
}