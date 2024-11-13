package uniandes.dpoo.cursos.tests.actividades;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.actividades.Recurso;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


class RecursoTest {

    private Recurso recurso;
    private static final String TITULO = "Video Tutorial";
    private static final String DESCRIPCION = "Tutorial de introducción";
    private static final int DURACION = 45;
    private static final String NIVEL_DIFICULTAD = "Media";
    private static final List<String> ACTIVIDADES_PREVIAS = new ArrayList<>();
    private static final LocalDateTime FECHA_LIMITE = LocalDateTime.now().plusDays(5);
    private static final boolean OBLIGATORIA = true;
    private static final String CREADOR = "Instructor";
    private static final String TIPO_RECURSO = "Video";
    private static final String URL_RECURSO = "https://example.com/video-tutorial";

    @BeforeEach
    void setUp() {
        recurso = new Recurso(TITULO, DESCRIPCION, DURACION, NIVEL_DIFICULTAD, ACTIVIDADES_PREVIAS, FECHA_LIMITE, OBLIGATORIA, CREADOR, TIPO_RECURSO, URL_RECURSO);
    }

    @Test
    void testConstructor() {
        assertEquals(TITULO, recurso.getTitulo(), "El título del recurso debería coincidir con el valor asignado");
        assertEquals(DESCRIPCION, recurso.getDescripcion(), "La descripción debería coincidir con el valor asignado");
        assertEquals(DURACION, recurso.getDuracion(), "La duración debería coincidir con el valor asignado");
        assertEquals(NIVEL_DIFICULTAD, recurso.getNivelDificultad(), "El nivel de dificultad debería coincidir con el valor asignado");
        assertEquals(ACTIVIDADES_PREVIAS, recurso.getActividadesPrevias(), "Las actividades previas deberían coincidir con el valor asignado");
        assertEquals(FECHA_LIMITE, recurso.getFechaLimite(), "La fecha límite debería coincidir con el valor asignado");
        assertEquals(OBLIGATORIA, recurso.isObligatoria(), "El valor de obligatoriedad debería coincidir con el valor asignado");
        assertEquals(CREADOR, recurso.getCreador(), "El creador debería coincidir con el valor asignado");
      
    }

    @Test
    void testVerRecurso() throws Exception {
        // Accedemos a `verRecurso` mediante reflexión, ya que es un método privado
        var metodoVerRecurso = Recurso.class.getDeclaredMethod("verRecurso");
        metodoVerRecurso.setAccessible(true);
        
        @SuppressWarnings("unchecked")
        List<String> resultado = (List<String>) metodoVerRecurso.invoke(recurso);
        
        assertEquals(2, resultado.size(), "La lista de recurso debería tener dos elementos");
        assertEquals(TIPO_RECURSO, resultado.get(0), "El primer elemento de la lista debería ser el tipo de recurso");
        assertEquals(URL_RECURSO, resultado.get(1), "El segundo elemento de la lista debería ser la URL del recurso");
    }
}