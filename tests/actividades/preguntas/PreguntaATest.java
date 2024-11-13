package actividades.preguntas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.actividades.preguntas.PreguntaA;

class PreguntaATest {

    private PreguntaA preguntaA;
    private static final String PREGUNTA_TEXT = "¿Cuál es la capital de Francia?";
    private static final String RESPUESTA_TEXT = "París";

    @BeforeEach
    void setUp() {
        preguntaA = new PreguntaA(PREGUNTA_TEXT);
    }

    @Test
    void testConstructor() {
        assertEquals(PREGUNTA_TEXT, preguntaA.getPregunta(), "El texto de la pregunta debería coincidir con el valor asignado en el constructor");
    }

    @Test
    void testIngresarRespuesta() {
        preguntaA.ingresarRespuesta(RESPUESTA_TEXT);
        assertEquals(RESPUESTA_TEXT, preguntaA.getRespuesta(), "La respuesta debería coincidir con el valor ingresado");
    }

    @Test
    void testSetPregunta() {
        String nuevaPregunta = "¿Cuál es la capital de España?";
        preguntaA.setPregunta(nuevaPregunta);
        assertEquals(nuevaPregunta, preguntaA.getPregunta(), "El texto de la pregunta debería coincidir con el nuevo valor asignado");
    }

    @Test
    void testGetRespuestaSinRespuesta() {
        assertNull(preguntaA.getRespuesta(), "La respuesta debería ser nula inicialmente");
    }
}
