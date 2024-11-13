package uniandes.dpoo.cursos.tests.Usuario;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.usuario.Professor;

class ProfessorTest {

    private Professor professor;
    private static final String LOGIN = "professor123";
    private static final String PASSWORD = "password123";
    private static final String TITULO_LEARNING_PATH = "Java Basics";
    private static final String TITULO_ACTIVIDAD = "Quiz 1";
    private static final LocalDateTime FECHA_CREACION = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        professor = new Professor(LOGIN, PASSWORD);
    }

    @Test
    void testConstructor() {
        assertNotNull(professor.getLearningPathCreados(), "El mapa learningPathCreados debería estar inicializado");
        assertNotNull(professor.getActividadesCreados(), "El mapa ActividadesCreados debería estar inicializado");
    }

    @Test
    void testAgregarLearningPathCreeada() {
        professor.agregarLearningPathCreeada(TITULO_LEARNING_PATH, FECHA_CREACION);
        assertTrue(professor.getLearningPathCreados().containsKey(TITULO_LEARNING_PATH), "El learning path debería haberse agregado al mapa");
        assertEquals(FECHA_CREACION, professor.getLearningPathCreados().get(TITULO_LEARNING_PATH), "La fecha de creación debería coincidir con el valor asignado");
    }

    @Test
    void testAgregarActividadCreada() {
        professor.agregarActividadCreada(TITULO_ACTIVIDAD, FECHA_CREACION);
        assertTrue(professor.getLearningPathCreados().containsKey(TITULO_ACTIVIDAD), "La actividad debería haberse agregado al mapa");
        assertEquals(FECHA_CREACION, professor.getLearningPathCreados().get(TITULO_ACTIVIDAD), "La fecha de creación debería coincidir con el valor asignado");
    }

    @Test
    void testSettersAndGetters() {
        Map<String, LocalDateTime> nuevoLearningPath = new HashMap<>();
        Map<String, LocalDateTime> nuevaActividad = new HashMap<>();
        nuevoLearningPath.put("Advanced Java", FECHA_CREACION);
        nuevaActividad.put("Examen Final", FECHA_CREACION);
        
        professor.setLearningPathCreados(nuevoLearningPath);
        professor.setActividadesCreados(nuevaActividad);
        
        assertEquals(nuevoLearningPath, professor.getLearningPathCreados(), "El mapa learningPathCreados debería coincidir con el valor asignado");
        assertEquals(nuevaActividad, professor.getActividadesCreados(), "El mapa ActividadesCreados debería coincidir con el valor asignado");
    }
}