package uniandes.dpoo.usuario;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public class Professor extends Usuario {
	
	private Map<String,LocalDateTime> learningPathCreados;
	private Map<String,LocalDateTime> ActividadesCreados;
	

	public Professor(String login, String contraseña) {
		super(login, contraseña);
		this.learningPathCreados=new HashMap<>();
		this.learningPathCreados=new HashMap<>();
		
	}

	public void agregarLearningPathCreeada(String tituloLP,LocalDateTime fecha) {
		learningPathCreados.put(tituloLP,fecha);
	}
	public void agregarActividadCreada(String tituloLP,LocalDateTime fecha) {
		learningPathCreados.put(tituloLP,fecha);
	}


	public Map<String, LocalDateTime> getLearningPathCreados() {
		return learningPathCreados;
	}


	public void setLearningPathCreados(Map<String, LocalDateTime> learningPathCreados) {
		this.learningPathCreados = learningPathCreados;
	}


	public Map<String, LocalDateTime> getActividadesCreados() {
		return ActividadesCreados;
	}


	public void setActividadesCreados(Map<String, LocalDateTime> actividadesCreados) {
		ActividadesCreados = actividadesCreados;
	}
	
}
