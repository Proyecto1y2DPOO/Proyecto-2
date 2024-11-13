package uniandes.dpoo.actividades;

import java.time.LocalDateTime;
import java.util.List;

public class Tarea extends Actividad {

	public Tarea(String titulo, String descripcion, int duracion, String nivelDificultad,
			List<String> actividadesPrevias, LocalDateTime fechaLimite, boolean obligatoria, String creador) {
		super(titulo, descripcion, duracion, nivelDificultad, actividadesPrevias, fechaLimite, obligatoria, creador);
	}

}
