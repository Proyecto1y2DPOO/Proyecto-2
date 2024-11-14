package uniandes.dpoo.exceptions;

@SuppressWarnings("serial")
public class ExisteUsuarioException extends Exception {

	private String yaExisteUsuario;
	
	public ExisteUsuarioException(String string) {
		// TODO Auto-generated constructor stub
	
	super();
	this.yaExisteUsuario = string;
	}
	
	public String getMessage() {
		
		return yaExisteUsuario;
	}
}