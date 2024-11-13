package uniandes.dpoo.plataforma;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;

import uniandes.dpoo.actividades.Actividad;
import uniandes.dpoo.exceptions.ContraseñaIncorrectaException;
import uniandes.dpoo.exceptions.ExisteLearningPathException;
import uniandes.dpoo.exceptions.ExisteUsuarioException;
import uniandes.dpoo.exceptions.noEsProfeException;
import uniandes.dpoo.exceptions.noHaIniciadoException;
import uniandes.dpoo.learningpath.LearningPath;
import uniandes.dpoo.usuario.Professor;
import uniandes.dpoo.usuario.Student;

public class Plataforma {
	
	private Map<String,Student> students;
	private Map<String,Professor> profesores;
	private Map<String,LearningPath> learningPaths;
	private LocalDateTime fechaAct;
	public String usuario;
    private Boolean esProfe;

    // Constructor for Plataforma
    public Plataforma() 
    {	
    	students = new HashMap<>();
        profesores = new HashMap<>();
        learningPaths = new HashMap<>();
        fechaAct =LocalDateTime.now();
        usuario="";
        esProfe=false;
        
    }

    // Methods from the diagram

    public void registrar(String login, String contraseña, boolean bool) throws Exception {
    	if (true==existeUsuario(login)) {
    		throw new ExisteUsuarioException("Ya existe este usuario");
    	}
    	else {
    		if(bool==true) {
    			Professor profesor=new Professor(login,contraseña);
    			profesores.put(login, profesor);
    			esProfe=true;
				usuario=login;
    		}
    		else {
    			Student estudiante=new Student(login,contraseña);
    			students.put(login, estudiante);
				usuario=login;
    		}
    	}
    }

    public boolean existeUsuario(String login) {
    	if (students.containsKey(login)==true || profesores.containsKey(login)==true) {
    		return true;
    	}
    	else {
        	return false;
    	}
    }

    public boolean isProfe(String login) {
    	if ( profesores.containsKey(login)==true) {
    		return true;
    	}
    	else {
        	return false;
    	}
    }
    
    public void InformationUsuario(String login,String name, Date dateOfBirth, String direccion, int cedula, String nivelDeEducacion) {
        // Code to update user information
    	if (isProfe(login)!=false){
    		Professor profe=profesores.get(login);
    		profe.informetionUsuario(name, dateOfBirth, direccion,cedula, nivelDeEducacion);
    	}
    	else {
    		Student estudiante=students.get(login);
    		estudiante.informetionUsuario(name, dateOfBirth, direccion, cedula, nivelDeEducacion);
    		}
    }
    
    public void iniciarSesion(String login, String contraseña) throws Exception {
    	if (true==existeUsuario(login)) {
    		throw new ExisteUsuarioException("Ya existe este usuario");
    	}
    	else {
	    	if (students.containsKey(login)!=true && profesores.containsKey(login)!=true) {
	    		throw new ExisteUsuarioException("No existe ese usuario");
	    	}
	    	if(profesores.containsKey(login)==true) {
	    		Professor profe=profesores.get(login);
	    		if (profe.getContraseña()==contraseña) {
					esProfe=true;
					usuario=login;
	    		}
	    		else {
	    			throw new ContraseñaIncorrectaException();
	    		}
			}
			else {
				Student estudiante=students.get(login);
	    		if (estudiante.getContraseña()==contraseña) {
					usuario=login;
	    		}
	    		else {
	    			throw new ContraseñaIncorrectaException();
	    		}
			}
		}
	    	
    }

    public void crearLearningPath(String titulo,String desContenido,String objetivos,String nivelD,int duracionMin,String creador) throws Exception
	{
    	if (usuario==null) {
    		throw new noHaIniciadoException("No se ha iniciado sesion");
    	}
    		
    	if (esProfe==false){
    		throw new noEsProfeException("No es profesor no puede crear un Learning Path");
    	}
    	else {
    		if (existeLP(titulo)!=true) {
    			throw new ExisteLearningPathException("Ya existe ese Learning Path");
    		}
    		else {
    			LearningPath learningParth = new LearningPath(titulo,desContenido,objetivos.split(",."), nivelD, duracionMin,fechaAct, creador);
    			learningPaths.put(titulo, learningParth);
    		}
    		
    	}
    }
    
    public boolean existeLP(String tituloLP) {
        // Code to check if a user exists
    	if (learningPaths.containsKey(tituloLP)==true ) {
    		return true;
    	}
    	else {
        	return false;
    	}
    }
    
    public void crearEncuesta(String tituloLP,String titulo, String descripcion, int duracion, String nivelDificultad, List<String> actividadesPrevias, LocalDateTime fechaLimite, boolean obligatoria, String creador) throws Exception {
    	if (esProfe==false){
    		throw new noEsProfeException("No es profesor no puede crear un Learning Path");
    	}
    	else {
    		if (existeLP(tituloLP)!=true) {
				throw new ExisteLearningPathException("No existe ese Learning Path");
			}
			else {
				LearningPath learningPath=learningPaths.get(tituloLP);
				learningPath.crearEncuesta(titulo, descripcion, duracion, nivelDificultad, actividadesPrevias, fechaLimite, obligatoria, creador);
			}
    	}
    	
	}
    
    public void crearQuiz(String tituloLP,String titulo, String descripcion, int duracion, String nivelDificultad, List<String> actividadesPrevias, LocalDateTime fechaLimite, boolean obligatoria, String creador,Double califMin) throws Exception {
    	if (esProfe==false){
    		throw new noEsProfeException("No es profesor no puede crear un Learning Path");
    	}
    	else {
    		if (existeLP(tituloLP)!=true) {
				throw new ExisteLearningPathException("No existe ese Learning Path");
			}
			else {
				LearningPath learningPath=learningPaths.get(tituloLP);
				learningPath.crearQuiz(titulo, descripcion, duracion, nivelDificultad, actividadesPrevias, fechaLimite, obligatoria, creador,califMin);
			}
    	}
    	
	}
    
    public void crearTarea(String tituloLP,String titulo, String descripcion, int duracion, String nivelDificultad, List<String> actividadesPrevias, LocalDateTime fechaLimite, boolean obligatoria, String creador) throws Exception {
    	if (esProfe==false){
    		throw new noEsProfeException("No es profesor no puede crear un Learning Path");
    	}
	    	if (existeLP(tituloLP)==true) {
				throw new ExisteLearningPathException("No existe ese Learning Path");
			}
			else {
				LearningPath learningPath=learningPaths.get(tituloLP);
				learningPath.crearTarea(titulo, descripcion, duracion, nivelDificultad, actividadesPrevias, fechaLimite, obligatoria, creador);
			}
	}
    
    public void crearRecurso(String tituloLP,String titulo, String descripcion, int duracion, String nivelDificultad, List<String> actividadesPrevias, LocalDateTime fechaLimite, boolean obligatoria, String creador,String tipoRecurso,String uRLRecuso) throws Exception {
    	if (esProfe==false){
    		throw new noEsProfeException("No es profesor no puede crear un Learning Path");
    	}
    	else {
	    	if (existeLP(tituloLP)==true) {
				throw new ExisteLearningPathException("No existe ese Learning Path");
			}
			else {
				LearningPath learningPath=learningPaths.get(tituloLP);
				learningPath.crearRecurso(titulo, descripcion, duracion, nivelDificultad, actividadesPrevias, fechaLimite, obligatoria, creador, tipoRecurso,uRLRecuso);
			}
    	}
	}
    
    public void crearExamen(String tituloLP,String titulo, String descripcion, int duracion, String nivelDificultad, List<String> actividadesPrevias, LocalDateTime fechaLimite, boolean obligatoria, String creador) throws Exception {
    	if (esProfe==false){
    		throw new noEsProfeException("No es profesor no puede crear un Learning Path");
    	}
    	else {
	    	if (existeLP(tituloLP)!=true) {
				throw new ExisteLearningPathException("No existe ese Learning Path");
			}
			else {
				LearningPath learningPath=learningPaths.get(tituloLP);
				learningPath.crearExamen(titulo, descripcion, duracion, nivelDificultad, actividadesPrevias, fechaLimite, obligatoria, creador);
			}
    	}
	}
    
    public void inscribirLearningPath(String tituloLP) throws Exception {
    	if (existeLP(tituloLP)!=true) {
    		throw new ExisteLearningPathException("No existe ese Learning Path");
    		
		}
		else {
			if (esProfe!=false){
				throw new noEsProfeException("No es estudiante no puede inscribir a un Learning Path");
			}
			else {
				LearningPath learningPath=learningPaths.get(tituloLP);
				learningPath.agregarEstudiante(usuario,fechaAct);
				Student estudiante=students.get(usuario);
				estudiante.agregarLP(tituloLP, false);
			}
		}
	}
	
    public Map<String,Actividad> mostrarActividades(String tituloLP) throws Exception {
    	if (existeLP(tituloLP)!=true) {
			throw new ExisteLearningPathException("No existe ese Learning Path");
		}
		else {
			LearningPath learningPath=learningPaths.get(tituloLP);
			Map<String,Actividad> listaAct =learningPath.mostrarActividades();
			return listaAct;
		}
	}
    
    public Actividad mostrarActividad(String tituloLP,String TituloAct) throws Exception {
    	if (existeLP(tituloLP)!=true) {
			throw new ExisteLearningPathException("No existe ese Learning Path");
		}
		else {
			LearningPath learningPath=learningPaths.get(tituloLP);
			Actividad listaAct =learningPath.mostrarActividad(TituloAct,usuario);
			return listaAct;
		}
	}
    
    public Actividad iniciarActividad(String tituloLP,String actividad) throws Exception {
    	if (existeLP(tituloLP)!=true) {
			throw new ExisteLearningPathException("No existe ese Learning Path");
		}
		else {
			if (esProfe!=false){
				throw new noEsProfeException("No es estudiante no puede inscribir a un Learning Path");
			}
			else {
				LearningPath learningPath=learningPaths.get(tituloLP);
				return learningPath.iniciarActividad( actividad,usuario);
			}	
		}
    	
	}
    
    public LearningPath verLearningPath(String tituloLP) throws Exception {
    	if (existeLP(tituloLP)!=true) {
			throw new ExisteLearningPathException("No existe ese Learning Path");
		}
		else {
			return learningPaths.get(tituloLP);
		}
    }
    
    public void clonarActividad(String tituloLP,String actividad,String tituloLPDesti) throws Exception {
    	if (existeLP(tituloLP)==true && existeLP(tituloLPDesti)==true) {
				LearningPath learningPath1=learningPaths.get(tituloLP);
				LearningPath learningPath=learningPaths.get(tituloLPDesti);
				Actividad acti=learningPath1.mostrarActividad( actividad,usuario);
				acti.setCreador(usuario);
				learningPath.agregarActividad(acti);
		}
		else {
			
			throw new ExisteLearningPathException("No existe alguno de los dos Learning Path");
		}
	}

    public Actividad mostrarActividadIni(String tituloLP,String login) throws Exception {
    	if (existeLP(tituloLP)!=true) {
			throw new ExisteLearningPathException("No existe ese Learning Path");
		}
		else {
			LearningPath learningPath=learningPaths.get(tituloLP);
			Actividad listaAct =learningPath.mostrarActividadIniada(login);
			return listaAct;
		}
	}
    
    public void marcaCompletarActividad(String tituloLP,String estado) throws Exception {
    	if (esProfe!=false){
			throw new noEsProfeException("No es estudiante no puede completar una actividad");
		}
		else {
			if (existeLP(tituloLP)!=true) {
				throw new ExisteLearningPathException("No existe ese Learning Path");
			}
			else {
				LearningPath learningPath=learningPaths.get(tituloLP);
				learningPath.completarActividad( usuario,estado,fechaAct);
		
			}
		}
    }
    
    public void cambiarEstadoActividad(String tituloLP,String estado,String login,String tituloactividad) throws Exception {
    	if (existeLP(tituloLP)!=true) {
			throw new ExisteLearningPathException("No existe ese Learning Path");
		}
		else {
			LearningPath learningPath=learningPaths.get(tituloLP);
			learningPath.cambiarEstadoAct(login, tituloactividad, estado);
		}
    }
    
    public Double verCalificacion(String tituloLP, String tituloAc) throws Exception {
        // Code to view rating of an activity in a LearningPath
    	if (existeLP(tituloLP)!=true) {
			throw new ExisteLearningPathException("No existe ese Learning Path");
		}
		else {
			LearningPath learningPath=learningPaths.get(tituloLP);
			return learningPath.verCalificacionAct(usuario, tituloAc);
		}
    }
    
    public Double verCalificacionQuiz(String tituloLP, String tituloAc) throws Exception {
        // Code to view rating of an activity in a LearningPath
    	if (existeLP(tituloLP)!=true) {
			throw new ExisteLearningPathException("No existe ese Learning Path");
		}
		else {
			LearningPath learningPath=learningPaths.get(tituloLP);
			return learningPath.verCalificacionQuiz(usuario, tituloAc);
		}
    }

    public void cambiarCalificacion(String tituloLP, String tituloAc,Double nota,String login) throws Exception {
        // Code to view rating of an activity in a LearningPath
    	if (existeLP(tituloLP)!=true) {
			throw new ExisteLearningPathException("No existe ese Learning Path");
		}
		else {
			if (esProfe==false){
				throw new noEsProfeException("No eres un estudiante no puede cambiar la calificacion");
			}
			else {
				LearningPath learningPath=learningPaths.get(tituloLP);
				learningPath.cambiarCalificacion(login, tituloAc, nota);
			}
		}
    }
    
    public int verTasaExito(String tituloLP,String login) throws Exception {
        // Code to view the success rate in a LearningPath
    	if (existeLP(tituloLP)!=true) {
			throw new ExisteLearningPathException("No existe ese Learning Path");
		}
		else {
			LearningPath learningPath=learningPaths.get(tituloLP);
			return learningPath.verExitos(login);
		}
    }
   
    public int verTasaFracaso(String tituloLP,String login) throws Exception {
        // Code to view the failure rate in a LearningPath
    	if (existeLP(tituloLP)!=true) {
			throw new ExisteLearningPathException("No existe ese Learning Path");
		}
		else {
			LearningPath learningPath=learningPaths.get(tituloLP);
			return learningPath.verFracasos(login);
		}
    	
    }
    
    public void calcularDuraion(String tituloLP,String login) throws Exception {
    	if (existeLP(tituloLP)!=true) {
			throw new ExisteLearningPathException("No existe ese Learning Path");
		}
		else {
			if (esProfe!=false){
				throw new noEsProfeException("No eres un estudiante no puede cambiar la calificacion");
			}
			else {
				LearningPath learningPath=learningPaths.get(tituloLP);
				learningPath.calcularDuracionMin(login);
			}
		}
    } 
    
    public void agregarPreguntaQui(String tituloLP,String tituloactividad,String pregunta, String opcion1, String opcion2, String opcion3, String opcion4, int opcionC,
			String explicacion) throws Exception {
    	if (existeLP(tituloLP)!=true) {
			throw new ExisteLearningPathException("No existe ese Learning Path");
		}
		else {
			if (esProfe==false){
				throw new noEsProfeException("No eres un estudiante no puede cambiar la calificacion");
			}
			else {
				LearningPath learningPath=learningPaths.get(tituloLP);
				learningPath.agregarPreguntaQuiz(tituloactividad, pregunta, opcion1, opcion2, opcion3, opcion4, opcionC, explicacion);
			}
		}
    }
    
    public void agregarPregunta(String tituloLP,String tituloactividad,String pregunta) throws Exception {
    	if (existeLP(tituloLP)!=true) {
			throw new ExisteLearningPathException("No existe ese Learning Path");
		}
		else {
			if (esProfe==false){
				throw new noEsProfeException("No eres un estudiante no puede cambiar la calificacion");
			}
			else {
				LearningPath learningPath=learningPaths.get(tituloLP);
				learningPath.agregarPregunta(tituloactividad, pregunta);
			}
		}
    }

    public void generarReseña(String tituloLP,String reseña) throws Exception {
    	
    	if (existeLP(tituloLP)!=true) {
			throw new ExisteLearningPathException("No existe ese Learning Path");
		}
		else {
			LearningPath learningPath=learningPaths.get(tituloLP);
			learningPath.agregarReseña(reseña);
		}
    }

    public void generarReseñaAct(String tituloLP,String Act,String reseña) throws Exception {
    	
    	if (existeLP(tituloLP)!=true) {
			throw new ExisteLearningPathException("No existe ese Learning Path");
		}
		else {
			LearningPath learningPath=learningPaths.get(tituloLP);
			learningPath.agregarReseñaAct(reseña, Act);
		}
    }
    
    public void generarRating(String tituloLP,Double rating) throws Exception {
    	
    	if (existeLP(tituloLP)!=true) {
			throw new ExisteLearningPathException("No existe ese Learning Path");
		}
		else {
			LearningPath learningPath=learningPaths.get(tituloLP);
			learningPath.agregarRating(rating);
		}
    }
    
    public void generarRatingAct(String tituloLP,String tituloAct,Double rating) throws Exception {
    	
    	if (existeLP(tituloLP)!=true) {
			throw new ExisteLearningPathException("No existe ese Learning Path");
		}
		else {
			LearningPath learningPath=learningPaths.get(tituloLP);
			learningPath.agregarRatingAct(rating, tituloAct);
		}
    }

    public int verProgreso(String tituloLP,String login) throws Exception {
    	
    	if (existeLP(tituloLP)!=true) {
			throw new ExisteLearningPathException("No existe ese Learning Path");
		}
		else {
			LearningPath learningPath=learningPaths.get(tituloLP);
			return learningPath.verProgreso(login);
		}
    }

    public Double verRating(String tituloLP) throws Exception {
    	
    	if (existeLP(tituloLP)!=true) {
			throw new ExisteLearningPathException("No existe ese Learning Path");
		}
		else {
			LearningPath learningPath=learningPaths.get(tituloLP);
			return learningPath.getRating();
		}
    }

    public List<String> verReseñas(String tituloLP) throws Exception {
    	
    	if (existeLP(tituloLP)!=true) {
			throw new ExisteLearningPathException("No existe ese Learning Path");
		}
		else {
			LearningPath learningPath=learningPaths.get(tituloLP);
			return learningPath.getReseña();
		}
    }

    public void agregarInteres(String interes) throws Exception {
	
		if (esProfe!=false){
			throw new noEsProfeException("No eres un estudiante no puede cambiar la calificacion");
		}
		else {
			Student estu=students.get(usuario);
			estu.agregarInteres(interes);
			generarRecomen(interes, estu);
		}
    }

    public void agregarlogro(String logro) throws Exception {
	
		if (esProfe!=false){
			throw new noEsProfeException("No eres un estudiante no puede cambiar la calificacion");
		}
		else {
			Student estu=students.get(usuario);
			estu.agregarLogro(logro);
		}
    }
    
    public void agregarlogros(List<String> logros) throws Exception {
	
		if (esProfe!=false){
			throw new noEsProfeException("No eres un estudiante no puede cambiar la calificacion");
		}
		else {
			Student estu=students.get(usuario);
			estu.setLogro(logros);
		}
    }
    

    public void agregarInreses(List<String> interes) throws Exception {
	
		if (esProfe!=false){
			throw new noEsProfeException("No eres un estudiante no puede cambiar la calificacion");
		}
		else {
			Student estu=students.get(usuario);
			estu.setInterese(interes);
			for (int i = 0; i < interes.size(); i++) {
	    		String inte=interes.get(i);
	    		generarRecomen(inte, estu);
	    	}
		}
    }
    	
   

    public void generarRecomen( String interes,Student estu) {
        // Code to generate recommendations
    	for (Map.Entry<String, LearningPath> entry : learningPaths.entrySet()) {
    		String Name = entry.getKey();
    		LearningPath learning = entry.getValue();
    		if ( learning.getObjetivos().toString().contains(interes)) {
    			estu.agregarRecomendacion(Name);
    		}
    	}
    }
    
}
