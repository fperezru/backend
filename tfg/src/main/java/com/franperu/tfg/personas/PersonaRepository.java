package com.franperu.tfg.personas;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
	
	//Optional<Persona> findPersona(Long id);
	
	//boolean existPersona(Persona p);
}
