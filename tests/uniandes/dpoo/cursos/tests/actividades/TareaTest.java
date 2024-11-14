package uniandes.dpoo.cursos.tests.actividades;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.actividades.Tarea;

class TareaTest {

    private Tarea tarea;
    private static final String TITULO = "Proyecto Final";
    private static final String DESCRIPCION = "Completar el proyecto final de la asignatura";
    private static final int DURACION = 120;
    private static final String NIVEL_DIFICULTAD = "Alta";
    private static final List<String> ACTIVIDADES_PREVIAS = new ArrayList<>();
    private static final LocalDateTime FECHA_LIMITE = LocalDateTime.now().plusDays(3);
    private static final boolean OBLIGATORIA = true;
    private static final String CREADOR = "Profesor";
    private static final String OBJETIVO = "aprender mucho";

    @BeforeEach
    void setUp() {
        tarea = new Tarea(TITULO, DESCRIPCION, DURACION, NIVEL_DIFICULTAD, ACTIVIDADES_PREVIAS, FECHA_LIMITE, OBLIGATORIA, CREADOR, OBJETIVO);
    }

    @Test
    void testConstructor() {
        assertEquals(TITULO, tarea.getTitulo(), "El título de la tarea debería coincidir con el valor asignado");
        assertEquals(DESCRIPCION, tarea.getDescripcion(), "La descripción de la tarea debería coincidir con el valor asignado");
        assertEquals(DURACION, tarea.getDuracion(), "La duración de la tarea debería coincidir con el valor asignado");
        assertEquals(NIVEL_DIFICULTAD, tarea.getNivelDificultad(), "El nivel de dificultad de la tarea debería coincidir con el valor asignado");
        assertEquals(ACTIVIDADES_PREVIAS, tarea.getActividadesPrevias(), "Las actividades previas deberían coincidir con el valor asignado");
        assertEquals(FECHA_LIMITE, tarea.getFechaLimite(), "La fecha límite de la tarea debería coincidir con el valor asignado");
        assertEquals(OBLIGATORIA, tarea.isObligatoria(), "El valor de obligatoriedad de la tarea debería coincidir con el valor asignado");
        assertEquals(CREADOR, tarea.getCreador(), "El creador de la tarea debería coincidir con el valor asignado");
    }
}