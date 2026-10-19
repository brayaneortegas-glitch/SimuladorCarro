package org.lector_archivo;

import org.clases.Llantas;
import org.clases.Motor;
import org.clases.Vehiculo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Se encarga de leer el archivo de configuración plano (ej. config.txt)
 * para instanciar las piezas físicas del vehículo (Motor y Llantas)
 * y ensamblar el objeto {@link Vehiculo} al inicio de la ejecución.
 *
 * @author Jacobo Lengua
 * @version 1.0
 */
public class ConfiguradorVehiculo {
    /**
     * Lee la configuración del archivo especificado en la ruta y construye
     * un objeto {@link Vehiculo} completamente inicializado y listo para la simulación.
     *
     * @param ruta La ubicación o nombre del archivo de texto con los parámetros.
     * @return Una instancia de {@link Vehiculo} con el motor y las llantas configurados.
     */
    public Vehiculo cargarConfiguracion(String ruta) {
        Map<String, String> datos = leerArchivo(ruta);

        // Extraemos los datos del diccionario (si no los encuentra, pone unos por defecto)
        String tipoLlanta = datos.getOrDefault("llantas", "Baratas");
        int cilindraje = 1000;

        try {
            if (datos.containsKey("motor")) {
                cilindraje = Integer.parseInt(datos.get("motor"));
            }
        } catch (Exception e) {
            System.out.println("Error al procesar el cilindraje del motor.");
        }

        // Creamos las piezas con un solo parámetro (Requiere que Motor y Llantas estén actualizados)
        Motor motor = crearMotor(cilindraje);
        Llantas llantas = crearLlanta(tipoLlanta);

        // Retornamos el vehículo ya armado
        return new Vehiculo(motor, llantas);
    }

    /**
     * Lee el archivo de texto línea por línea y extrae los pares de clave-valor
     * (por ejemplo, tipo de llanta y cilindraje del motor).
     *
     * @param ruta La ruta del archivo a leer.
     * @return Un mapa que asocia la propiedad con su valor en texto.
     */
    private Map<String, String> leerArchivo(String ruta) {
        Map<String, String> configuracion = new HashMap<>();

        try {
            File archivo = new File(ruta);
            Scanner lector = new Scanner(archivo);

            // Leemos palabra por palabra y las guardamos en parejas
            while (lector.hasNext()) {
                String llave = lector.next();
                if (lector.hasNext()) {
                    String valor = lector.next();
                    configuracion.put(llave.toLowerCase(), valor);
                }
            }
            lector.close();

        } catch (Exception e) {
            System.out.println("Error: No se encontró el archivo " + ruta + " en la carpeta del proyecto.");
        }

        return configuracion;
    }

    /**
     * Instancia un nuevo objeto {@link Motor} basado en el cilindraje leído.
     *
     * @param cilindraje Capacidad del motor.
     * @return Instancia de Motor.
     */
    private Motor crearMotor(int cilindraje) {
        return new Motor(cilindraje);
    }

    /**
     * Instancia un nuevo objeto {@link Llantas} basado en el tipo leído.
     *
     * @param tipo Categoría de las llantas.
     * @return Instancia de Llantas.
     */
    private Llantas crearLlanta(String tipo) {
        return new Llantas(tipo);
    }
}