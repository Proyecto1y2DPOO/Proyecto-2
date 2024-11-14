package uniandes.dpoo.actividades;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Recurso extends Actividad {
	
	private String tipoRecurso;
	private String URLRecuso;

	public Recurso(String titulo, String descripcion, int duracion, String nivelDificultad,
			List<String> actividadesPrevias, LocalDateTime fechaLimite, boolean obligatoria, String creador,
			String tipoRecurso, String uRLRecuso,String objetivo) {
		super(titulo, descripcion, duracion, nivelDificultad, actividadesPrevias, fechaLimite, obligatoria, creador, objetivo);
		this.tipoRecurso = tipoRecurso;
		URLRecuso = uRLRecuso;
	}

	@SuppressWarnings("unused")
	private List<String> verRecurso() {
		List<String> lista=new ArrayList<>();
		lista.add(tipoRecurso);
		lista.add(URLRecuso);
		return lista;
    }

	public String getTipoRecurso() {
		return tipoRecurso;
	}

	public void setTipoRecurso(String tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}

	public String getURLRecuso() {
		return URLRecuso;
	}

	public void setURLRecuso(String uRLRecuso) {
		URLRecuso = uRLRecuso;
	}

}
