package com.franperu.tfg.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<Usuario>> getLista() {
		List<Usuario> lista = usuarioService.obtenerUsusarios();
		return new ResponseEntity<List<Usuario>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/lista/{rol}")
	public ResponseEntity<List<Usuario>> getListaFamiliares(Rol rol) {
		List<Usuario> lista = usuarioService.obtenerFamiliares(rol);
		return new ResponseEntity<List<Usuario>>(lista, HttpStatus.OK);
	}
	
	@GetMapping(value= {"/familiar/{familiar}"})
	public ResponseEntity<List<Usuario>> getListaPacientes(Long familiar) {
		List<Usuario> lista = usuarioService.obtenerPacientes(familiar);
		return new ResponseEntity<List<Usuario>>(lista, HttpStatus.OK);
	}
}
