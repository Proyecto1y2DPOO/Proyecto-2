package uniandes.dpoo.exceptions;

@SuppressWarnings("serial")
public class ContraseñaIncorrectaException extends Exception {

	private String incorrecta;
	
	public ContraseñaIncorrectaException() {
		
		super();
		this.incorrecta = "La contraseña ingresada es incorrecta";
	}
	
	@Override
	public String getMessage() {
		return incorrecta;
	}
}