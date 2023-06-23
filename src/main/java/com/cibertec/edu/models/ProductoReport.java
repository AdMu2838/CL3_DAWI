package com.cibertec.edu.models;

import java.util.Date;

public class ProductoReport {
	private String nombre;
    private String descripcion;
    private Date fechaRegistro;

    public ProductoReport(String nombre, String descripcion, Date fechaRegistro) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
    
    
}
