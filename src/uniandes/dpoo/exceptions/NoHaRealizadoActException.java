package uniandes.dpoo.exceptions;

@SuppressWarnings("serial")
public class NoHaRealizadoActException extends Exception {

	private String NoHaRealizado;

	public NoHaRealizadoActException() {
		// TODO Auto-generated constructor stub
		
		super();
		this.NoHaRealizado = "No se ha realizado la actividad";
	}

	
	@Override
	public String getMessage(){
	
		return NoHaRealizado;
	}

}