package uniandes.dpoo.exceptions;

@SuppressWarnings("serial")
public class ActNoEsPreguntaException extends Exception {

	private String NoesPregunta;

	public ActNoEsPreguntaException() {
		// TODO Auto-generated constructor stub
		
		super();
		this.NoesPregunta = "No se puede agregar esa pregunta por que esa actividad no tiene preguntas";
	}

	
	@Override
	public String getMessage(){
	
		return NoesPregunta;
	}

}