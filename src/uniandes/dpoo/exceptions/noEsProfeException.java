package uniandes.dpoo.exceptions;


@SuppressWarnings("serial")
public class noEsProfeException extends Exception {

	private String noEsProfe;
	
	public noEsProfeException(String string) {
		// TODO Auto-generated constructor stub
	
	super();
	this.noEsProfe = string;
	
	}
	
	@Override
	
	public String getMessage() {
		
		return noEsProfe;
	}
	
}