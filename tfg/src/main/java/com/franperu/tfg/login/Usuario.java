package com.franperu.tfg.login;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.franperu.tfg.mascotas.Mascota;
import com.franperu.tfg.personas.Persona;

import java.util.HashSet;
import java.util.Set;

@Entity
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
    

    @NotNull
    @ManyToMany
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();
    
    @NotNull
    @JsonManagedReference
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Persona> personas;
    
    @NotNull
    @JsonManagedReference
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Mascota> mascotas;

    public Usuario() {
    }

    public Usuario(@NotNull String nombre, @NotNull String nombreUsuario, @NotNull String email, @NotNull String password) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
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
    
}