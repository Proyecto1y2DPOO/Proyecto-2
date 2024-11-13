package uniandes.dpoo.actividades;

import java.time.LocalDateTime;
import java.util.List;

import uniandes.dpoo.actividades.preguntas.PreguntaA;

public class Encuesta extends Actividad {
	
	private List<PreguntaA> preguntas;
	
	public Encuesta(String titulo, String descripcion, int duracion, String nivelDificultad,
			List<String> actividadesPrevias, LocalDateTime fechaLimite, boolean obligatoria, String creador) {
		super(titulo, descripcion, duracion, nivelDificultad, actividadesPrevias, fechaLimite, obligatoria, creador);
		
	}
	
	public void agregarPregunta(String pregunta) {
    	PreguntaA pregunt= new PreguntaA(pregunta);
    	preguntas.add(pregunt);
    }
	
	public void ingresarRespuesta(String respuesta,int posion){
    	
    	PreguntaA pregunta=preguntas.get(posion);
    	pregunta.ingresarRespuesta(respuesta);
    	
    }

	public List<PreguntaA> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<PreguntaA> preguntas) {
		this.preguntas = preguntas;
	}

}
