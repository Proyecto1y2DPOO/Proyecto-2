package uniandes.dpoo.persistencia;

import java.io.IOException;

import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.exceptions.InformacionInconsistenteException;

/**
 * Interfaz para la persistencia de datos de una aerolínea.
 * Define los métodos para cargar y salvar la información de la aerolínea.
 */
public interface IPersistenciaAerolinea {

    /**
     * Carga la información de todos los elementos de una aerolínea desde un archivo, 
     * excluyendo clientes y tiquetes, y actualiza la estructura de objetos de la aerolínea.
     * 
     * @param archivo La ruta al archivo que contiene la información de la aerolínea.
     * @param aerolinea La instancia de Aerolinea en la que se debe almacenar la información.
     * @throws IOException Si hay problemas leyendo el archivo.
     * @throws InformacionInconsistenteException Si la información en el archivo es inconsistente
     *         con la estructura esperada o con el estado actual de la aerolínea.
     */
    void cargarAerolinea(String archivo, Aerolinea aerolinea) throws IOException, InformacionInconsistenteException;

    /**
     * Salva la información de todos los elementos de una aerolínea en un archivo, 
     * excluyendo clientes y tiquetes.
     * 
     * @param archivo La ruta al archivo donde se debe guardar la información de la aerolínea.
     * @param aerolinea La instancia de Aerolinea que contiene la información a almacenar.
     * @throws IOException Si hay problemas escribiendo en el archivo.
     */
    void salvarAerolinea(String archivo, Aerolinea aerolinea) throws IOException;
}