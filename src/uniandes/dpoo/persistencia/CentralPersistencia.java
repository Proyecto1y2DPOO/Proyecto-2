package uniandes.dpoo.persistencia;

/**
 * Esta clase cumple el rol de una fábrica de componentes que se encargan de manejar la persistencia de una aerolínea y de sus tiquetes
 */
public class CentralPersistencia
{
    /**
     * La cadena utilizada para identificar a los archivos en formato JSON
     */
    public static final String JSON = "JSON";

    /**
     * La cadena utilizada para identificar a los archivos en texto plano
     */
    public static final String PLAIN = "PlainText";

    /**
     * Este método retorna una nueva instancia de una clase capaz de cargar y salvar los datos de una aerolínea.
     * 
     * Las clases concretas que se pueden retornar son PersistenciaAerolineaJson y PersistenciaAerolineaPlaintext
     * 
     * @param tipoArchivo El tipo del archivo que será usado para cargar la información de la aerolínea
     * @return El objeto que debería usarse para cargar y salvar la información
     * @throws TipoInvalidoException Se lanza esta excepción si se utiliza un tipo de archivo que no es válido
     */
    public static IPersistenciaAerolinea getPersistenciaAerolinea(String tipoArchivo) throws TipoInvalidoException {
        if (tipoArchivo == null) {
            throw new TipoInvalidoException("El tipo de archivo no puede ser nulo.");
        }
        
        switch (tipoArchivo.toUpperCase()) { // Cambiado a toUpperCase() para coincidir con las constantes
            case JSON:
                return new PersistenciaAerolineaJson();
            case PLAIN:
                return new PersistenciaAerolineaPlaintext();
            default:
                throw new TipoInvalidoException("Tipo de archivo no válido: " + tipoArchivo);
        }
    }
    }

    /**
     * Este método retorna una nueva instancia de una clase capaz de cargar y salvar los datos de los tiquetes de una aerolínea
     * 
     * La única clase concreta que se puede retornar es PersistenciaTiquetesJson
     * 
     * @param tipoArchivo El tipo del archivo que será usado para cargar la información de los tiquetes
     * @return  El objeto que debería usarse para cargar y salvar la información
     * @throws TipoInvalidoException Se lanza esta excepción si se utiliza un tipo de archivo que no es válido
     */
    public static IPersistenciaTiquetes getPersistenciaTiquetes( String tipoArchivo ) throws TipoInvalidoException
    {
        if( JSON.equals( tipoArchivo ) )
            return new PersistenciaTiquetesJson( );
        else
            throw new TipoInvalidoException( tipoArchivo );
    }
}
