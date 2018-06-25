package com.rd_recicla.models;


public class Incidente {
	
	
	private Long id;
	
	private String titulo;
	
	private String detalle;
	
	private String estado = "Pending";
	
	private String longitud; 
	
	private String latitud;
	
	private Usuario usuario;
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Incidente [id=" + id + ", titulo=" + titulo + ", detalle=" + detalle + ", estado=" + estado
				+ ", longitud=" + longitud + ", latitud=" + latitud + ", usuario=" + usuario + "]";
	}


 
	
	
	
	
	

}
