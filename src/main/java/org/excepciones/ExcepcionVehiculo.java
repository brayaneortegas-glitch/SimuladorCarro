package org.excepciones;

/**
 * Clase base para todas las excepciones controladas del simulador de vehículos.
 *
 * @author brayan estiven ortega soto
 * @version 1.0
 */
public class ExcepcionVehiculo extends Exception {
    /**
     * @param message El detalle del error ocurrido.
     */
    public ExcepcionVehiculo(String message) {
        super(message);
    }
}