package uniandes.dpoo.exceptions;

@SuppressWarnings("serial")
public class YaTieneOtraActException extends Exception {

	private String yaTieneOtraAct;

	public YaTieneOtraActException() {
		// TODO Auto-generated constructor stub
		
		super();
		this.yaTieneOtraAct = "Ya tiene otra actividad en curso, por favor, acabela antes";
	}

	
	@Override
	public String getMessage(){
	
		return yaTieneOtraAct;
	}

}