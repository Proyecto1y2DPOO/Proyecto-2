import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import uniandes.dpoo.exceptions.*;
import uniandes.dpoo.plataforma.Plataforma;


public class PlataformaTest {
    private Plataforma plataforma;

    @BeforeEach
    public void setUp() {
        plataforma = new Plataforma();
    }

    @Test
    public void testRegistrarEstudiante() throws Exception {
        plataforma.registrar("estudiante1", "password123", false);
        assertTrue(plataforma.existeUsuario("estudiante1"));
    }

    @Test
    public void testRegistrarProfesor() throws Exception {
        plataforma.registrar("profesor1", "password123", true);
        assertTrue(plataforma.existeUsuario("profesor1"));
        assertTrue(plataforma.isProfe("profesor1"));
    }

    @Test
    public void testRegistrarUsuarioExistente() throws Exception {
        try {
            plataforma.registrar("estudiante1", "password123", false);
            plataforma.registrar("estudiante1", "password123", false); // Duplicado
            fail("Se esperaba una excepción de usuario existente");
        } catch (ExisteUsuarioException e) {
            assertEquals("Ya existe este usuario", e.getMessage());
        }
    }

    @Test
    public void testIniciarSesionCorrecto() throws Exception {
        plataforma.registrar("estudiante1", "password123", false);
        plataforma.iniciarSesion("estudiante1", "password123");
        assertEquals("estudiante1", plataforma.getUsuario());
    }

    @Test
    public void testIniciarSesionIncorrecto() throws Exception {
        try {
            plataforma.registrar("estudiante1", "password123", false);
            plataforma.iniciarSesion("estudiante1", "wrongpassword");
            fail("Se esperaba una excepción de contraseña incorrecta");
        } catch (ContraseñaIncorrectaException e) {
            assertTrue(e instanceof ContraseñaIncorrectaException);
        }
    }

    @Test
    public void testCrearLearningPathComoEstudiante() throws Exception {
        try {
            plataforma.registrar("estudiante1", "password123", false);
            plataforma.iniciarSesion("estudiante1", "password123");
            plataforma.crearLearningPath("Curso1", "Contenido del curso", "Objetivos del curso", "Intermedio", 120, "profesor1");
            fail("Se esperaba una excepción ya que un estudiante no puede crear un Learning Path");
        } catch (noEsProfeException e) {
            assertTrue(e instanceof noEsProfeException);
        }
    }

    @Test
    public void testCrearLearningPathComoProfesor() throws Exception {
        plataforma.registrar("profesor1", "password123", true);
        plataforma.iniciarSesion("profesor1", "password123");
        plataforma.crearLearningPath("Curso1", "Contenido del curso", "Objetivos del curso", "Intermedio", 120, "profesor1");
        // Suponemos que los LearningPaths se almacenan en un mapa
        assertTrue(plataforma.existeLP("Curso1"));
    }

    @Test
    public void testInscribirLearningPathComoEstudiante() throws Exception {
        plataforma.registrar("profesor1", "password123", true);
        plataforma.registrar("estudiante1", "password123", false);
        plataforma.iniciarSesion("profesor1", "password123");
        plataforma.crearLearningPath("Curso1", "Contenido del curso", "Objetivos del curso", "Intermedio", 120, "profesor1");
        plataforma.iniciarSesion("estudiante1", "password123");
        plataforma.inscribirLearningPath("Curso1");
        assertEquals(1, plataforma.verProgreso("Curso1", "estudiante1"));
    }

    @Test
    public void testMostrarActividades() throws Exception {
        plataforma.registrar("profesor1", "password123", true);
        plataforma.iniciarSesion("profesor1", "password123");
        plataforma.crearLearningPath("Curso1", "Contenido", "Objetivos", "Avanzado", 90, "profesor1");
        var actividades = plataforma.mostrarActividades("Curso1");
        assertNotNull(actividades);
    }

    @Test
    public void testCompletarActividadComoEstudiante() throws Exception {
        plataforma.registrar("profesor1", "password123", true);
        plataforma.registrar("estudiante1", "password123", false);
        plataforma.iniciarSesion("profesor1", "password123");
        plataforma.crearLearningPath("Curso1", "Contenido", "Objetivos", "Básico", 60, "profesor1");
        plataforma.iniciarSesion("estudiante1", "password123");
        plataforma.inscribirLearningPath("Curso1");
        // Suponiendo que "Actividad1" es una actividad válida en el Learning Path
        plataforma.marcaCompletarActividad("Curso1", "Actividad1");
        assertEquals("completa", plataforma.mostrarActividad("Curso1", "Actividad1").getEstado());
    }
    
    @Test
    public void testGettersYSettersUsuario() {
    	plataforma.setUsuario("l.guiza");
    	assertEquals("l.guiza", plataforma.getUsuario());
    	plataforma.setEsProfe(true);
    	assertEquals(true, plataforma.getEsProfe());
    	
    }
    
    

}
