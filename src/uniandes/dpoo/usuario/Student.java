package uniandes.dpoo.usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student extends Usuario {
	
	private List<String> intereses;
	private List<String> logros;
	private Map<String,Boolean> inscritos;
	private List<String> recomendaciones;
	private Map<String,Integer> tiempo;
	
	public Student(String login, String contraseña) {
        super(login,contraseña);
        this.intereses = new ArrayList<>();
        this.logros = new ArrayList<>();
        this.inscritos = new HashMap<>();
        this.recomendaciones = new ArrayList<>();
        this.tiempo =new HashMap<>();
        		
   		}
   public void agregarInteres(String interes) {
        intereses.add(interes);
    }

    public void agregarLogro(String logro) {
        logros.add(logro);
    }

    public void agregarLP(String tituloLP,boolean finalizado) {
        inscritos.put(tituloLP, finalizado);
    }
    public void agregarRecomendacion(String tituloLP) {
        recomendaciones.add(tituloLP);
    }
    public void agregarTiempo(String tituloLP,int duracion) {
    	tiempo.put(tituloLP, duracion);
    }

    public List<String> getIntereses() {
        return intereses;
    }
    public List<String> getRecomendaciones() {
        return recomendaciones;
    }
    public List<String> getLogros() {
        return logros;
    }
	
	public Map<String,Boolean> getInscritos() {
		return inscritos;
	}
	public void setInscritos(Map<String,Boolean> inscritos) {
		this.inscritos = inscritos;
	}
	public Map<String,Integer> getTiempo() {
		return tiempo;
	}
	public void setTiempo(Map<String,Integer> tiempo) {
		this.tiempo = tiempo;
	}
	
	public void setInterese(List<String> interese) {
		this.intereses = interese;
	}
	
	public void setLogro(List<String> logro) {
		this.logros = logro;
	}
}
