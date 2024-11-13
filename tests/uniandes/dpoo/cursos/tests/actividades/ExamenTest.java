package uniandes.dpoo.cursos.tests.actividades;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import uniandes.dpoo.actividades.Examen;
import uniandes.dpoo.actividades.preguntas.PreguntaA;

class ExamenTest {

    private Examen examen;
    private static final String TITULO = "Examen Final";
    private static final String DESCRIPCION = "Examen final del curso";
    private static final int DURACION = 120;
    private static final String NIVEL_DIFICULTAD = "Alta";
    private static final List<String> ACTIVIDADES_PREVIAS = new ArrayList<>();
    private static final LocalDateTime FECHA_LIMITE = LocalDateTime.now().plusDays(2);
    private static final boolean OBLIGATORIA = true;
    private static final String CREADOR = "Profesor";

    @BeforeEach
    void setUp() {
        examen = new Examen(TITULO, DESCRIPCION, DURACION, NIVEL_DIFICULTAD, ACTIVIDADES_PREVIAS, FECHA_LIMITE, OBLIGATORIA, CREADOR);
        examen.setPreguntas(new ArrayList<>());
    }

    @Test
    void testAgregarPregunta() {
        examen.agregarPregunta("¿Cuál es la capital de Japón?");
        assertEquals(1, examen.getPreguntas().size(), "La pregunta debería haberse agregado al examen");
    }

    @Test
    void testIngresarRespuesta() {
        examen.agregarPregunta("¿Cuál es la capital de Japón?");
        examen.ingresarRespuesta("Tokio", 0);
        PreguntaA pregunta = examen.getPreguntas().get(0);
        assertEquals("Tokio", pregunta.getRespuesta(), "La respuesta debería haberse registrado correctamente");
    }

    @Test
    void testGetPreguntas() {
        List<PreguntaA> preguntas = new ArrayList<>();
        preguntas.add(new PreguntaA("¿Cuál es la capital de Canadá?"));
        examen.setPreguntas(preguntas);
        assertEquals(preguntas, examen.getPreguntas(), "La lista de preguntas debería coincidir con la lista asignada");
    }

    @Test
    void testSetPreguntas() {
        List<PreguntaA> nuevasPreguntas = new ArrayList<>();
        nuevasPreguntas.add(new PreguntaA("¿Qué es el ADN?"));
        examen.setPreguntas(nuevasPreguntas);
        assertEquals(nuevasPreguntas, examen.getPreguntas(), "La lista de preguntas debería ser la asignada");
    }
}
