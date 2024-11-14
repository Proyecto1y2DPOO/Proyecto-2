package uniandes.dpoo.cursos.tests.actividades;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.actividades.Encuesta;
import uniandes.dpoo.actividades.preguntas.PreguntaA;

class EncuestaTest {

    private Encuesta encuesta;
    private static final String TITULO = "Encuesta de Satisfacción";
    private static final String DESCRIPCION = "Encuesta para evaluar satisfacción del curso";
    private static final int DURACION = 30;
    private static final String NIVEL_DIFICULTAD = "Baja";
    private static final List<String> ACTIVIDADES_PREVIAS = new ArrayList<>();
    private static final LocalDateTime FECHA_LIMITE = LocalDateTime.now().plusDays(1);
    private static final boolean OBLIGATORIA = false;
    private static final String CREADOR = "Profesor";
    private static final String OBJETIVO = "aprender mucho";

    @BeforeEach
    void setUp() {
        encuesta = new Encuesta(TITULO, DESCRIPCION, DURACION, NIVEL_DIFICULTAD, ACTIVIDADES_PREVIAS, FECHA_LIMITE, OBLIGATORIA, CREADOR, OBJETIVO);
        encuesta.setPreguntas(new ArrayList<>());
    }

    @Test
    void testAgregarPregunta() {
        encuesta.agregarPregunta("¿Qué tan satisfecho está con el curso?");
        assertEquals(1, encuesta.getPreguntas().size(), "La pregunta debería haberse agregado a la encuesta");
    }

    @Test
    void testIngresarRespuesta() {
        encuesta.agregarPregunta("¿Qué tan satisfecho está con el curso?");
        encuesta.ingresarRespuesta("Muy satisfecho", 0);
        PreguntaA pregunta = encuesta.getPreguntas().get(0);
        assertEquals("Muy satisfecho", pregunta.getRespuesta(), "La respuesta debería haberse registrado correctamente");
    }

    @Test
    void testGetPreguntas() {
        List<PreguntaA> preguntas = new ArrayList<>();
        preguntas.add(new PreguntaA("¿Cuál es su opinión sobre la duración del curso?"));
        encuesta.setPreguntas(preguntas);
        assertEquals(preguntas, encuesta.getPreguntas(), "La lista de preguntas debería coincidir con la lista asignada");
    }

    @Test
    void testSetPreguntas() {
        List<PreguntaA> nuevasPreguntas = new ArrayList<>();
        nuevasPreguntas.add(new PreguntaA("¿Recomendaría este curso a otros?"));
        encuesta.setPreguntas(nuevasPreguntas);
        assertEquals(nuevasPreguntas, encuesta.getPreguntas(), "La lista de preguntas debería ser la asignada");
    }
}
