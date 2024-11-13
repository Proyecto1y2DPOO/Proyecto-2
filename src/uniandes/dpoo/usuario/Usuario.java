package uniandes.dpoo.usuario;

import java.time.LocalDate;
import java.util.Date;

public class Usuario {
	
	private String login;
	private String contraseña;
	private String name;
	private Date dateOfBirth;
	private String direccion;
	private int cedula;
	private String nivelDeEduca;

	public Usuario(String login, String contraseña) {
		this.login = login;
		this.contraseña=contraseña;
	}

	public void informetionUsuario(String name, Date dateOfBirth2, 
            String direccion, int cedula, String nivelDeEduca) {
	
	     this.name = name;
	     this.dateOfBirth = dateOfBirth2;
	     this.direccion = direccion;
	     this.cedula = cedula;
	     this.nivelDeEduca = nivelDeEduca;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getCedula() {
		return cedula;
	}
	public void setCedula(int cedula) {
		this.cedula = cedula;
	}
	public String getNivelDeEduca() {
		return nivelDeEduca;
	}
	public void setNivelDeEduca(String nivelDeEduca) {
		this.nivelDeEduca = nivelDeEduca;
	}
}

