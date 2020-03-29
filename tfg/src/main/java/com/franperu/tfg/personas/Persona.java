package com.franperu.tfg.personas;



import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
    @Column(name = "fecha_nacimiento")
    private Date fecha_nacimiento;
	
    @Column(name = "fecha_defuncion")
    private Date fecha_defuncion;
	
	public Persona() {
		
	}
	
	public Persona(String nombre, String apellido1, String apellido2, String relacion, String descripcion, String imagen1, String imagen2, String imagen3, String imagen4, String video1, String video2, Date fecha_nacimiento, Date fecha_defuncion) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.relacion = relacion;
		this.descripcion = descripcion;
		this.imagen1 = imagen1;
		this.imagen2 = imagen2;
		this.imagen3 = imagen3;
		this.imagen4 = imagen4;
		this.video1 = video1;
		this.video2 = video2;
		this.fecha_nacimiento = fecha_nacimiento;
		this.fecha_defuncion = fecha_defuncion;
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
	
	public Date getFechaNacimiento() {
		return this.fecha_nacimiento;
	}
	
	public void setFechaNacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	
	public Date getFechaDefuncion() {
		return this.fecha_defuncion;
	}
	
	public void setFechaDefuncion(Date fecha_defuncion) {
		this.fecha_defuncion = fecha_defuncion;
	}
	
}
