package org.clases;

import org.excepciones.AccidenteExcepcion;
import org.excepciones.PatinajeExcepcion;
import org.excepciones.VehiculoApagadoExcepcion;
import org.excepciones.VehiculoYaApagadoExcepcion;
import org.excepciones.VehiculoYaEncendidoExcepcion;

/**
 * La clase Vehiculo representa un vehículo simulado que controla sus estados físicos y lógicos.
 * Gestiona el motor, las llantas y aplica las reglas de conducción.
 *
 * @author Brayan Estiven Ortega Soto
 * @version 1.0
 */
public class Vehiculo {
    private double velocidad;
    private boolean encendido;
    private boolean patinando;
    private boolean accidentado;
    private Motor motor;
    private Llantas llantas;

    /**
     * Construye un nuevo vehículo con un motor y llantas específicos.
     *
     * @param motor   El motor que limitará la aceleración.
     * @param llantas Las llantas que determinarán el límite de frenado brusco.
     */
    public Vehiculo(Motor motor, Llantas llantas) {
        this.motor = motor;
        this.llantas = llantas;
        this.velocidad = 0;
        this.patinando = false;
        this.accidentado = false;
        this.encendido = false;
    }

    /**
     * Enciende el vehículo si se encuentra apagado.
     *
     * @throws VehiculoYaEncendidoExcepcion Si el vehículo ya estaba encendido.
     */
    public void encender() throws VehiculoYaEncendidoExcepcion {
        if (this.encendido == true) {
            throw new VehiculoYaEncendidoExcepcion("El vehiculo ya se encuentra encendido");
        }
        this.encendido = true;
    }

    /**
     * Apaga el vehículo. Si la velocidad actual supera los 60 km/h, el vehículo se accidenta.
     *
     * @throws VehiculoYaApagadoExcepcion Si el vehículo ya estaba apagado.
     * @throws AccidenteExcepcion         Si se apaga yendo a más de 60 km/h.
     */
    public void apagar() throws VehiculoYaApagadoExcepcion, AccidenteExcepcion {
        if (this.encendido == false) {
            throw new VehiculoYaApagadoExcepcion("El vehiculo ya esta apagado");
        }
        if (this.velocidad > 60) {
            this.velocidad = 0.0;
            this.accidentado = true;
            this.encendido = false;
            throw new AccidenteExcepcion("El vehiculo se accidentó por apagarlo a más de 60km/h");
        }
        this.velocidad = 0.0;
        this.encendido = false;
    }

    /**
     * Aumenta la velocidad del vehículo.
     *
     * @param magnitud La cantidad de km/h a incrementar.
     * @throws VehiculoApagadoExcepcion Si se intenta acelerar un vehículo apagado.
     * @throws AccidenteExcepcion       Si el vehículo ya está accidentado o si la nueva velocidad supera el límite del motor.
     * @throws PatinajeExcepcion        Si el vehículo está patinando.
     */
    public void acelerar(double magnitud) throws VehiculoApagadoExcepcion, AccidenteExcepcion, PatinajeExcepcion {
        if (this.encendido == false) {
            throw new VehiculoApagadoExcepcion("El vehiculo esta apagado");
        }
        if (this.accidentado == true) {
            throw new AccidenteExcepcion("El vehiculo se accidentó, no se puede acelerar");
        }
        // CORRECCIÓN: Se evalúa 'patinando' en lugar de 'accidentado'
        if (this.patinando == true) {
            throw new PatinajeExcepcion("El vehiculo esta patinando");
        }

        double nuevaVelocidad = magnitud + this.velocidad;

        if (nuevaVelocidad > this.motor.getVelocidadMaxima()) {
            this.accidentado = true;
            this.velocidad = 0.0;
            this.encendido = false;
            throw new AccidenteExcepcion("El vehiculo se accidentó debido a que se supero la velocidad máxima: " + motor.getVelocidadMaxima() + "Km/h");
        }

        this.velocidad = nuevaVelocidad;
    }

    /**
     * Reduce la velocidad del vehículo con un frenado estándar.
     *
     * @param magnitud La cantidad de km/h a reducir.
     * @throws VehiculoApagadoExcepcion Si el vehículo está apagado.
     * @throws PatinajeExcepcion        Si la fuerza de frenado supera la velocidad actual.
     */
    public void frenar(double magnitud) throws VehiculoApagadoExcepcion, PatinajeExcepcion {
        if (this.encendido == false) {
            throw new VehiculoApagadoExcepcion("El vehiculo está apagado, no puedes frenar.");
        }
        if (this.velocidad == 0) {
            return;
        }
        if (magnitud > 30) {
            this.frenarBruscamente(magnitud);
            return;
        }

        double velocidadOriginal = this.velocidad;

        this.velocidad = Math.max(0, this.velocidad - magnitud);

        if (magnitud > velocidadOriginal) {
            this.patinando = true;
            throw new PatinajeExcepcion("Frenaste con más fuerza que tu velocidad actual. El vehículo está patinando.");
        }

        if (this.velocidad == 0 && this.patinando == true) {
            this.patinando = false;
        }
    }

    /**
     * Reduce la velocidad del vehículo evaluando los límites de las llantas ante un frenado brusco.
     *
     * @param magnitud La cantidad de km/h a reducir.
     * @throws VehiculoApagadoExcepcion Si el vehículo está apagado.
     * @throws PatinajeExcepcion        Si se frena más fuerte que la velocidad actual o si se supera el límite de las llantas.
     */
    public void frenarBruscamente(double magnitud) throws VehiculoApagadoExcepcion, PatinajeExcepcion {
        if (this.encendido == false) {
            throw new VehiculoApagadoExcepcion("El vehiculo está apagado.");
        }
        if (this.velocidad == 0) {
            return;
        }
        if (magnitud <= 30) {
            this.frenar(magnitud);
            return;
        }

        double velocidadOriginal = this.velocidad;

        this.velocidad = Math.max(0, this.velocidad - magnitud);

        if (magnitud > velocidadOriginal) {
            this.patinando = true;
            throw new PatinajeExcepcion("Frenaste con más fuerza que tu velocidad actual. El vehículo está patinando.");
        }
        if (velocidadOriginal > this.llantas.getVelocidadMaxima()) {
            this.patinando = true;
            throw new PatinajeExcepcion("El frenado fue brusco y se superó el límite de las llantas, perdiste el control");
        }
        if (this.velocidad == 0 && this.patinando == true) {
            this.patinando = false;
        }
    }

    /**
     * @return La velocidad actual del vehículo en km/h.
     */
    public double getVelocidad() {
        return this.velocidad;
    }

    /**
     * @return true si el vehículo ha perdido el control y está patinando.
     */
    public boolean isPatinando() {
        return this.patinando;
    }

    /**
     * @return true si el vehículo está actualmente encendido.
     */
    public boolean isEncendido() {
        return this.encendido;
    }

    /**
     * @return true si el vehículo se accidentó por exceder sus límites.
     */
    public boolean isAccidentado() {
        return this.accidentado;
    }
}