package com.franperu.tfg.localizacion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/api/localizacion")
@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.1.34:4200"})
public class LocalizacionController {
	
	@Autowired
	LocalizacionService localizacionService;
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<Localizacion>> getLista() {
		List<Localizacion> lista = localizacionService.obtenerLocalizaciones();
		return new ResponseEntity<List<Localizacion>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/lista/{id}")
	public ResponseEntity<List<Localizacion>> getListaLocalizaciones(@PathVariable Long id) {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		List<Localizacion> lista = localizacionService.obtenerLocalizacionesUsuario(usuario);
		return new ResponseEntity<List<Localizacion>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/detalle/{id}")
	public ResponseEntity<Localizacion> getOne(@PathVariable Long id) {
		if(!localizacionService.existePorId(id))
			return new ResponseEntity(new Mensaje("no existe esa localizacion"), HttpStatus.NOT_FOUND);
		
		Localizacion localizacion = localizacionService.obtenerPorId(id).get();
		return new ResponseEntity<Localizacion>(localizacion, HttpStatus.OK);
	}
	
	@PostMapping("/nuevo/{id}")
	public ResponseEntity<?> create(@RequestBody Localizacion localizacion, @PathVariable Long id) {
		Optional<Usuario> usuarioOptional = usuarioService.getById(id);
		Usuario usuario = new Usuario();
		usuario = usuarioOptional.get();
		localizacion.setUsuario(usuario);
		localizacionService.guardar(localizacion);
		return new ResponseEntity(new Mensaje("localizacion guardada"), HttpStatus.CREATED);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> update(@RequestBody Localizacion localizacion, @PathVariable("id") Long id) {
		Localizacion localizacionUpdate = localizacionService.obtenerPorId(id).get();
		localizacionUpdate.setLatitud(localizacion.getLatitud());
		localizacionUpdate.setLongitud(localizacion.getLongitud());
		localizacionService.guardar(localizacionUpdate);
		return new ResponseEntity(new Mensaje("localizacion actualizada"), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/borrar/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if(!localizacionService.existePorId(id))
			return new ResponseEntity(new Mensaje("no existe esa localizacion"), HttpStatus.NOT_FOUND);
		localizacionService.borrar(id);
		
		return new ResponseEntity(new Mensaje("localizacion eliminada"), HttpStatus.OK);
	}
	
	

}
