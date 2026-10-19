package org.excepciones;

/**
 * Excepcion lanzada Cuando el vehículo patina debido a
 * un frenado brusco o a una intensidad de frenado superior a su velocidad actual.
 *
 * @author brayan estiven ortega soto
 * @version 1.0
 */
public class PatinajeExcepcion extends ExcepcionVehiculo {
    /**
     * @param message El detalle del accidente.
     */
    public PatinajeExcepcion(String message) {
        super(message);
    }
}
