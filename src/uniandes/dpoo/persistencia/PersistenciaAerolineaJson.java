package uniandes.dpoo.persistencia;

import org.json.JSONObject;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PersistenciaAerolineaJson implements IPersistenciaAerolinea {

    public void cargarAerolinea(String archivo, Aerolinea aerolinea) throws IOException, InformacionInconsistenteException {
        // Leer el archivo JSON
        String contenido = new String(Files.readAllBytes(Paths.get(archivo)));
        JSONObject jsonObject = new JSONObject(contenido);

        // Aquí iría la lógica para cargar la información del JSON en la instancia de Aerolinea
        // Ejemplo ficticio:
        // aerolinea.cargarDatosDesdeJson(jsonObject);
    }

    public void salvarAerolinea(String archivo, Aerolinea aerolinea) throws IOException {
        // Convertir la información de la aerolínea a JSON
        JSONObject jsonObject = new JSONObject();
        // Aquí iría la lógica para convertir la información de Aerolinea a JSON
        // Ejemplo ficticio:
        // jsonObject = aerolinea.convertirADatosJson();

        // Escribir el JSON al archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            writer.write(jsonObject.toString(4)); // El segundo parámetro es la indentación para formato legible
        }
    }
}
}