package org.clases;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConfiguradorVehiculo {

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

    private Motor crearMotor(int cilindraje) {
        return new Motor(cilindraje);
    }

    private Llantas crearLlanta(String tipo) {
        return new Llantas(tipo);
    }
}