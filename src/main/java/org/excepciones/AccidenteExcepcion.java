package org.excepciones;

/**
 * Excepción lanzada cuando el vehículo excede su capacidad física
 * o realiza una maniobra crítica a alta velocidad.
 *
 * @author brayan estiven ortega soto
 * @version 1.0
 *
 */
public class AccidenteExcepcion extends ExcepcionVehiculo {
    /**
     * @param message El detalle del accidente.
     */
    public AccidenteExcepcion(String message) {
        super(message);
    }
}