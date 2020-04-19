package com.franperu.tfg.personas;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.franperu.tfg.login.Usuario;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
	
	//Optional<Persona> findPersona(Long id);
	
	//boolean existPersona(Persona p);
	List<Persona>findByUsuario(Usuario usuario);
}
