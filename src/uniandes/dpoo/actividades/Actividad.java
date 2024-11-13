package uniandes.dpoo.actividades;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Actividad {
	
	private String titulo;
	private String descripcion;
	private int duracion;
	private String nivelDificultad;
	private List<String> actividadesPrevias;
	private LocalDateTime fechaCompletada;
	private LocalDateTime fechaLimite;
	private List<String> recomendacion;
	private String estado;
	private boolean obligatoria;
	private String creador;
	private int tiempoDedicado;
	private Double rating;
	private List<String> reseña;
	private List<Double> ratings;
	private Double nota;
	
	public Actividad(String titulo, String descripcion, int duracion, String nivelDificultad, List<String> actividadesPrevias, LocalDateTime fechaLimite, boolean obligatoria, String creador) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.nivelDificultad = nivelDificultad;
		this.actividadesPrevias = actividadesPrevias;
		this.fechaLimite = fechaLimite;
		this.obligatoria = obligatoria;
		this.creador = creador;
		this.tiempoDedicado = 0;
		this.rating = 0.0; 
		this.reseña = new ArrayList<>(); 
		this.recomendacion = new ArrayList<>();
		this.ratings = new ArrayList<>();
		this.estado="";
	}
	
	public void agregarReseña(String reseñas) {
		reseña.add(reseñas);
	}
	public void agregarRecomendacion(String reseña) {
		recomendacion.add(reseña);
	}
	public void agregarRating(Double rating) {
		ratings.add(rating);
		calcularRating();
	}

	public void calificar(Double nota) {
		this.setNota(nota);
	}
	public void cambiarEstado(String estado) {
		this.estado = estado;
	}
	public void calcularRating() {
		int rati=0;
    	for (int i = 0; i < ratings.size(); i++) {
    		Double ratin=ratings.get(i);
    		rating+=ratin;	
    	}
    	if (rati!=0) {
    		rating=(double) (rati/ratings.size()*5);
    	}
    	else {
    		rating=0.0;
    	}
	}
	
	
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public String getNivelDificultad() {
		return nivelDificultad;
	}
	public void setNivelDificultad(String nivelDificultad) {
		this.nivelDificultad = nivelDificultad;
	}
	public LocalDateTime getFechaCompletada() {
		return fechaCompletada;
	}
	public void setFechaCompletada(LocalDateTime fechaCompletada) {
		this.fechaCompletada = fechaCompletada;
	}
	public LocalDateTime getFechaLimite() {
		return fechaLimite;
	}
	public void setFechaLimite(LocalDateTime fechaLimite) {
		this.fechaLimite = fechaLimite;
	}
	public List<String> getActividadesPrevias() {
		return actividadesPrevias;
	}
	public void setActividadesPrevias(List<String> actividadesPrevias) {
		this.actividadesPrevias = actividadesPrevias;
	}
	public List<String> getRecomendacion() {
		return recomendacion;
	}
	public void setRecomendacion(List<String> recomendacion) {
		this.recomendacion = recomendacion;
	}
	public boolean isObligatoria() {
		return obligatoria;
	}
	public void setObligatoria(boolean obligatoria) {
		this.obligatoria = obligatoria;
	}
	public String getCreador() {
		return creador;
	}
	public void setCreador(String creador) {
		this.creador = creador;
	}
	public int getTiempoDedicado() {
		return tiempoDedicado;
	}
	public void setTiempoDedicado(int tiempoDedicado) {
		this.tiempoDedicado = tiempoDedicado;
	}
	public List<String> getReseña() {
		return reseña;
	}
	public void setReseña(List<String> reseña) {
		this.reseña = reseña;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public Double getNota() {
		return nota;
	}
	public void setNota(Double nota) {
		this.nota = nota;
	}
	public String getEstado() {
		return estado;
	}

	public List<Double> getRatings() {
		return ratings;
	}

	public void setRatings(List<Double> ratings) {
		this.ratings = ratings;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

		
}
