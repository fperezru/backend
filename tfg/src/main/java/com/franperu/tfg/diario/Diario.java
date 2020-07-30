package com.franperu.tfg.diario;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.franperu.tfg.login.Usuario;
import com.sun.istack.NotNull;

@Entity
@Table(name = "diario")
public class Diario {
	
	@Id
	@Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
    @Column(name = "titulo")
    private String titulo;
	
	@NotNull
    @Column(name = "fecha")
    private Date fecha;
	
	@Column(name = "diario")
    private String diario;
	
	@NotNull
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
	
	public Diario() {
		
	}
	
	public Diario(String titulo, Date fecha, String diario, Usuario usuario) {
		this.titulo = titulo;
		this.fecha = fecha;
		this.diario = diario;
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return this.titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public Date getFecha() {
		return this.fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public String getDiario() {
		return this.diario;
	}
	
	public void setDiario(String diario) {
		this.diario = diario;
	}
}
