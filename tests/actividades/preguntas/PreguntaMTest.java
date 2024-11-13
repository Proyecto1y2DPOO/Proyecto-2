package actividades.preguntas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.actividades.preguntas.PreguntaM;

class PreguntaMTest {

    private PreguntaM preguntaM;
    private static final String PREGUNTA_TEXT = "¿Cuál es el planeta más cercano al sol?";
    private static final String OPCION1 = "Venus";
    private static final String OPCION2 = "Mercurio";
    private static final String OPCION3 = "Marte";
    private static final String OPCION4 = "Júpiter";
    private static final int OPCION_CORRECTA = 2;
    private static final String EXPLICACION = "Mercurio es el planeta más cercano al sol.";

    @BeforeEach
    void setUp() {
        preguntaM = new PreguntaM(PREGUNTA_TEXT, OPCION1, OPCION2, OPCION3, OPCION4, OPCION_CORRECTA, EXPLICACION);
    }

    @Test
    void testConstructor() {
        assertEquals(PREGUNTA_TEXT, preguntaM.getPregunta(), "El texto de la pregunta debería coincidir con el valor asignado en el constructor");
        assertEquals(OPCION1, preguntaM.getOpcion1(), "Opción 1 debería coincidir con el valor asignado");
        assertEquals(OPCION2, preguntaM.getOpcion2(), "Opción 2 debería coincidir con el valor asignado");
        assertEquals(OPCION3, preguntaM.getOpcion3(), "Opción 3 debería coincidir con el valor asignado");
        assertEquals(OPCION4, preguntaM.getOpcion4(), "Opción 4 debería coincidir con el valor asignado");
        assertEquals(OPCION_CORRECTA, preguntaM.getOpcionC(), "La opción correcta debería coincidir con el valor asignado");
        assertEquals(EXPLICACION, preguntaM.getExplicacion(), "La explicación debería coincidir con el valor asignado");
    }

    @Test
    void testIngresarRespuestaCorrecta() {
        String resultado = preguntaM.ingresarRespuesta(OPCION_CORRECTA);
        assertEquals("La respuesta es correcta por:" + EXPLICACION, resultado, "El resultado debería indicar que la respuesta es correcta");
        assertTrue(preguntaM.getBool(), "El valor de 'bool' debería ser true cuando la respuesta es correcta");
        assertEquals(OPCION_CORRECTA, preguntaM.getRespuesta(), "La respuesta registrada debería coincidir con la opción correcta");
    }

    @Test
    void testIngresarRespuestaIncorrecta() {
        int opcionIncorrecta = 1;
        String resultado = preguntaM.ingresarRespuesta(opcionIncorrecta);
        assertEquals("La respuesta es incorrecta por:" + EXPLICACION, resultado, "El resultado debería indicar que la respuesta es incorrecta");
        assertFalse(preguntaM.getBool(), "El valor de 'bool' debería ser false cuando la respuesta es incorrecta");
        assertEquals(opcionIncorrecta, preguntaM.getRespuesta(), "La respuesta registrada debería coincidir con la opción seleccionada");
    }

    @Test
    void testSettersAndGetters() {
        preguntaM.setPregunta("¿Cuál es el océano más grande?");
        preguntaM.setOpcion1("Atlántico");
        preguntaM.setOpcion2("Pacífico");
        preguntaM.setOpcion3("Índico");
        preguntaM.setOpcion4("Ártico");
        preguntaM.setOpcionC(2);
        preguntaM.setExplicacion("El océano Pacífico es el más grande.");
        
        assertEquals("¿Cuál es el océano más grande?", preguntaM.getPregunta(), "El texto de la pregunta debería coincidir con el nuevo valor asignado");
        assertEquals("Atlántico", preguntaM.getOpcion1(), "Opción 1 debería coincidir con el nuevo valor asignado");
        assertEquals("Pacífico", preguntaM.getOpcion2(), "Opción 2 debería coincidir con el nuevo valor asignado");
        assertEquals("Índico", preguntaM.getOpcion3(), "Opción 3 debería coincidir con el nuevo valor asignado");
        assertEquals("Ártico", preguntaM.getOpcion4(), "Opción 4 debería coincidir con el nuevo valor asignado");
        assertEquals(2, preguntaM.getOpcionC(), "La opción correcta debería coincidir con el nuevo valor asignado");
        assertEquals("El océano Pacífico es el más grande.", preguntaM.getExplicacion(), "La explicación debería coincidir con el nuevo valor asignado");
    }
}
