package org.excepciones;

/**
 * Excepción que se lanza cuando se intenta encender un
 * vehículo que ya está encendido.
 *
 * @author brayan estiven ortega soto
 * @version 1.0
 */
public class VehiculoYaEncendidoExcepcion extends ExcepcionVehiculo {
    /**
     * @param message El detalle del accidente.
     */
    public VehiculoYaEncendidoExcepcion(String message) {
        super(message);
    }
}
