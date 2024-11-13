package uniandes.dpoo.cursos.tests.actividades;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


class FechaHoraActualTest {

    private LocalDateTime ahora;
    private LocalDateTime fechaFutura;
    private DateTimeFormatter formato;

    @BeforeEach
    void setUp() {
        ahora = LocalDateTime.now();
        fechaFutura = LocalDateTime.parse("2024-12-03T10:15:30");
        formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    @Test
    void testFechaActual() {
        LocalDate fechaHoy = LocalDate.now();
        assertNotNull(fechaHoy, "La fecha actual no debería ser nula");
    }

    @Test
    void testHoraActual() {
        assertNotNull(ahora, "La hora actual no debería ser nula");
    }

    @Test
    void testFormatoFechaHora() {
        String fechaHoraFormateada = ahora.format(formato);
        assertEquals(19, fechaHoraFormateada.length(), "La fecha formateada debería tener el formato 'yyyy-MM-dd HH:mm:ss'");
    }

    @Test
    void testComparacionFechas() {
        int resultado = ahora.compareTo(fechaFutura);
        assertTrue(resultado < 0, "La fecha actual debería ser anterior a la fecha futura");
    }

    @Test
    void testParseFecha() {
        LocalDateTime fechaParseada = LocalDateTime.parse("2024-12-03T10:15:30");
        assertEquals(fechaFutura, fechaParseada, "La fecha parseada debería ser igual a la fecha futura definida");
    }
}