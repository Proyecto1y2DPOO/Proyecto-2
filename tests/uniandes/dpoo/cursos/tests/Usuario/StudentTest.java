package uniandes.dpoo.cursos.tests.Usuario;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.usuario.Student;

class StudentTest {

	private Student student;
    private static final String LOGIN = "student123";
    private static final String PASSWORD = "password123";
    private static final String INTERES = "Programación";
    private static final String LOGRO = "Completar curso Java";
    private static final String TITULO_LP = "Java Basics";
    private static final int DURACION = 120;
    private static final boolean FINALIZADO = true;

    @BeforeEach
    void setUp() {
        student = new Student(LOGIN, PASSWORD);
    }

    @Test
    void testConstructor() {
        assertNotNull(student.getIntereses(), "La lista de intereses debería estar inicializada");
        assertNotNull(student.getLogros(), "La lista de logros debería estar inicializada");
        assertNotNull(student.getInscritos(), "El mapa de inscritos debería estar inicializado");
        assertNotNull(student.getRecomendaciones(), "La lista de recomendaciones debería estar inicializada");
        assertNotNull(student.getTiempo(), "El mapa de tiempo debería estar inicializado");
    }

    @Test
    void testAgregarInteres() {
        student.agregarInteres(INTERES);
        assertTrue(student.getIntereses().contains(INTERES), "El interés debería haberse agregado a la lista de intereses");
    }

    @Test
    void testAgregarLogro() {
        student.agregarLogro(LOGRO);
        assertTrue(student.getLogros().contains(LOGRO), "El logro debería haberse agregado a la lista de logros");
    }

    @Test
    void testAgregarLP() {
        student.agregarLP(TITULO_LP, FINALIZADO);
        assertTrue(student.getInscritos().containsKey(TITULO_LP), "El título de Learning Path debería estar en el mapa de inscritos");
        assertEquals(FINALIZADO, student.getInscritos().get(TITULO_LP), "El estado de finalización debería coincidir con el valor asignado");
    }

    @Test
    void testAgregarRecomendacion() {
        student.agregarRecomendacion(TITULO_LP);
        assertTrue(student.getRecomendaciones().contains(TITULO_LP), "La recomendación debería haberse agregado a la lista de recomendaciones");
    }

    @Test
    void testAgregarTiempo() {
        student.agregarTiempo(TITULO_LP, DURACION);
        assertTrue(student.getTiempo().containsKey(TITULO_LP), "El título de Learning Path debería estar en el mapa de tiempo");
        assertEquals(DURACION, student.getTiempo().get(TITULO_LP), "La duración debería coincidir con el valor asignado");
    }

    @Test
    void testSettersAndGetters() {
        List<String> nuevosIntereses = new ArrayList<>();
        List<String> nuevosLogros = new ArrayList<>();
        List<String> nuevasRecomendaciones = new ArrayList<>();
        Map<String, Boolean> nuevosInscritos = new HashMap<>();
        Map<String, Integer> nuevoTiempo = new HashMap<>();

        nuevosIntereses.add("Data Science");
        nuevosLogros.add("Completar curso de Python");
        nuevasRecomendaciones.add("Machine Learning");
        nuevosInscritos.put("Python Basics", false);
        nuevoTiempo.put("Python Basics", 100);

        student.setInterese(nuevosIntereses);
        student.setLogro(nuevosLogros);
        student.setInscritos(nuevosInscritos);
        student.setTiempo(nuevoTiempo);

        assertEquals(nuevosIntereses, student.getIntereses(), "La lista de intereses debería coincidir con el valor asignado");
        assertEquals(nuevosLogros, student.getLogros(), "La lista de logros debería coincidir con el valor asignado");
        assertEquals(nuevasRecomendaciones, student.getRecomendaciones(), "La lista de recomendaciones debería coincidir con el valor asignado");
        assertEquals(nuevosInscritos, student.getInscritos(), "El mapa de inscritos debería coincidir con el valor asignado");
        assertEquals(nuevoTiempo, student.getTiempo(), "El mapa de tiempo debería coincidir con el valor asignado");
    }

}
