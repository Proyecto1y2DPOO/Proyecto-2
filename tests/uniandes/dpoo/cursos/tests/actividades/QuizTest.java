package uniandes.dpoo.cursos.tests.actividades;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import uniandes.dpoo.actividades.Quiz;
import uniandes.dpoo.actividades.preguntas.PreguntaM;

class QuizTest {

    private Quiz quiz;
    private static final String TITULO = "Quiz de prueba";
    private static final String DESCRIPCION = "Descripción de prueba";
    private static final int DURACION = 60;
    private static final String NIVEL_DIFICULTAD = "Media";
    private static final List<String> ACTIVIDADES_PREVIAS = new ArrayList<>();
    private static final LocalDateTime FECHA_LIMITE = LocalDateTime.now().plusDays(1);
    private static final boolean OBLIGATORIA = true;
    private static final String CREADOR = "Profesor";
    private static final Double CALIF_MIN = 3.0;
    private static final String OBJETIVO = "aprender mucho";

    @BeforeEach
    void setUp() {
        quiz = new Quiz(TITULO, DESCRIPCION, DURACION, NIVEL_DIFICULTAD, ACTIVIDADES_PREVIAS, FECHA_LIMITE, OBLIGATORIA, CREADOR, CALIF_MIN, OBJETIVO);
    }

    @Test
    void testAgregarPregunta() {
        quiz.agregarPregunta("¿Cuál es la capital de Francia?", "Madrid", "París", "Roma", "Berlín", 2, "París es la capital de Francia");
        assertEquals(1, quiz.getPreguntas().size(), "La pregunta debería haberse agregado");
    }

    @Test
    void testIngresarRespuestaCorrecta() {
        quiz.agregarPregunta("¿Cuál es la capital de Francia?", "Madrid", "París", "Roma", "Berlín", 2, "París es la capital de Francia");
        String respuesta = quiz.ingresarRespuesta(2, 0);
        assertEquals("Correcto", respuesta, "Debería devolver 'Correcto' para la respuesta correcta");
    }

    @Test
    void testIngresarRespuestaIncorrecta() {
        quiz.agregarPregunta("¿Cuál es la capital de Francia?", "Madrid", "París", "Roma", "Berlín", 2, "París es la capital de Francia");
        String respuesta = quiz.ingresarRespuesta(1, 0);
        assertEquals("Incorrecto", respuesta, "Debería devolver 'Incorrecto' para la respuesta incorrecta");
    }

    @Test
    void testCalcularCalificacionExitosa() {
        quiz.agregarPregunta("¿Cuál es la capital de Francia?", "Madrid", "París", "Roma", "Berlín", 2, "París es la capital de Francia");
        quiz.ingresarRespuesta(2, 0); // Respuesta correcta
        quiz.calcularCalificacion();
        assertEquals("exitosa", quiz.getEstado(), "El estado debería ser 'exitosa' si la calificación supera el mínimo");
        assertTrue(quiz.getNota() >= CALIF_MIN, "La nota debe ser mayor o igual a la calificación mínima para ser exitosa");
    }

    @Test
    void testCalcularCalificacionNoExitosa() {
        quiz.agregarPregunta("¿Cuál es la capital de Francia?", "Madrid", "París", "Roma", "Berlín", 2, "París es la capital de Francia");
        quiz.ingresarRespuesta(1, 0); // Respuesta incorrecta
        quiz.calcularCalificacion();
        assertEquals("no exitosa", quiz.getEstado(), "El estado debería ser 'no exitosa' si la calificación no alcanza el mínimo");
        assertTrue(quiz.getNota() < CALIF_MIN, "La nota debe ser menor a la calificación mínima para ser no exitosa");
    }

    @Test
    void testSetCalifMin() {
        Double nuevaCalifMin = 4.0;
        quiz.setCalifMin(nuevaCalifMin);
        assertEquals(nuevaCalifMin, quiz.getCalifMin(), "La calificación mínima debería ser igual a la nueva calificación asignada");
    }

    @Test
    void testSetPreguntas() {
        List<PreguntaM> nuevasPreguntas = new ArrayList<>();
        nuevasPreguntas.add(new PreguntaM("¿Cuál es la capital de Italia?", "Madrid", "París", "Roma", "Berlín", 3, "Roma es la capital de Italia"));
        quiz.setPreguntas(nuevasPreguntas);
        assertEquals(nuevasPreguntas, quiz.getPreguntas(), "La lista de preguntas debería ser la asignada");
    }
}
