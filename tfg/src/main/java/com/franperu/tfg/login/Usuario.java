package com.franperu.tfg.login;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.franperu.tfg.diario.Diario;
import com.franperu.tfg.informacion.EntityIdResolver;
import com.franperu.tfg.informacion.Informacion;
import com.franperu.tfg.informacion.Tipo;
import com.franperu.tfg.mascotas.Mascota;
import com.franperu.tfg.otros_recuerdos.OtroRecuerdo;
import com.franperu.tfg.personas.Persona;
import com.franperu.tfg.viajes.Viaje;

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
    private String password;
    
    private Long familiar;
    
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Rol> roles = new HashSet<>();
    
    @JsonManagedReference
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Persona> personas;

    @JsonManagedReference
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Mascota> mascotas;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Viaje> viajes;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OtroRecuerdo> otros_recuerdos;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Diario> paginas;
    
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Informacion> informaciones =  new HashSet<>();;

    public Usuario() {
    }

    public Usuario(@NotNull String nombre, @NotNull String nombreUsuario, @NotNull String email, @NotNull String password, Long familiar) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.familiar = familiar;
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
    
}