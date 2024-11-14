package uniandes.dpoo.cursos.tests.actividades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import uniandes.dpoo.actividades.Actividad;


public class ActividadTest {
	private Actividad actividad;
    private LocalDateTime fechaLimite;

    @BeforeEach
    public void setUp() {
        fechaLimite = LocalDateTime.of(2024, 12, 31, 23, 59); 
        actividad = new Actividad(
                "Estudio de Java",
                "Estudiar conceptos de Java y realizar prácticas.",
                120, 
                "Intermedio",
                Arrays.asList("Repasar conceptos previos"),
                fechaLimite,
                true,
                "Profesor A",
                "Aprender Java"
        );
    }

    @Test
    public void testConstructorYGetters() {
        assertEquals("Estudio de Java", actividad.getTitulo());
        assertEquals("Estudiar conceptos de Java y realizar prácticas.", actividad.getDescripcion());
        assertEquals(120, actividad.getDuracion());
        assertEquals("Intermedio", actividad.getNivelDificultad());
        assertTrue(actividad.isObligatoria());
        assertEquals("Profesor A", actividad.getCreador());
        assertEquals("Aprender Java", actividad.getObjetivo());
        assertEquals(fechaLimite, actividad.getFechaLimite());
        assertEquals(0, actividad.getTiempoDedicado());
        assertEquals(0.0, actividad.getRating());
    }

    @Test
    public void testAgregarResena() {
        actividad.agregarReseña("Muy buena actividad.");
        assertEquals(1, actividad.getReseña().size());
        assertEquals("Muy buena actividad.", actividad.getReseña().get(0));
    }

    @Test
    public void testAgregarRecomendacion() {
        actividad.agregarRecomendacion("Recomendada para todos los estudiantes.");
        assertEquals(1, actividad.getRecomendacion().size());
        assertEquals("Recomendada para todos los estudiantes.", actividad.getRecomendacion().get(0));
    }

    @Test
    public void testAgregarRating() {
        actividad.agregarRating(4.0);
        assertEquals(1, actividad.getRatings().size());
        assertEquals(4.0, actividad.getRatings().get(0));
    }

    @Test
    public void testCalcularRating() {
        actividad.agregarRating(4.0);
        actividad.agregarRating(5.0);
        actividad.agregarRating(3.0);
        
        actividad.calcularRating();
        assertEquals(4.0, actividad.getRating()); 
    }

    @Test
    public void testCalificar() {
        actividad.calificar(4.5);
        assertEquals(4.5, actividad.getNota());
    }

    @Test
    public void testCambiarEstado() {
        actividad.cambiarEstado("Completada");
        assertEquals("Completada", actividad.getEstado());
    }

    @Test
    public void testActividadesPrevias() {
        actividad.setActividadesPrevias(Arrays.asList("Repasar conceptos de programación"));
        assertEquals(1, actividad.getActividadesPrevias().size());
        assertEquals("Repasar conceptos de programación", actividad.getActividadesPrevias().get(0));
    }
    
    @Test
    public void testConstructorConParametros() {
        Actividad nuevaActividad = new Actividad(
                "Practicar JUnit",
                "Realizar ejercicios prácticos de JUnit.",
                90,
                "Avanzado",
                Arrays.asList("Repasar pruebas unitarias"),
                LocalDateTime.of(2024, 11, 30, 18, 0),
                false,
                "Profesor B",
                "Mejorar habilidades en pruebas unitarias"
        );
        
        assertEquals("Practicar JUnit", nuevaActividad.getTitulo());
        assertEquals("Realizar ejercicios prácticos de JUnit.", nuevaActividad.getDescripcion());
        assertEquals(90, nuevaActividad.getDuracion());
        assertEquals("Avanzado", nuevaActividad.getNivelDificultad());
        assertFalse(nuevaActividad.isObligatoria());
        assertEquals("Profesor B", nuevaActividad.getCreador());
        assertEquals("Mejorar habilidades en pruebas unitarias", nuevaActividad.getObjetivo());
    }
    @Test
    void testOtr() {
    	actividad.setTitulo("Act1");
    	assertEquals("Act1", actividad.getTitulo());
    	actividad.setDescripcion("Act1 consiste en x y y z");
    	actividad.setDuracion(120);
    	actividad.setNivelDificultad("Alto");
    	actividad.setFechaLimite(LocalDateTime.of(2024, 11, 10, 10, 10, 10));
    	actividad.setObligatoria(true);
    	actividad.setCreador("Laura");
    	actividad.setTiempoDedicado(125);
    	actividad.setRating(4.5);
    	actividad.setEstado("Completado");
    	actividad.setObjetivo("Completar conocimientos");
    }

}