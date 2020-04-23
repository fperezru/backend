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
import com.franperu.tfg.login.Usuario;
import com.franperu.tfg.login.UsuarioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personas")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {

	@Autowired
	PersonaService personaService;
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/lista")
    public ResponseEntity<List<Persona>> getLista(){
        List<Persona> lista = personaService.obtenerPersonas();
        return new ResponseEntity<List<Persona>>(lista, HttpStatus.OK);
    }
	
	@GetMapping("/lista/{id}")
    public ResponseEntity<List<Persona>> getListaPersonas(@PathVariable Long id){
		Usuario usuario = new Usuario();
		usuario.setId(id);
        List<Persona> lista = personaService.obtenerPersonasUsuario(usuario);
        return new ResponseEntity<List<Persona>>(lista, HttpStatus.OK);
    }
	
	@GetMapping("/detalle/{id}")
	public ResponseEntity<Persona> getOne(@PathVariable Long id){
	    if(!personaService.existePorId(id))
	       return new ResponseEntity(new Mensaje("no existe esa persona"), HttpStatus.NOT_FOUND);
	        
	    Persona persona = personaService.obtenerPorId(id).get();
	    return new ResponseEntity<Persona>(persona, HttpStatus.OK);
	}
	
	@PostMapping("/nuevo/{id}")
	public ResponseEntity<?> create(@RequestBody Persona persona, @PathVariable Long id) {
		Optional<Usuario> usuarioOptional = usuarioService.getById(id);
		Usuario usuario = new Usuario();
		usuario = usuarioOptional.get();
		persona.setUsuario(usuario);
		personaService.guardar(persona);
		return new ResponseEntity(new Mensaje("persona guardada"), HttpStatus.CREATED);
	}
	
	@PutMapping("/actualizar/{id}")
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
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if(!personaService.existePorId(id))
			return new ResponseEntity(new Mensaje("no existe ese persona"), HttpStatus.NOT_FOUND);
		personaService.borrar(id);
		
		return new ResponseEntity(new Mensaje("persona eliminada"), HttpStatus.OK);
	}
	
}
