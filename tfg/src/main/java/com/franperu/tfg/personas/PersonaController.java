package com.franperu.tfg.personas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.franperu.tfg.DTO.Mensaje;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
//@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {

	@Autowired
	PersonaService personaService;
	
	@GetMapping("/lista")
    public ResponseEntity<List<Persona>> getLista(){
        List<Persona> lista = personaService.obtenerPersonas();
        return new ResponseEntity<List<Persona>>(lista, HttpStatus.OK);
    }
	
	@GetMapping("/detalle/{id}")
	public ResponseEntity<Persona> getOne(@PathVariable Long id){
	    if(!personaService.existePorId(id))
	       return new ResponseEntity(new Mensaje("no existe esa persona"), HttpStatus.NOT_FOUND);
	        
	    Persona persona = personaService.obtenerPorId(id).get();
	    return new ResponseEntity<Persona>(persona, HttpStatus.OK);
	}
	
	@PostMapping("/nuevo")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> create(@RequestBody Persona persona) {
		personaService.guardar(persona);
		return new ResponseEntity(new Mensaje("persona guardada"), HttpStatus.CREATED);
	}
	
	@PutMapping("/actualizar/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> update(@RequestBody Persona persona, @PathVariable("id") Long id) {
		Persona personaUpdate = personaService.obtenerPorId(id).get();
		personaUpdate.setNombre(persona.getNombre());
		personaUpdate.setApellido1(persona.getApellido1());
		personaUpdate.setApellido2(persona.getApellido2());
		personaUpdate.setRelacion(persona.getRelacion());
		personaUpdate.setDescripcion(persona.getDescripcion());
		personaUpdate.setImagen1(persona.getImagen1());
		personaUpdate.setImagen2(persona.getImagen2());
		personaUpdate.setImagen3(persona.getImagen3());
		personaUpdate.setImagen4(persona.getImagen4());
		personaUpdate.setVideo1(persona.getVideo1());
		personaUpdate.setVideo2(persona.getVideo2());
		personaUpdate.setFechaNacimiento(persona.getFechaNacimiento());
		personaUpdate.setFechaDefuncion(persona.getFechaDefuncion());
		personaService.guardar(personaUpdate);
		return new ResponseEntity(new Mensaje("persona actualizada"), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/borrar/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if(!personaService.existePorId(id))
			return new ResponseEntity(new Mensaje("no existe ese persona"), HttpStatus.NOT_FOUND);
		personaService.borrar(id);
		
		return new ResponseEntity(new Mensaje("persona eliminada"), HttpStatus.OK);
	}
	
}
