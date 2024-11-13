package uniandes.dpoo.actividades.preguntas;

public class PreguntaM {
	
	private String pregunta;
    private String opcion1;
    private String opcion2;
    private String opcion3;
    private String opcion4;
    private int opcionC;
    private String explicacion;
    private int respuesta;
    private Boolean bool;

    public PreguntaM(String pregunta, String opcion1, String opcion2, String opcion3, String opcion4, int opcionC,
			String explicacion) {
		super();
		this.pregunta = pregunta;
		this.opcion1 = opcion1;
		this.opcion2 = opcion2;
		this.opcion3 = opcion3;
		this.opcion4 = opcion4;
		this.opcionC = opcionC;
		this.explicacion = explicacion;
		
	}


	public String ingresarRespuesta(int opcion) {
        // Code to submit answer
		if(opcion ==opcionC) {
			respuesta=opcion;
			bool=true;
			return "La respuesta es correcta por:"+explicacion;
		}
		else {
			respuesta=opcion;
			bool=false;
			return "La respuesta es incorrecta por:"+explicacion;
		}    
    }

	public String getOpcion1() {
		return opcion1;
	}

	public void setOpcion1(String opcion1) {
		this.opcion1 = opcion1;
	}

	public String getOpcion2() {
		return opcion2;
	}

	public void setOpcion2(String opcion2) {
		this.opcion2 = opcion2;
	}

	public String getOpcion3() {
		return opcion3;
	}

	public void setOpcion3(String opcion3) {
		this.opcion3 = opcion3;
	}

	public String getOpcion4() {
		return opcion4;
	}

	public void setOpcion4(String opcion4) {
		this.opcion4 = opcion4;
	}

	public int getOpcionC() {
		return opcionC;
	}

	public void setOpcionC(int opcionC) {
		this.opcionC = opcionC;
	}

	public String getExplicacion() {
		return explicacion;
	}

	public void setExplicacion(String explicacion) {
		this.explicacion = explicacion;
	}

	public int getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(int respuesta) {
		this.respuesta = respuesta;
	}
    

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}


	public Boolean getBool() {
		return bool;
	}


	public void setBool(Boolean bool) {
		this.bool = bool;
	}

}
