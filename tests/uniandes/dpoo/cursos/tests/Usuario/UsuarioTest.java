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
    
    @BeforeEach
    void setUp() {
        usuario = new Usuario(LOGIN, CONTRASENA);
        assertEquals(LOGIN, usuario.getLogin());
        assertEquals(CONTRASENA, usuario.getContraseña());
    }

    @Test
    public void testInformetionUsuario() {

        String name = "Laura Guiza";
        Date dateOfBirth = new Date(2000, 12, 10); 
        String direccion = "Calle 123";
        int cedula = 12345678;
        String nivelDeEduca = "Universitario";

        usuario.informetionUsuario(name, dateOfBirth, direccion, cedula, nivelDeEduca);

        assertEquals(name, usuario.getName());
        assertEquals(dateOfBirth, usuario.getDateOfBirth());
        assertEquals(direccion, usuario.getDireccion());
        assertEquals(cedula, usuario.getCedula());
        assertEquals(nivelDeEduca, usuario.getNivelDeEduca());
    }
    
    @Test
    public void testSetters() {
    	String name2 = "Juan Perez";
    	String login2 = "juanperez";
    	int cedula2 = 11345678;
    	String nivelDeEduca2 = "Escolar";
    	String contraseña2 = "Contraseña123";
    	String direccion2 = "Calle 321";
    	Date fecha2 = new Date(2000,12,10);
    	usuario.setNivelDeEduca(nivelDeEduca2);
    	usuario.setCedula(cedula2);
        usuario.setName(name2);
        usuario.setLogin(login2);
        usuario.setContraseña(contraseña2);
        usuario.setDireccion(direccion2);
        usuario.setDateOfBirth(fecha2);
        assertEquals(name2, usuario.getName());
        assertEquals(login2, usuario.getLogin());
        assertEquals(cedula2, usuario.getCedula());
        assertEquals(nivelDeEduca2, usuario.getNivelDeEduca());
        assertEquals(contraseña2, usuario.getContraseña());
        assertEquals(direccion2, usuario.getDireccion());
        assertEquals(fecha2, usuario.getDateOfBirth());
        
    }

}
