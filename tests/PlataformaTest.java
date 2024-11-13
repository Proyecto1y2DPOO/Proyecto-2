import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.plataforma.Plataforma;

class PlataformaTest {

    private Plataforma plataforma;

    @BeforeEach
    void setUp() {
        plataforma = new Plataforma();
    }

    // Test para registrar un estudiante
    @Test
    void testRegistrarEstudiante() throws Exception {
        plataforma.registrar("student1", "password123", false);  // false para estudiante
        assertTrue(plataforma.existeUsuario("student1"));
        assertFalse(plataforma.isProfe("student1"));
    }

    // Test para registrar un profesor
    @Test
    void testRegistrarProfesor() throws Exception {
        plataforma.registrar("professor1", "profpass", true);  // true para profesor
        assertTrue(plataforma.existeUsuario("professor1"));
        assertTrue(plataforma.isProfe("professor1"));
    }

    // Test para iniciar sesión con un usuario válido
    @Test
    void testIniciarSesionValido() throws Exception {
        plataforma.registrar("student1", "password123", false);
        plataforma.iniciarSesion("student1", "password123");
        assertEquals("student1", plataforma.usuario);
    }

    // Test para iniciar sesión con un usuario inválido
    @Test
    void testIniciarSesionInvalido() {
        assertThrows(ExisteUsuarioException.class, () -> {
            plataforma.iniciarSesion("nonexistentUser", "password");
        });
    }

    // Test para la creación de un Learning Path
    @Test
    void testCrearLearningPathProfesor() throws Exception {
        plataforma.registrar("professor1", "profpass", true);  // Registrar como profesor
        plataforma.iniciarSesion("professor1", "profpass");
        plataforma.crearLearningPath("Java Basics", "Learn the basics of Java.", "Understand syntax, classes, and methods", "Beginner", 120, "professor1");
        assertTrue(plataforma.existeLP("Java Basics"));
    }

    // Test para que un estudiante no pueda crear un Learning Path
    @Test
    void testCrearLearningPathEstudiante() throws Exception {
        plataforma.registrar("student1", "password123", false);  // Registrar como estudiante
        plataforma.iniciarSesion("student1", "password123");
        assertThrows(noEsProfeException.class, () -> {
            plataforma.crearLearningPath("Java Basics", "Learn the basics of Java.", "Understand syntax, classes, and methods", "Beginner", 120, "student1");
        });
    }

    // Test para verificar que un Learning Path no puede ser creado si ya existe
    @Test
    void testCrearLearningPathExistente() throws Exception {
        plataforma.registrar("professor1", "profpass", true);  // Registrar como profesor
        plataforma.iniciarSesion("professor1", "profpass");
        plataforma.crearLearningPath("Java Basics", "Learn the basics of Java.", "Understand syntax, classes, and methods", "Beginner", 120, "professor1");
        assertThrows(ExisteLearningPathException.class, () -> {
            plataforma.crearLearningPath("Java Basics", "Learn Java in depth.", "Learn advanced topics", "Intermediate", 150, "professor1");
        });
    }
}
