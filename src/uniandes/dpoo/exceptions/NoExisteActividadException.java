package uniandes.dpoo.exceptions;

@SuppressWarnings("serial")
public class NoExisteActividadException extends Exception {

	private String NoExisteActividad;
	
	public NoExisteActividadException() {
		
		super();
		this.NoExisteActividad = "No existe la actividad a la cual intenta acceder o manipular";
	}
	
	@Override
	
	public String getMessage() {
	
		return NoExisteActividad;
	
	}
}
