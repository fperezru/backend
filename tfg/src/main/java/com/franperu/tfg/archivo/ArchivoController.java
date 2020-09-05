package com.franperu.tfg.archivo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.franperu.tfg.login.Usuario;
import com.franperu.tfg.login.UsuarioService;

@RestController
@RequestMapping("/api/archivos")
@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.1.34:4200"})
public class ArchivoController {

	@Autowired
	ArchivoService archivoService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/listaPersonas/{id}")
	public ResponseEntity<List<Archivo>> getListaPersonas( @PathVariable("id") Long id) {
		Usuario usuario = new Usuario();
		usuario = usuarioService.getById(id).get();
		
		List<Archivo> lista = archivoService.obtenerPersonasUsuario(usuario);
		return new ResponseEntity<List<Archivo>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/listaMascotas/{id}")
	public ResponseEntity<List<Archivo>> getListaMascotas( @PathVariable("id") Long id) {
		Usuario usuario = new Usuario();
		usuario = usuarioService.getById(id).get();
		
		List<Archivo> lista = archivoService.obtenerMascotasUsuario(usuario);
		return new ResponseEntity<List<Archivo>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/listaViajes/{id}")
	public ResponseEntity<List<Archivo>> getListaViajes( @PathVariable("id") Long id) {
		Usuario usuario = new Usuario();
		usuario = usuarioService.getById(id).get();
		
		List<Archivo> lista = archivoService.obtenerViajessUsuario(usuario);
		return new ResponseEntity<List<Archivo>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/listaOtros/{id}")
	public ResponseEntity<List<Archivo>> getListaOtros( @PathVariable("id") Long id) {
		Usuario usuario = new Usuario();
		usuario = usuarioService.getById(id).get();
		
		List<Archivo> lista = archivoService.obtenerOtrosUsuario(usuario);
		return new ResponseEntity<List<Archivo>>(lista, HttpStatus.OK);
	}
}
