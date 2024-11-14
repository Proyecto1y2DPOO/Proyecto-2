package uniandes.dpoo.exceptions;

@SuppressWarnings("serial")
public class ExisteLearningPathException extends Exception {

	private String yaExisteLearningPath;
	
	public ExisteLearningPathException(String string) {
		// TODO Auto-generated constructor stub
	
	
	super();
	this.yaExisteLearningPath= string;
	}
	
	@Override
	
	public String getMessage() {
		return yaExisteLearningPath;
	}
}