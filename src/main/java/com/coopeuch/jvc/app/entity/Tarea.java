package com.coopeuch.jvc.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tareas")
public class Tarea implements Serializable{
	
	private static final long serialVersionUID = 6701979027180878382L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	@NotEmpty(message="La Descripción es obligatoria")
    @Column(name = "Descripcion")
    private String descripcion;

	@NotNull(message="La fecha no puede estar vacía")
    @Column(name = "Fecha_Creacion")
    private Date fechaCreacion;

    @Column(name = "Vigente")
    private boolean vigente;

    public Tarea(){}

    public Tarea(long id, String descripcion, Date fechaCreacion, boolean vigente) {
        super();
        this.id = id;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.vigente = vigente;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public boolean isVigente() {
		return vigente;
	}

	public void setVigente(boolean vigente) {
		this.vigente = vigente;
	}
}

