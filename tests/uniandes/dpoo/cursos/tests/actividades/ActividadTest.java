package uniandes.dpoo.cursos.tests.actividades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import uniandes.dpoo.actividades.Actividad;


public class ActividadTest {

    private Actividad actividad;
    private LocalDateTime fechaLimite;

    @BeforeEach
    void setUp() {
        fechaLimite = LocalDateTime.now().plusDays(5);
        List<String> actividadesPrevias = new ArrayList<>();
        actividadesPrevias.add("Actividad Previa 1");

        actividad = new Actividad("Titulo de Prueba", "Descripcion de Prueba", 60, "Intermedio", actividadesPrevias, fechaLimite, true, "Creador");
    }

    @Test
    void testAgregarReseña() {
        actividad.agregarReseña("Muy buena actividad");
        actividad.agregarReseña("Excelente contenido");
        
        assertEquals(2, actividad.getRecomendacion().size());
        assertEquals("Muy buena actividad", actividad.getReseña().get(0));
        assertEquals("Excelente contenido", actividad.getReseña().get(1));
    }

    @Test
    void testAgregarRecomendacion() {
        actividad.agregarRecomendacion("Recomiendo que cambien x cosa");
        actividad.agregarRecomendacion("Recomiendo que lo mantengan");
        
        assertEquals(2, actividad.getRecomendacion().size());
        assertEquals("Recomiendo que cambien x cosa", actividad.getRecomendacion().get(0));
        assertEquals("Recomiendo que lo mantengan", actividad.getRecomendacion().get(1));
    }
    @Test
    void testAgregarRating() {
        actividad.agregarRating(4.9);
        //System.out.println(actividad.getRating());
        assertEquals(4.9, actividad.getRating());
    }
    @Test
    void testCalificar() {
        actividad.calificar(8.5);
        assertEquals(8.5, actividad.getNota());
    }

    @Test
    void testCambiarEstado() {
        actividad.cambiarEstado("En Progreso");
        assertEquals("En Progreso", actividad.getEstado());

        actividad.cambiarEstado("Completada");
        assertEquals("Completada", actividad.getEstado());
    }

    @Test
    void testGettersAndSetters() {
        actividad.setTitulo("Nuevo Titulo");
        assertEquals("Nuevo Titulo", actividad.getTitulo());

        actividad.setDescripcion("Nueva Descripcion");
        assertEquals("Nueva Descripcion", actividad.getDescripcion());

        actividad.setDuracion(90);
        assertEquals(90, actividad.getDuracion());

        actividad.setNivelDificultad("Avanzado");
        assertEquals("Avanzado", actividad.getNivelDificultad());

        LocalDateTime nuevaFechaLimite = LocalDateTime.now().plusDays(10);
        actividad.setFechaLimite(nuevaFechaLimite);
        assertEquals(nuevaFechaLimite, actividad.getFechaLimite());
    }

    @Test
    void testTiempoDedicado() {
        actividad.setTiempoDedicado(120);
        assertEquals(120, actividad.getTiempoDedicado());
    }

    @Test
    void testObligatoria() {
        assertTrue(actividad.isObligatoria());
        actividad.setObligatoria(false);
        assertFalse(actividad.isObligatoria());
    }

    @Test
    void testCreador() {
        assertEquals("Creador", actividad.getCreador());
        actividad.setCreador("Nuevo Creador");
        assertEquals("Nuevo Creador", actividad.getCreador());
    }

}