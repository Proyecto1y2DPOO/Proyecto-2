package uniandes.dpoo.exceptions;


@SuppressWarnings("serial")
public class NoEsQuizException extends Exception {

	private String NoEsQuiz;
	
	public NoEsQuizException() {
		// TODO Auto-generated constructor stub
	
	super();
	this.NoEsQuiz = "La actividad a la que quiere verle la calificacion no es quiz";;
	
	}
	
	@Override
	
	public String getMessage() {
		
		return NoEsQuiz;
	}
	
}