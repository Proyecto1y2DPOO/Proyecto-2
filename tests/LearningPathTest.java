
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.actividades.*;
import uniandes.dpoo.exceptions.*;
import uniandes.dpoo.learningpath.LearningPath;
import uniandes.dpoo.actividades.Tarea;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LearningPathTest {

	private LearningPath learningPath;
    private String studentLogin;
    private LocalDateTime currentTime;

    @BeforeEach
    public void setUp() {
        String[] objetivos = {"Objetivo 1", "Objetivo 2"};
        currentTime = LocalDateTime.now();
        learningPath = new LearningPath("Path 1", "Contenido de Path 1", objetivos, "Intermedio", 120, currentTime, "Profesor 1");

        studentLogin = "student1";
    }

    @Test
    public void testAgregarEstudiante() throws Exception {
        learningPath.agregarEstudiante(studentLogin, currentTime);
        assertTrue(learningPath.existeEstudiante(studentLogin), "El estudiante debería existir");
    }

    @Test
    public void testAgregarEstudianteExistente() {
        assertThrows(ExisteUsuarioException.class, () -> {
            learningPath.agregarEstudiante(studentLogin, currentTime);
            learningPath.agregarEstudiante(studentLogin, currentTime);
        }, "Debería lanzar una excepción si el estudiante ya está registrado");
    }

    @Test
    public void testAgregarActividad() {
        Actividad actividad = new Tarea("Tarea 1", "Descripción", 60, "Baja", Arrays.asList(), currentTime.plusDays(1), true, "Profesor 1", "Aprender X");
        learningPath.agregarActividad(actividad);
        assertTrue(learningPath.getActividades().containsKey(actividad.getTitulo()), "La actividad debería ser agregada");
    }

    @Test
    public void testIniciarActividad() throws Exception {
        Actividad actividad = new Tarea("Tarea 1", "Descripción", 60, "Baja", Arrays.asList(), currentTime.plusDays(1), true, "Profesor 1", "Aprender X");
        learningPath.agregarActividad(actividad);
        learningPath.agregarEstudiante(studentLogin, currentTime);
        Actividad actividadIniciada = learningPath.iniciarActividad(actividad.getTitulo(), studentLogin);

        assertEquals(actividad, actividadIniciada, "La actividad iniciada debería ser la misma que se pasó");
    }

    @Test
    public void testIniciarActividadYaIniciada() throws Exception {
        Actividad actividad = new Tarea("Tarea 1", "Descripción", 60, "Baja", Arrays.asList(), currentTime.plusDays(1), true, "Profesor 1", "Aprender X");
        learningPath.agregarActividad(actividad);
        learningPath.agregarEstudiante(studentLogin, currentTime);

        learningPath.iniciarActividad(actividad.getTitulo(), studentLogin);

        assertThrows(YaTieneOtraActException.class, () -> {
            learningPath.iniciarActividad(actividad.getTitulo(), studentLogin);
        }, "Debería lanzar una excepción si el estudiante ya tiene una actividad iniciada");
    }

    @Test
    public void testCompletarActividad() throws Exception {
        Actividad actividad = new Tarea("Tarea 1", "Descripción", 60, "Baja", Arrays.asList(), currentTime.plusDays(1), true, "Profesor 1", "Aprender X");
        learningPath.agregarActividad(actividad);
        learningPath.agregarEstudiante(studentLogin, currentTime);

        learningPath.iniciarActividad(actividad.getTitulo(), studentLogin);
        learningPath.completarActividad(studentLogin, "exitosa", currentTime.plusMinutes(60));

        Actividad actividadCompletada = learningPath.mostrarActividad(actividad.getTitulo(), studentLogin);

        assertEquals("exitosa", actividadCompletada.getEstado(), "El estado de la actividad debería ser 'exitosa'");
    }

    @Test
    public void testVerProgreso() throws Exception {
        Actividad actividad = new Tarea("Tarea 1", "Descripción", 60, "Baja", Arrays.asList(), currentTime.plusDays(1), true, "Profesor 1", "Aprender X");
        learningPath.agregarActividad(actividad);
        learningPath.agregarEstudiante(studentLogin, currentTime);

        learningPath.iniciarActividad(actividad.getTitulo(), studentLogin);
        learningPath.completarActividad(studentLogin, "exitosa", currentTime.plusMinutes(60));

        int progreso = learningPath.verProgreso(studentLogin);

        assertEquals(100, progreso, "El progreso del estudiante debería ser 100% después de completar la actividad");
    }

    @Test
    public void testVerExitos() throws Exception {
        Actividad actividad = new Tarea("Tarea 1", "Descripción", 60, "Baja", Arrays.asList(), currentTime.plusDays(1), true, "Profesor 1", "Aprender X");
        learningPath.agregarActividad(actividad);
        learningPath.agregarEstudiante(studentLogin, currentTime);

        learningPath.iniciarActividad(actividad.getTitulo(), studentLogin);
        learningPath.completarActividad(studentLogin, "exitosa", currentTime.plusMinutes(60));

        int exitos = learningPath.verExitos(studentLogin);

        assertEquals(100, exitos, "El porcentaje de éxitos debería ser 100%");
    }

    @Test
    public void testVerFracasos() throws Exception {
        Actividad actividad = new Tarea("Tarea 1", "Descripción", 60, "Baja", Arrays.asList(), currentTime.plusDays(1), true, "Profesor 1", "Aprender X");
        learningPath.agregarActividad(actividad);
        learningPath.agregarEstudiante(studentLogin, currentTime);

        learningPath.iniciarActividad(actividad.getTitulo(), studentLogin);
        learningPath.completarActividad(studentLogin, "no exitosa", currentTime.plusMinutes(60));

        int fracasos = learningPath.verFracasos(studentLogin);

        assertEquals(100, fracasos, "El porcentaje de fracasos debería ser 100%");
    }
    
    @Test
    public void testSetYGets() {
    	learningPath.setVersion(1);
    	assertEquals(1, learningPath.getVersion());
    	learningPath.setTitulo("Titulo1");
    	assertEquals("Titulo1", learningPath.getTitulo());
    	learningPath.setDesContenido("DescCo");
    	assertEquals("DescCo", learningPath.getDesContenido());
    	String[] objetivosEsperados = {
                "Aprender Java",
                "Dominar JUnit",
                "Practicar TDD"
            };
    	learningPath.setObjetivos(objetivosEsperados);
    	assertEquals(objetivosEsperados, learningPath.getObjetivos());
    	learningPath.setNivelDificultad("Alto");
    	assertEquals("Alto", learningPath.getNivelDificultad());
    	learningPath.setDuracionMin(60);
    	assertEquals(60, learningPath.getDuracionMin());
    	learningPath.setRating(4.5);
    	assertEquals(4.5, learningPath.getRating());
    	LocalDateTime fecha1= LocalDateTime.of(2024, 1, 13, 10, 15);
    	learningPath.setFechaCreacion(fecha1);
    	assertEquals(fecha1, learningPath.getFechaCreacion());
    	LocalDateTime fecha2= LocalDateTime.of(2024, 2, 13, 10, 15);
    	learningPath.setFechaModificacion(fecha2);
    	assertEquals(fecha2, learningPath.getFechaModificacion());
    	learningPath.setCreador("Laura");
    	assertEquals("Laura", learningPath.getCreador());
    	List<String> estudiantesEsperados = Arrays.asList("Estudiante1", "Estudiante2", "Estudiante3");
    	learningPath.setEstudiantes(estudiantesEsperados);
    	assertEquals(estudiantesEsperados, learningPath.getEstudiantes());
    	List<String> resenasEsperados = Arrays.asList("Bueno", "Malo", "Bueno, me gusto");
    	learningPath.setReseña(resenasEsperados);
    	assertEquals(resenasEsperados, learningPath.getReseña());
    	List<Double> ratingsEsperados = Arrays.asList(4.1, 2.0, 4.9);
    	learningPath.setRatings(ratingsEsperados);
    	assertEquals(ratingsEsperados, learningPath.getRatings());
    }
    
    
    
    
}
