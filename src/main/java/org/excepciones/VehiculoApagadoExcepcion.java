package org.excepciones;

/**
 * Excepcion lanzada Cuando se intenta acelerar o frenar con el vehículo apagado.
 *
 * @author brayan estiven ortega soto
 * @version 1.0
 */
public class VehiculoApagadoExcepcion extends ExcepcionVehiculo {
    public VehiculoApagadoExcepcion(String message) {
        super(message);
    }
}
