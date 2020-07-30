package com.franperu.tfg.informacion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.franperu.tfg.login.Usuario;

@Entity
@Table(name = "informacion")
public class Informacion {
	
	@Id
	@Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
    @Column(name = "titulo")
    private String titulo;
	
	@NotNull
	@Column(name = "contenido")
	private String contenido;
	
	@NotNull
	@JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "admin_id", nullable = false)
    private Usuario usuario;
	
	@NotNull
	@JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_id", nullable = false)
    private Tipo tipo;
	
	public Informacion() {
		
	}
	
	public Informacion(String titulo, String contenido, Usuario usuario, Tipo tipo ) {
		this.titulo = titulo;
		this.contenido = contenido;
		this.usuario = usuario;
		this.tipo = tipo;
		
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Tipo getTipo() {
		return this.tipo;
	}
	
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
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
	
	public String getContenido() {
		return this.contenido;
	}
	
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

}
