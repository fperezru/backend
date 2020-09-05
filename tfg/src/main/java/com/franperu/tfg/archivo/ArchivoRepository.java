package com.franperu.tfg.archivo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.franperu.tfg.login.Usuario;

public interface ArchivoRepository extends JpaRepository<Archivo, Long> {

	@Query("SELECT a from Archivo a WHERE a.usuario = usuario AND a.tipo = 'mascota'")
	List<Archivo>findArchivosMascota(@Param("usuario") Usuario usuario);
	
	@Query("SELECT a from Archivo a WHERE a.usuario = usuario AND a.tipo = 'persona'")
	List<Archivo>findArchivosPersona(@Param("usuario") Usuario usuario);
	
	@Query("SELECT a from Archivo a WHERE a.usuario = usuario AND a.tipo = 'viaje'")
	List<Archivo>findArchivosViaje(@Param("usuario") Usuario usuario);
	
	@Query("SELECT a from Archivo a WHERE a.usuario = usuario AND a.tipo = 'otro'")
	List<Archivo>findArchivosOtros(@Param("usuario") Usuario usuario);
}
