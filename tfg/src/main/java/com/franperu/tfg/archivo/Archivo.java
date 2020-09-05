package com.franperu.tfg.archivo;

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
@Table(name = "archivo")
public class Archivo {

	@Id
	@Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
    @Column(name = "tipo")
    private String tipo;
	
	@NotNull
    @Column(name = "link")
    private String link;
	
	@NotNull
    @Column(name = "idRecuerdo")
    private Long idRecuerdo;
	
	@NotNull
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
	
	public Archivo() {
		
	}
	
	public Archivo(String tipo, String link, Long idRecuerdo, Usuario usuario) {
		this.tipo = tipo;
		this.link = link;
		this.idRecuerdo = idRecuerdo;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Long getIdRecuerdo() {
		return idRecuerdo;
	}

	public void setIdRecuerdo(Long idRecuerdo) {
		this.idRecuerdo = idRecuerdo;
	}
}
