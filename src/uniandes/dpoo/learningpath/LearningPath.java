package uniandes.dpoo.learningpath;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uniandes.dpoo.actividades.Actividad;
import uniandes.dpoo.actividades.Encuesta;
import uniandes.dpoo.actividades.Examen;
import uniandes.dpoo.actividades.Quiz;
import uniandes.dpoo.actividades.Recurso;
import uniandes.dpoo.actividades.Tarea;
import uniandes.dpoo.exceptions.ActNoEsPreguntaException;
import uniandes.dpoo.exceptions.ExisteUsuarioException;
import uniandes.dpoo.exceptions.NoEsQuizException;
import uniandes.dpoo.exceptions.NoExisteActividadException;
import uniandes.dpoo.exceptions.NoHaRealizadoActException;
import uniandes.dpoo.exceptions.YaTieneOtraActException;

public class LearningPath {
	
	private Map<String,String> activas;
	private String[] objetivos;
	private Map<String,Actividad> actividades;
	private Map<String,Map<String,Actividad>> estudiantesAct;
	private List<String> estudiantes;
	private Map<String,Integer> exitos;
	private Map<String,Integer> fracasos;
	private String titulo;
	private String desContenido;
	private String nivelDificultad;
	private int duracionMin;
	private Double rating;
	private List<String> reseñas;
	private List<Double> ratings;
	private LocalDateTime fechaCreacion;
	private LocalDateTime fechaModificacion;
	private Map<String,LocalDateTime> fechaInicio;
	private Map<String,LocalDateTime> fechaFinalisacion;
	private Map<String,Integer> progresos;
	private int version;
	private String creador;
	
	public LearningPath(String titulo,String desContenido,String[] objetivos,String nivelD,int duracionMin,LocalDateTime fechaCre,String creador) 
	{
		this.titulo = titulo;
        this.objetivos = objetivos;
        this.nivelDificultad = nivelD;
        this.duracionMin = duracionMin;
        this.rating = 0.0;
        this.fechaCreacion = fechaCre;
        this.fechaModificacion = fechaCre;
        this.creador = creador;
        this.actividades = new HashMap<>();
        this.activas = new HashMap<>();
        this.estudiantes= new ArrayList<>();
        this.estudiantesAct = new HashMap<>();
        this.version=1;
	}
	
	public void agregarEstudiante(String loginStu,LocalDateTime fecha) throws Exception {
		
		if (existeEstudiante(loginStu)!=true) {
			estudiantes.add(loginStu);
			estudiantesAct.put(loginStu,actividades);
			activas.put(loginStu,null);
			progresos.put(loginStu, 0);
			fechaInicio.put(loginStu, fecha);
		}
		else {
			throw new ExisteUsuarioException("Ya esta ese estudiante");
		}
	}
    
	public void agregarActividad(Actividad actividad) {
		actividades.put(actividad.getTitulo(),actividad);
	}
	
	
    public boolean estaIniciadaOtraActividad(String loginStu) {
        if (activas.get(loginStu)!= null) {
        	return true;
        }
        else {
        	return false;
        }
    }
    
    public Actividad mostrarActividadIniada(String login) throws Exception {
			String tituloAct = activas.get(login);
			Map<String,Actividad> activi=estudiantesAct.get(login);
			Actividad actividad =activi.get(tituloAct);
			return actividad;
    }
	
    public void completarActividad(String loginStu ,String estado,LocalDateTime fecha) throws Exception {
    	if (existeEstudiante(loginStu)==true) {
	        String actividad = activas.get(loginStu);
	        if (existeActivdad(actividad)==true) {
	        	if (actividad != null) {
	        		Actividad actividad1=actividades.get(actividad);
	        		actividad1.cambiarEstado(estado);
	        		activas.put(loginStu,null);
	        		calcularEXitos(loginStu);
	        		calcularFracsos(loginStu);
	        		calcularProgreso(loginStu);
	        		calcularFinalizacion(loginStu, fecha);
	        	}
	        	else {
					throw new YaTieneOtraActException();
				}
	        }
	        else {
	        	throw new NoExisteActividadException();
	        }
    	}
		else {
			throw new ExisteUsuarioException("No esta inscrito ese estudiante");
		}
    }
    
	public void crearTarea(String titulo, String descripcion, int duracion, String nivelDificultad, List<String> actividadesPrevias, LocalDateTime fechaLimite, boolean obligatoria, String creado) {
		Actividad actividad =new Tarea(titulo,descripcion,duracion,nivelDificultad, actividadesPrevias,  fechaLimite,obligatoria, creador);
		actividades.put(actividad.getTitulo(),actividad);
	}
	public void crearQuiz(String titulo, String descripcion, int duracion, String nivelDificultad, List<String> actividadesPrevias, LocalDateTime fechaLimite, boolean obligatoria, String creado,Double califMin) {
		Actividad actividad =new Quiz(titulo,descripcion,duracion,nivelDificultad, actividadesPrevias,  fechaLimite,obligatoria, creador,califMin);
		actividades.put(actividad.getTitulo(),actividad);
	}
	public void crearEncuesta(String titulo, String descripcion, int duracion, String nivelDificultad, List<String> actividadesPrevias, LocalDateTime fechaLimite, boolean obligatoria, String creado) {
		Actividad actividad =new Encuesta(titulo,descripcion,duracion,nivelDificultad, actividadesPrevias,  fechaLimite,obligatoria, creador);
		actividades.put(actividad.getTitulo(),actividad);
	}
	public void crearExamen(String titulo, String descripcion, int duracion, String nivelDificultad, List<String> actividadesPrevias, LocalDateTime fechaLimite, boolean obligatoria, String creado) {
		Actividad actividad =new Examen(titulo,descripcion,duracion,nivelDificultad, actividadesPrevias,  fechaLimite,obligatoria, creador);
		actividades.put(actividad.getTitulo(),actividad);
	}
	public void crearRecurso(String titulo, String descripcion, int duracion, String nivelDificultad, List<String> actividadesPrevias, LocalDateTime fechaLimite, boolean obligatoria, String creado,
			String tipoRecurso, String uRLRecuso) {
		Actividad actividad =new Recurso(titulo,descripcion,duracion,nivelDificultad, actividadesPrevias,  fechaLimite,obligatoria, creador, tipoRecurso,uRLRecuso);
		actividades.put(actividad.getTitulo(),actividad);
	}
	
	public Map<String,Actividad> mostrarActividades() {
		return actividades;
	}
	

	
	public Actividad mostrarActividad(String tituloAct,String login) throws Exception {
		if (existeActivdad(tituloAct)==true) {
			if (existeEstudiante(login)==true) {
				Map<String,Actividad> activi=estudiantesAct.get(login);
				Actividad actividad =activi.get(tituloAct);
				return actividad;
			}
			else {
				Actividad actividad =actividades.get(tituloAct);
				return actividad;
			}
		}
		else {
			throw new NoExisteActividadException();
		}
	}
	
	public void cambiarEstadoAct(String loginStu,String tituloactividad ,String estado) throws Exception {
		if (existeActivdad(tituloactividad)==true) {
			if (existeEstudiante(loginStu)==true) {
	    		Actividad actividad1=actividades.get(tituloactividad);
	    		actividad1.cambiarEstado(estado);
			}
			else {
				throw new ExisteUsuarioException("No esta inscrito ese estudiante");
			}
        }
        else {
        	throw new NoExisteActividadException();
        }
	}
	
	public Actividad iniciarActividad(String tituloAct,String login) throws Exception {
		if (existeActivdad(tituloAct)==true ) {
			if (existeEstudiante(login)==true) {
				if (estaIniciadaOtraActividad(login)!=true) {
					activas.put(login,tituloAct ) ;
					Map<String,Actividad> activi=estudiantesAct.get(login);
					Actividad actividad =activi.get(tituloAct);
					//String tipodeclase =actividad.toString();
					
					
					return actividad;
				}
				else {
					throw new YaTieneOtraActException();
				}
			}
			else {
				throw new ExisteUsuarioException("No esta inscrito ese estudiante");
			}
		}
		else {
			throw new NoExisteActividadException();
		}
	}
	
	public Double verCalificacionAct(String loginStu,String tituloactividad ) throws Exception {
		if (existeActivdad(tituloactividad)==true) {
			if (existeEstudiante(loginStu)==true) {
				Map<String,Actividad> activis=estudiantesAct.get(loginStu);
	    		Actividad actividad1=activis.get(tituloactividad);
	    		if (actividad1.getEstado()!="") {
	    			return actividad1.getNota();
				}
				else {
					throw new NoHaRealizadoActException();
				}
			}
			else {
				throw new ExisteUsuarioException("No esta inscrito ese estudiante");
			}
        }
        else {
        	throw new NoExisteActividadException();
        }
	}
	
	public Double verCalificacionQuiz(String loginStu,String tituloactividad ) throws Exception {
		if (existeActivdad(tituloactividad)==true) {
			Map<String,Actividad> activis=estudiantesAct.get(loginStu);
    		Actividad actividad1=activis.get(tituloactividad);
    		if (actividad1.getEstado()!="") {
    			String esQuiz=actividad1.toString();
	    		if (esQuiz.contains("Quiz")==true) {
	    			return actividad1.getNota();
	    		}
	    		else {
	    			throw new NoEsQuizException();
	    		}	
    		}
    		else {
    			throw new NoHaRealizadoActException();
    		}
        }
        else {
        	throw new NoExisteActividadException();
        }
	}
	
	public void cambiarCalificacion(String loginStu,String tituloactividad,Double nota ) throws Exception {
		if (existeActivdad(tituloactividad)==true) {
    		Actividad actividad1=actividades.get(tituloactividad);
    		if (actividad1.getEstado()!="") {
    			actividad1.calificar(nota);	
    		}
    		else {
    			throw new NoHaRealizadoActException();
    		}
        }
        else {
        	throw new NoExisteActividadException();
        }
	}
	
	public boolean existeEstudiante(String login) {
    	if (estudiantes.contains(login)==true ) {
    		return true;
    	}
    	else {
        	return false;
    	}
    }
	
	private boolean existeActivdad(String tituloAct) {
		if (actividades.containsKey(tituloAct)==true ) {
    		return true;
    	}
    	else {
        	return false;
    	}
	}
	
	public void calcularEXitos(String login) throws Exception {
		if (existeEstudiante(login)==true) {
			Map<String,Actividad> activis=estudiantesAct.get(login);
			int exitosas=0;
	    	for (Map.Entry<String, Actividad> entry : activis.entrySet()) {
	    		Actividad acti=entry.getValue();
	    		if (acti.getEstado()=="exitosa") {
	    			exitosas++;	
	    		}
	    	}
	    	int porcentage=0;
	    	if (exitosas==0) {
	    		porcentage =(int) (exitosas/activis.size()*100);
	    	}
	    	exitos.put(login, porcentage);
		}
		else {
			throw new ExisteUsuarioException("No esta inscrito ese estudiante");
		}
    }
	
	public void calcularFracsos(String login) throws Exception {
		if (existeEstudiante(login)==true) {
			Map<String,Actividad> activis=estudiantesAct.get(login);
			int fracasos1=0;
	    	for (Map.Entry<String, Actividad> entry : activis.entrySet()) {
	    		Actividad acti=entry.getValue();
	    		if (acti.getEstado()=="no exitosa") {
	    			fracasos1++;	
	    		}
	    	}
	    	int porcentage=0;
	    	if (fracasos1!=0) {
	    		porcentage =(int) (fracasos1/activis.size()*100);
	    	}
	    	fracasos.put(login, porcentage);
		}
		else {
			throw new ExisteUsuarioException("No esta inscrito ese estudiante");
		}
    }
	
	public void calcularProgreso(String login) throws Exception {
		if (existeEstudiante(login)==true) {
			Map<String,Actividad> activis=estudiantesAct.get(login);
			int respondidas=0;
	    	for (Map.Entry<String, Actividad> entry : activis.entrySet()) {
	    		Actividad acti=entry.getValue();
	    		if (acti.getEstado()!="") {
	    			respondidas++;	
	    		}
	    	}
	    	int porcentage=0;
	    	if (respondidas!=0) {
	    		porcentage =(int) (respondidas/activis.size()*100);
	    	}
	    	progresos.put(login, porcentage);
		}
		else {
			throw new ExisteUsuarioException("No esta inscrito ese estudiante");
		}
    }
	
	public int verProgreso(String loginStu) throws Exception {
		if (existeEstudiante(loginStu)==true) {
    		return progresos.get(loginStu);
		}
		else {
			throw new ExisteUsuarioException("No esta inscrito ese estudiante");
		}
	}
    
	public int verExitos(String loginStu) throws Exception {
		if (existeEstudiante(loginStu)==true) {
    		return exitos.get(loginStu);
		}
		else {
			throw new ExisteUsuarioException("No esta inscrito ese estudiante");
		}
	}
    
	public int verFracasos(String loginStu) throws Exception {
		if (existeEstudiante(loginStu)==true) {
    		return fracasos.get(loginStu);
		}
		else {
			throw new ExisteUsuarioException("No esta inscrito ese estudiante");
		}
	}
	public Boolean calcularFinalizacion(String login,LocalDateTime fecha) throws Exception {
		if (existeEstudiante(login)==true) {
			Map<String,Actividad> activis=estudiantesAct.get(login);
			int completados=0;
	    	for (Map.Entry<String, Actividad> entry : activis.entrySet()) {
	    		Actividad acti=entry.getValue();
	    		if (acti.getEstado()!="") {
	    			completados++;	
	    		}
	    	}
	    	if (completados==activis.size()) {
	    		fechaFinalisacion.put(login, fecha);
	    		return true;
	    	}
	    	return false;
		}
		else {
			throw new ExisteUsuarioException("No esta inscrito ese estudiante");
		}
	}
	
	public int calcularDuracionMin(String login) throws Exception {
		if (existeEstudiante(login)==true) {
			LocalDateTime fechaEmpezo=fechaInicio.get(login);
			LocalDateTime fechaFin=fechaFinalisacion.get(login);
			Duration duracion = Duration.between(fechaEmpezo, fechaFin);
	        int minutos = (int) (duracion.toMinutes() % 60);
	        return minutos;
		}
		else {
			throw new ExisteUsuarioException("No esta inscrito ese estudiante");
		}
	}
	
	public void agregarPreguntaQuiz(String tituloactividad,String pregunta, String opcion1, String opcion2, String opcion3, String opcion4, int opcionC,
			String explicacion) throws Exception {
		if (existeActivdad(tituloactividad)==true) {
    		Actividad actividad1=actividades.get(tituloactividad);
			String esQuiz=actividad1.toString();
    		if (esQuiz.contains("Quiz")==true) {
    			((Quiz) actividad1).agregarPregunta( pregunta, opcion1, opcion2, opcion3, opcion4, opcionC,
			 explicacion);
    		}
    		else {
    			throw new ActNoEsPreguntaException();
    		}
        }
        else {
        	throw new NoExisteActividadException();
        }
	}
	
	public void agregarPregunta(String tituloactividad,String pregunta) throws Exception {
		if (existeActivdad(tituloactividad)==true) {
    		Actividad actividad1=actividades.get(tituloactividad);
			String act=actividad1.toString();
    		if (act.contains("Encuesta")==true) {
    			((Encuesta) actividad1).agregarPregunta( pregunta);
    		}
    		if (act.contains("Examen")==true) {
    			((Examen) actividad1).agregarPregunta( pregunta);
    		}
    		else {
    			throw new ActNoEsPreguntaException();
    		}
        }
        else {
        	throw new NoExisteActividadException();
        }
	}
	public void agregarReseña(String reseña) {
		reseñas.add(reseña);
	}
	public void agregarRating(Double rating) {
		ratings.add(rating);
		calcularRating();
	}
	public void calcularRating() {
		int rati=0;
    	for (int i = 0; i < ratings.size(); i++) {
    		Double ratin=ratings.get(i);
    		rating+=ratin;	
    	}
    	if (rati!=0) {
    		rating=(double) (rati/ratings.size());
    	}
    	else {
    		rating=0.0;
    	}
	}
	public void agregarReseñaAct(String reseña,String tituloAct) {
		Actividad acti=actividades.get(tituloAct);
		acti.agregarReseña(reseña);
	}
	public void agregarRatingAct(Double ratin,String tituloAct) {
		Actividad acti=actividades.get(tituloAct);
		acti.agregarRating(ratin);
	}
	
	
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDesContenido() {
		return desContenido;
	}
	public void setDesContenido(String descContenido) {
		this.desContenido = descContenido;
	}
	public String[] getObjetivos() {
		return objetivos;
	}
	public void setObjetivos(String[] objetivos) {
		this.objetivos = objetivos;
	}
	public String getNivelDificultad() {
		return nivelDificultad;
	}
	public void setNivelDificultad(String nivelDificultad) {
		this.nivelDificultad = nivelDificultad;
	}
	public int getDuracionMin() {
		return duracionMin;
	}
	public void setDuracionMin(int duracionMin) {
		this.duracionMin = duracionMin;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public LocalDateTime getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public Map<String,Actividad> getActividades() {
		return actividades;
	}
	public void setActividades(Map<String,Actividad> actividades) {
		this.actividades = actividades;
	}

	public String getCreador() {
		return creador;
	}

	public void setCreador(String creador) {
		this.creador = creador;
	}


	public Map<String,Map<String,Actividad>> getEstudiantesAct() {
		return estudiantesAct;
	}

	public void setEstudiantesAct(Map<String,Map<String,Actividad>> estudiantesAct) {
		this.estudiantesAct = estudiantesAct;
	}

	public List<String> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(List<String> estuantes) {
		this.estudiantes = estuantes;
	}

	public Map<String,LocalDateTime> getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Map<String,LocalDateTime> fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Map<String,LocalDateTime> getFechaFinalisacion() {
		return fechaFinalisacion;
	}

	public void setFechaFinalisacion(Map<String,LocalDateTime> fechaFinalisacion) {
		this.fechaFinalisacion = fechaFinalisacion;
	}

	public Map<String,Integer> getExitos() {
		return exitos;
	}

	public void setExitos(Map<String,Integer> exitos) {
		this.exitos = exitos;
	}

	public Map<String,Integer> getFracasos() {
		return fracasos;
	}

	public void setFracasos(Map<String,Integer> fracasos) {
		this.fracasos = fracasos;
	}

	public List<String> getReseña() {
		return reseñas;
	}

	public void setReseña(List<String> reseñas) {
		this.reseñas = reseñas;
	}

	public List<Double> getRatings() {
		return ratings;
	}

	public void setRatings(List<Double> ratings) {
		this.ratings = ratings;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
