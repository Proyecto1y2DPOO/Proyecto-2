package uniandes.dpoo.cursos.text;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.actividades.*;
import uniandes.dpoo.exceptions.*;
import uniandes.dpoo.learningpath.LearningPath;
import uniandes.dpoo.actividades.Tarea;


import java.time.LocalDateTime;
import java.util.ArrayList;

public class LearningPathTest {

    private LearningPath learningPath;
    private String studentLogin;
    private LocalDateTime startDate;

    @BeforeEach
    public void setUp() {
        String[] objectives = {"Learn Java", "Understand OOP"};
        learningPath = new LearningPath("Java Basics", "A course for beginners", objectives, "Beginner", 120, LocalDateTime.now(), "Instructor");
        studentLogin = "student1";
        startDate = LocalDateTime.now();
    }

    @Test
    public void testAgregarEstudiante() throws Exception {
        learningPath.agregarEstudiante(studentLogin, startDate);
        assertTrue(learningPath.getEstudiantes().contains(studentLogin));
        assertNotNull(learningPath.getFechaInicio().get(studentLogin), "Student's start date should be recorded.");
    }

    @Test
    public void testAgregarEstudianteAlreadyExists() {
        assertThrows(ExisteUsuarioException.class, () -> {
            learningPath.agregarEstudiante(studentLogin, startDate);
            learningPath.agregarEstudiante(studentLogin, startDate);
        }, "Should throw exception if student is already added.");
    }

    @Test
    public void testAgregarActividad() {
        Tarea tarea = new Tarea("Tarea 1", "Simple task", 30, "Beginner", new ArrayList<>(), LocalDateTime.now(), true, "Instructor");
        learningPath.agregarActividad(tarea);
        assertTrue(learningPath.getActividades().containsKey("Tarea 1"), "Activity should be added to the learning path.");
    }

    @Test
    public void testIniciarActividad() throws Exception {
        Tarea tarea = new Tarea("Tarea 1", "Simple task", 30, "Beginner", new ArrayList<>(), LocalDateTime.now(), true, "Instructor");
        learningPath.agregarActividad(tarea);
        learningPath.agregarEstudiante(studentLogin, startDate);
        
        Actividad actividad = learningPath.iniciarActividad("Tarea 1", studentLogin);
        assertEquals("Tarea 1", actividad.getTitulo(), "Started activity should be 'Tarea 1'.");
        assertEquals("Tarea 1", learningPath.getFechaInicio().get(studentLogin).toString(), "Student's current activity should be 'Tarea 1'.");
    }

    @Test
    public void testIniciarActividadOtraActiva() throws Exception {
        Tarea tarea1 = new Tarea("Tarea 1", "First task", 30, "Beginner", new ArrayList<>(), LocalDateTime.now(), true, "Instructor");
        Tarea tarea2 = new Tarea("Tarea 2", "Second task", 30, "Beginner", new ArrayList<>(), LocalDateTime.now(), true, "Instructor");
        learningPath.agregarActividad(tarea1);
        learningPath.agregarActividad(tarea2);
        learningPath.agregarEstudiante(studentLogin, startDate);
        
        learningPath.iniciarActividad("Tarea 1", studentLogin);
        assertThrows(YaTieneOtraActException.class, () -> {
            learningPath.iniciarActividad("Tarea 2", studentLogin);
        }, "Should throw exception if another activity is already active.");
    }

    @Test
    public void testCompletarActividad() throws Exception {
        Tarea tarea = new Tarea("Tarea 1", "Complete this task", 30, "Beginner", new ArrayList<>(), LocalDateTime.now(), true, "Instructor");
        learningPath.agregarActividad(tarea);
        learningPath.agregarEstudiante(studentLogin, startDate);
        learningPath.iniciarActividad("Tarea 1", studentLogin);

        learningPath.completarActividad(studentLogin, "Tarea 1", "exitosa");
        assertNull(learningPath.getFechaInicio().get(studentLogin), "No activity should be active for the student after completion.");
        assertEquals("exitosa", learningPath.getActividades().get("Tarea 1").getEstado(), "Activity should be marked as completed successfully.");
    }

    @Test
    public void testCalcularExitos() throws Exception {
        Tarea tarea = new Tarea("Tarea 1", "Complete this task", 30, "Beginner", new ArrayList<>(), LocalDateTime.now(), true, "Instructor");
        learningPath.agregarActividad(tarea);
        learningPath.agregarEstudiante(studentLogin, startDate);
        learningPath.iniciarActividad("Tarea 1", studentLogin);
        learningPath.completarActividad(studentLogin, "Tarea 1", "exitosa");

        learningPath.calcularEXitos(studentLogin);
        assertEquals(100, learningPath.verExitos(studentLogin), "Success rate should be 100% after completing one successful activity.");
    }

    @Test
    public void testCalcularFracasos() throws Exception {
        Tarea tarea = new Tarea("Tarea 1", "Complete this task", 30, "Beginner", new ArrayList<>(), LocalDateTime.now(), true, "Instructor");
        learningPath.agregarActividad(tarea);
        learningPath.agregarEstudiante(studentLogin, startDate);
        learningPath.iniciarActividad("Tarea 1", studentLogin);
        learningPath.completarActividad(studentLogin, "Tarea 1", "no exitosa");

        learningPath.calcularFracsos(studentLogin);
        assertEquals(100, learningPath.verFracasos(studentLogin), "Failure rate should be 100% after completing one unsuccessful activity.");
    }

    @Test
    public void testAgregarReseña() {
        String review = "Great course!";
        learningPath.agregarReseña(review);
        assertTrue(learningPath.getReseña().contains(review), "Review should be added to the course.");
    }

    @Test
    public void testCalcularRating() {
        learningPath.agregarRating(4.5);
        learningPath.agregarRating(5.0);
        assertEquals(4.75, learningPath.getRating(), 0.01, "The average rating should be 4.75 after two ratings of 4.5 and 5.0.");
    }
}
