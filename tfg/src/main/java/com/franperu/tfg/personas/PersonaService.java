package com.franperu.tfg.personas;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonaService {

	@Autowired
	PersonaRepository personaRepository;
	
	public List<Persona> obtenerPersonas() {
		List<Persona> lista_personas = personaRepository.findAll();
		return lista_personas;
	}
	
	public Optional<Persona> obtenerPorId(Long id) {
		return personaRepository.findById(id);
	}
	
	public void guardar(Persona persona) {
		personaRepository.save(persona);
	}
	
	public void borrar(Long id) {
		personaRepository.deleteById(id);
	}
	
	public boolean existePorId(Long id) {
		return personaRepository.existsById(id);
	}
	
}
