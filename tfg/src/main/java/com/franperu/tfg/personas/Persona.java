package com.franperu.tfg.personas;



import java.util.Date;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.franperu.tfg.login.Usuario;
import com.sun.istack.NotNull;

@Entity
@Table(name = "personas")
public class Persona {
	
	@Id
	@Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
    @Column(name = "nombre")
    private String nombre;
	
	@NotNull
    @Column(name = "apellido1")
    private String apellido1;
	
	@NotNull
    @Column(name = "apellido2")
    private String apellido2;
	
	@NotNull
    @Column(name = "relacion")
    private String relacion;
	
    @Column(name = "descripcion")
    private String descripcion;
	
	@NotNull
    @Column(name = "cumpleaños")
    private Date fecha_nacimiento;
	
    
    @NotNull
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    
	public Persona() {
		
	}
	
	public Persona(String nombre, Usuario usuario) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.relacion = relacion;
		this.descripcion = descripcion;
		this.fecha_nacimiento = fecha_nacimiento;
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
	
	public String getApellido1() {
		return this.apellido1;
	}
	
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	
	public String getApellido2() {
		return this.apellido2;
	}
	
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	
	public String getRelacion() {
		return this.relacion;
	}
	
	public void setRelacion(String relacion) {
		this.relacion = relacion;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Date getFechaNacimiento() {
		return this.fecha_nacimiento;
	}
	
	public void setFechaNacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}	
}
