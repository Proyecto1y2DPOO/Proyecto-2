import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.actividades.Actividad;
import uniandes.dpoo.actividades.Quiz;
import uniandes.dpoo.actividades.Tarea;
import uniandes.dpoo.exceptions.ExisteUsuarioException;
import uniandes.dpoo.exceptions.NoExisteActividadException;
import uniandes.dpoo.learningpath.LearningPath;

class LearningPathTest {

	private LearningPath learningPath;
    private LocalDateTime fechaCreacion;

    @BeforeEach
    public void setUp() {
        fechaCreacion = LocalDateTime.now();
        String[] objetivos = {"Aprender Java", "Dominar JUnit"};
        learningPath = new LearningPath("Curso Java", "Curso básico de Java", objetivos, "Intermedio", 120, fechaCreacion, "Juan Perez");
    }

    @Test
    public void testAgregarEstudiante() throws Exception {
        // Agregar un estudiante
        String loginStu = "estudiante1";
        LocalDateTime fechaIngreso = LocalDateTime.now();
        learningPath.agregarEstudiante(loginStu, fechaIngreso);

        // Verificar que el estudiante se agregó correctamente
        assertTrue(learningPath.existeEstudiante(loginStu), "El estudiante no fue agregado correctamente");
    }

    @Test
    public void testAgregarEstudianteExistente() {
        try {
            // Agregar el mismo estudiante dos veces
            String loginStu = "estudiante1";
            LocalDateTime fechaIngreso = LocalDateTime.now();
            learningPath.agregarEstudiante(loginStu, fechaIngreso);
            learningPath.agregarEstudiante(loginStu, fechaIngreso);  // Debe lanzar la excepción
            fail("Se esperaba una excepción ExisteUsuarioException");
        } catch (Exception e) {
            assertTrue(e instanceof ExisteUsuarioException, "Se esperaba una excepción ExisteUsuarioException");
        }
    }

    @Test
    public void testAgregarActividad() {
        // Crear y agregar una actividad
        String titulo = "Tarea 1";
        Tarea tarea = new Tarea(titulo, "Descripción tarea", 60, "Bajo", Arrays.asList(), LocalDateTime.now(), true, "Juan Perez");
        learningPath.agregarActividad(tarea);

        // Verificar que la actividad fue agregada correctamente
        assertNotNull(learningPath.getActividades().get(titulo), "La actividad no fue agregada correctamente");
    }

    @Test
    public void testIniciarActividad() throws Exception {
        // Agregar actividad
        String titulo = "Tarea 1";
        Tarea tarea = new Tarea(titulo, "Descripción tarea", 60, "Bajo", Arrays.asList(), LocalDateTime.now(), true, "Juan Perez");
        learningPath.agregarActividad(tarea);
        
        // Agregar estudiante
        String loginStu = "estudiante1";
        learningPath.agregarEstudiante(loginStu, LocalDateTime.now());

        // Iniciar actividad
        Actividad actividad = learningPath.iniciarActividad(titulo, loginStu);

        // Verificar que la actividad se ha iniciado correctamente
        assertNotNull(actividad, "La actividad no fue iniciada correctamente");
    }

    @Test
    public void testIniciarActividadEstudianteNoExistente() {
        try {
            // Intentar iniciar una actividad para un estudiante que no existe
            learningPath.iniciarActividad("Tarea 1", "estudianteNoExistente");
            fail("Se esperaba una excepción ExisteUsuarioException");
        } catch (Exception e) {
            assertTrue(e instanceof ExisteUsuarioException, "Se esperaba una excepción ExisteUsuarioException");
        }
    }

    @Test
    public void testCompletarActividadExitosa() throws Exception {
        // Crear y agregar una actividad
        String titulo = "Tarea 1";
        Tarea tarea = new Tarea(titulo, "Descripción tarea", 60, "Bajo", Arrays.asList(), LocalDateTime.now(), true, "Juan Perez");
        learningPath.agregarActividad(tarea);

        // Agregar estudiante
        String loginStu = "estudiante1";
        learningPath.agregarEstudiante(loginStu, LocalDateTime.now());

        // Iniciar actividad
        learningPath.iniciarActividad(titulo, loginStu);

        // Completar actividad
        learningPath.completarActividad(loginStu, "exitosa", LocalDateTime.now());

        // Verificar que el estado de la actividad es "exitosa"
        Actividad actividad = learningPath.mostrarActividadIniada(loginStu);
        assertEquals("exitosa", actividad.getEstado(), "La actividad no fue marcada como exitosa");
    }

    @Test
    public void testCompletarActividadNoExistente() {
        try {
            // Intentar completar una actividad que no existe
            learningPath.completarActividad("estudiante1", "Actividad Inexistente", LocalDateTime.now());
            fail("Se esperaba una excepción NoExisteActividadException");
        } catch (Exception e) {
            assertTrue(e instanceof NoExisteActividadException, "Se esperaba una excepción NoExisteActividadException");
        }
    }

    @Test
    public void testVerCalificacion() throws Exception {
        // Crear y agregar una actividad
        String titulo = "Quiz 1";
        Quiz quiz = new Quiz(titulo, "Descripción quiz", 60, "Bajo", Arrays.asList(), LocalDateTime.now(), true, "Juan Perez", 6.0);
        learningPath.agregarActividad(quiz);

        // Agregar estudiante
        String loginStu = "estudiante1";
        learningPath.agregarEstudiante(loginStu, LocalDateTime.now());

        // Calificar actividad
        learningPath.cambiarCalificacion(loginStu, titulo, 8.0);

        // Verificar calificación
        Double calificacion = learningPath.verCalificacionAct(loginStu, titulo);
        assertEquals(8.0, calificacion, "La calificación no fue correcta");
    }

    @Test
    public void testVerProgreso() throws Exception {
        // Agregar estudiante
        String loginStu = "estudiante1";
        learningPath.agregarEstudiante(loginStu, LocalDateTime.now());

        // Verificar progreso
        int progreso = learningPath.verProgreso(loginStu);
        assertEquals(0, progreso, "El progreso debería ser 0 al iniciar");
    }

    @Test
    public void testCalcularFinalizacion() throws Exception {
        // Agregar estudiante
        String loginStu = "estudiante1";
        learningPath.agregarEstudiante(loginStu, LocalDateTime.now());

        // Completar actividades
        learningPath.completarActividad(loginStu, "exitosa", LocalDateTime.now());

        // Verificar finalización
        Boolean finalizado = learningPath.calcularFinalizacion(loginStu, LocalDateTime.now());
        assertTrue(finalizado, "El estudiante debería haber completado todas las actividades");
    }

}
