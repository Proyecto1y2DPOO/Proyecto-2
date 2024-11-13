package uniandes.dpoo.cursos.tests.Usuario;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import uniandes.dpoo.usuario.Usuario;
import java.util.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;




class UsuarioTest {

    private Usuario usuario;
    private static final String LOGIN = "user123";
    private static final String CONTRASENA = "password123";
    private static final String NAME = "John Doe";
    private static final Date DATE_OF_BIRTH = Date(1990, Month.JANUARY, 1);
    private static final String DIRECCION = "123 Main St";
    private static final int CEDULA = 123456789;
    private static final String NIVEL_EDUCA = "Bachelor's Degree";

    @BeforeEach
    void setUp() {
        usuario = new Usuario(LOGIN, CONTRASENA);
    }

    @Test
    void testConstructor() {
        assertEquals(LOGIN, usuario.getLogin(), "El login debería ser correcto");
        assertEquals(CONTRASENA, usuario.getContraseña(), "La contraseña debería ser correcta");
    }

    @Test
    void testInformacionUsuario() {
        usuario.informetionUsuario(NAME, DATE_OF_BIRTH, DIRECCION, CEDULA, NIVEL_EDUCA);
        
        assertEquals(NAME, usuario.getName(), "El nombre debería ser correcto");
        assertEquals(DATE_OF_BIRTH, usuario.getDateOfBirth(), "La fecha de nacimiento debería ser correcta");
        assertEquals(DIRECCION, usuario.getDireccion(), "La dirección debería ser correcta");
        assertEquals(CEDULA, usuario.getCedula(), "La cédula debería ser correcta");
        assertEquals(NIVEL_EDUCA, usuario.getNivelDeEduca(), "El nivel educativo debería ser correcto");
    }

    @Test
    void testGettersAndSetters() {
        // Modificar los valores usando los setters
        String nuevoNombre = "Jane Doe";
        Date nuevaFechaNacimiento = new Date(1995, 5, 15);  // Date constructor deprecated
        String nuevaDireccion = "456 Elm St";
        int nuevaCedula = 987654321;
        String nuevoNivelEduca = "Master's Degree";
        
        usuario.setName(nuevoNombre);
        usuario.setDateOfBirth(nuevaFechaNacimiento);
        usuario.setDireccion(nuevaDireccion);
        usuario.setCedula(nuevaCedula);
        usuario.setNivelDeEduca(nuevoNivelEduca);

        // Verificar que los valores hayan sido correctamente modificados
        assertEquals(nuevoNombre, usuario.getName(), "El nombre debería haberse actualizado");
        assertEquals(nuevaFechaNacimiento, usuario.getDateOfBirth(), "La fecha de nacimiento debería haberse actualizado");
        assertEquals(nuevaDireccion, usuario.getDireccion(), "La dirección debería haberse actualizado");
        assertEquals(nuevaCedula, usuario.getCedula(), "La cédula debería haberse actualizado");
        assertEquals(nuevoNivelEduca, usuario.getNivelDeEduca(), "El nivel educativo debería haberse actualizado");
    }
}
