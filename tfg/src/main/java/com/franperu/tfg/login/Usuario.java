package com.franperu.tfg.login;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.franperu.tfg.archivo.Archivo;
import com.franperu.tfg.diario.Diario;
import com.franperu.tfg.informacion.EntityIdResolver;
import com.franperu.tfg.informacion.Informacion;
import com.franperu.tfg.informacion.Tipo;
import com.franperu.tfg.localizacion.Localizacion;
import com.franperu.tfg.mascotas.Mascota;
import com.franperu.tfg.otros_recuerdos.OtroRecuerdo;
import com.franperu.tfg.personas.Persona;
import com.franperu.tfg.viajes.Viaje;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
property = "id",
resolver = EntityIdResolver.class,
scope=Usuario.class)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    @Column(unique = true)
    private String nombreUsuario;

    @NotNull
    @Column(unique = true)
    private String email;
    
    @NotNull
	@Column(name = "apellidos")
	private String apellidos;
    
	@Column(name = "fecha_nacimiento")
	private Date fechaNacimiento;
    
    @NotNull
	@Column(name = "domicilio")
	private String domicilio;
    
    @NotNull
	@Column(name = "identificacion")
	private String identificacion;
    
    @NotNull
	@Column(name = "telefono")
	private String telefono;

    @NotNull
    private String password;
    
    private Long familiar;
    
	@Column(name = "permiso")
	private Boolean permiso;
    
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Rol> roles = new HashSet<>();
    
    @JsonManagedReference
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Persona> personas;

    @JsonManagedReference
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Mascota> mascotas;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Viaje> viajes;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<OtroRecuerdo> otros_recuerdos;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Diario> paginas;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Localizacion> localizaciones;
    
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Informacion> informaciones =  new HashSet<>();;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Archivo> archivos;

    public Usuario() {
    }

	public Usuario(@NotNull String nombre, @NotNull String nombreUsuario, @NotNull String apellidos, @NotNull String identificacion, Date fechaNacimiento, @NotNull String domicilio, @NotNull String telefono, @NotNull String email, @NotNull String password, Long familiar, Boolean permiso) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.apellidos = apellidos;
        this.identificacion = identificacion;
        this.fechaNacimiento = fechaNacimiento;
        this.domicilio = domicilio;       
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.familiar = familiar;
        this.permiso = permiso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Long getFamiliar() {
        return familiar;
    }

    public void setFamiliar(Long familiar) {
        this.familiar = familiar;
    }
    
    public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Boolean getPermiso() {
		return permiso;
	}

	public void setPermiso(Boolean permiso) {
		this.permiso = permiso;
	}

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
    
    public Set<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(Set<Persona> personas) {
        this.personas = personas;
    }
    
    public Set<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(Set<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
    
    public Set<Viaje> getViajess() {
        return viajes;
    }

    public void setViajes(Set<Viaje> viajes) {
        this.viajes = viajes;
    }
    
    public Set<OtroRecuerdo> getOtrosRecuerdos() {
        return otros_recuerdos;
    }

    public void setOtrosRecuerdos(Set<OtroRecuerdo> otros_recuerdos) {
        this.otros_recuerdos = otros_recuerdos;
    }
    
    public Set<Diario> getDiario() {
        return paginas;
    }

    public void setDiario(Set<Diario> paginas) {
        this.paginas = paginas;
    }
    
    public Set<Informacion> getInformaciones() {
        return informaciones;
    }

    public void setInformaciones(Set<Informacion> informaciones) {
        this.informaciones = informaciones;
    }
    
    public Set<Localizacion> getLocalizaciones() {
        return localizaciones;
    }

    public void setLocalizaciones(Set<Localizacion> localizaciones) {
        this.localizaciones = localizaciones;
    }

	public Set<Archivo> getArchivos() {
		return archivos;
	}

	public void setArchivos(Set<Archivo> archivos) {
		this.archivos = archivos;
	}   
}