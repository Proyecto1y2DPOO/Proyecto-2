package uniandes.dpoo.actividades;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import uniandes.dpoo.actividades.preguntas.PreguntaM;

public class Quiz extends Actividad {

	private List<PreguntaM> preguntas;
    private Double califMin;
    
    public Quiz(String titulo, String descripcion, int duracion, String nivelDificultad,
			List<String> actividadesPrevias, LocalDateTime fechaLimite, boolean obligatoria, String creador, Double califMin, String objetivo) {
		super(titulo, descripcion, duracion, nivelDificultad, actividadesPrevias, fechaLimite, obligatoria, creador, objetivo);
		this.setCalifMin(califMin);
		this.setPreguntas(new ArrayList<>());
	}
    public void agregarPregunta(String pregunta, String opcion1, String opcion2, String opcion3, String opcion4, int opcionC,
			String explicacion) {
    	PreguntaM pregunt= new PreguntaM(pregunta, opcion1, opcion2, opcion3, opcion4, opcionC, explicacion);
    	preguntas.add(pregunt);
    }
    
    public String ingresarRespuesta(int opcion,int posion){
    	
    	PreguntaM pregunta=preguntas.get(posion);
    	return pregunta.ingresarRespuesta(opcion);
    	
    }
    
    public void calcularCalificacion() {
    	int corretas=0;
    	for (int i = 0; i < preguntas.size(); i++) {
    		PreguntaM pregunta=preguntas.get(i);
    		if (pregunta.getBool()==true) {
    			corretas++;	
    		}
    	}
    	Double nota=0.0;
    	if (corretas!=0) {
    		nota=(double) (corretas/preguntas.size()*5);
    	}
    	if(nota>=califMin) {
    		super.cambiarEstado("exitosa");
    	}
    	else {
    		super.cambiarEstado("no exitosa");
    	}
    	super.setNota(nota);
    }

	public List<PreguntaM> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(List<PreguntaM> preguntas) {
		this.preguntas = preguntas;
	}
	public Double getCalifMin() {
		return califMin;
	}
	public void setCalifMin(Double califMin) {
		this.califMin = califMin;
	}
}
