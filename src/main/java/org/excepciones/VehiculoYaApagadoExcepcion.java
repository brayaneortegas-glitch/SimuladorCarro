package org.excepciones;

/**
 * Excepcion lanzada Cuando se intenta apagar
 * un vehículo que ya está apagado.
 *
 * @author Brayan Estiven Ortega Soto
 * @version 1.0
 */
public class VehiculoYaApagadoExcepcion extends ExcepcionVehiculo {
    /**
     * @param message El detalle del accidente.
     */
    public VehiculoYaApagadoExcepcion(String message) {
        super(message);
    }
}
