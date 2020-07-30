package com.franperu.tfg.informacion;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.franperu.tfg.enums.TipoNombre;

@Entity
@Table(name = "tipo")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
property = "id",
resolver = EntityIdResolver.class,
scope=Tipo.class)
public class Tipo {

	@Id
	@Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    @NotNull
    private TipoNombre tipoNombre;
    
    @OneToMany(mappedBy = "tipo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Informacion> informaciones;

    public Tipo() {
    	
    }
    
    public Tipo(TipoNombre tipoNombre) {
        this.tipoNombre = tipoNombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoNombre getTipoNombre() {
        return tipoNombre;
    }

    public void setTipoNombre(TipoNombre tipoNombre) {
        this.tipoNombre = tipoNombre;
    }
    
    public Set<Informacion> getInformaciones() {
        return informaciones;
    }

    public void setInformaciones(Set<Informacion> informaciones) {
        this.informaciones = informaciones;
    }

}
