package com.franperu.tfg.mascotas;

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
import com.franperu.tfg.login.Usuario;

@Entity
@Table(name = "mascotas")
public class Mascota {
	
	@Id
	@Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
    @Column(name = "nombre")
    private String nombre;
	
	@NotNull
	@Column(name = "especie")
	private String especie;
	
	@Column(name = "descripcion")
    private String descripcion;
	
    @Column(name = "imagen1")
    private String imagen1;
	
    @Column(name = "imagen2")
    private String imagen2;
	
    @Column(name = "imagen3")
    private String imagen3;
	
    @Column(name = "imagen4")
    private String imagen4;
	
    @Column(name = "video1")
    private String video1;
	
    @Column(name = "video2")
    private String video2;
    
    @NotNull
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    
    public Mascota() {
    	
    }
    
    public Mascota(String nombre, String especie, String descripcion, String imagen1, String imagen2, String imagen3, String imagen4, String video1, String video2, Usuario usuario) {
    	this.nombre = nombre;
    	this.especie = especie;
		this.descripcion = descripcion;
		this.imagen1 = imagen1;
		this.imagen2 = imagen2;
		this.imagen3 = imagen3;
		this.imagen4 = imagen4;
		this.video1 = video1;
		this.video2 = video2;
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
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getEspecie() {
		return this.especie;
	}
	
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getImagen1() {
		return this.imagen1;
	}
	
	public void setImagen1(String imagen1) {
		this.imagen1 = imagen1;
	}
	
	public String getImagen2() {
		return this.imagen2;
	}
	
	public void setImagen2(String imagen2) {
		this.imagen2 = imagen2;
	}
	
	public String getImagen3() {
		return this.imagen3;
	}
	
	public void setImagen3(String imagen3) {
		this.imagen3 = imagen3;
	}
	
	public String getImagen4() {
		return this.imagen4;
	}
	
	public void setImagen4(String imagen4) {
		this.imagen4 = imagen4;
	}
	
	public String getVideo1() {
		return this.video1;
	}
	
	public void setVideo1(String video1) {
		this.video1 = video1;
	}
	
	public String getVideo2() {
		return this.video2;
	}
	
	public void setVideo2(String video2) {
		this.video2 = video2;
	}
}
