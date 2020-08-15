package com.franperu.tfg.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.franperu.tfg.DTO.Mensaje;


@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.1.38:4200"})
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/lista")
	public ResponseEntity<List<Usuario>> getLista() {
		List<Usuario> lista = usuarioService.obtenerUsusarios();
		return new ResponseEntity<List<Usuario>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/lista/{rol}")
	public ResponseEntity<List<Usuario>> getListaFamiliares( @PathVariable("rol") Long rol) {
		Rol r = new Rol();
		r.setId(rol);
		List<Usuario> lista = usuarioService.obtenerFamiliares(r);
		return new ResponseEntity<List<Usuario>>(lista, HttpStatus.OK);
	}
	
	@GetMapping(value= {"/familiar/{familiar}"})
	public ResponseEntity<List<Usuario>> getListaPacientes(Long familiar) {
		List<Usuario> lista = usuarioService.obtenerPacientes(familiar);
		return new ResponseEntity<List<Usuario>>(lista, HttpStatus.OK);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> update(@RequestBody Usuario usuario, @PathVariable("id") Long id) {
		Usuario usuarioUpdate = usuarioService.getById(id).get();
		usuarioUpdate.setNombre(usuario.getNombre());
		usuarioUpdate.setNombreUsuario(usuario.getNombreUsuario());
		usuarioUpdate.setApellidos(usuario.getApellidos());
		usuarioUpdate.setIdentificacion(usuario.getIdentificacion());
		usuarioUpdate.setFechaNacimiento(usuario.getFechaNacimiento());
		usuarioUpdate.setDomicilio(usuario.getDomicilio());
		usuarioUpdate.setTelefono(usuario.getTelefono());
		usuarioUpdate.setEmail(usuario.getEmail());
		usuarioUpdate.setPermiso(usuario.getPermiso());
		usuarioUpdate.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuarioService.guardar(usuarioUpdate);
		return new ResponseEntity(new Mensaje("usuario actualizado"), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/borrar/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if(!usuarioService.existePorId(id))
			return new ResponseEntity(new Mensaje("no existe ese usuario"), HttpStatus.NOT_FOUND);
		usuarioService.borrar(id);
		
		return new ResponseEntity(new Mensaje("usuario eliminado"), HttpStatus.OK);
	}
}
