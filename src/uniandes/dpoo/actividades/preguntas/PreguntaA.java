package uniandes.dpoo.actividades.preguntas;

public class PreguntaA {
	
	private String pregunta;
	private String respuesta;
	
	public PreguntaA(String pregunta) {
		this.pregunta = pregunta;
	}

    public void ingresarRespuesta(String text) {
        this.respuesta = text;
    }
    
    public String getRespuesta() {
        return respuesta;
    }
	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

}
