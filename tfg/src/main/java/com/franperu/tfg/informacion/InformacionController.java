package com.franperu.tfg.informacion;

import java.util.HashSet;
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
import com.franperu.tfg.enums.TipoNombre;
import com.franperu.tfg.login.Usuario;
import com.franperu.tfg.login.UsuarioService;

@RestController
@RequestMapping("/api/informacion")
@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.1.34:4200"})
public class InformacionController {
	
	@Autowired
	InformacionService informacionService;
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	TipoService tipoService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<Informacion>> getLista() {
		List<Informacion> lista = informacionService.obtenerInformaciones();
		System.out.println(lista.get(0).getTipo().getTipoNombre());
		System.out.println(lista.get(0).getUsuario().getNombreUsuario());
		return new ResponseEntity<List<Informacion>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/lista/{tipo}")
	public ResponseEntity<List<Informacion>> getListaTipo(@PathVariable Long tipo) {
		Tipo p = new Tipo();
		p.setId(tipo);
		List<Informacion> lista = informacionService.obtenerInformacionesPorTipo(p);
		return new ResponseEntity<List<Informacion>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/detalle/{id}")
	public ResponseEntity<Informacion> getOne(@PathVariable Long id) {
		if(!informacionService.existePorId(id))
			return new ResponseEntity(new Mensaje("no existe esa informacion"), HttpStatus.NOT_FOUND);
		
		Informacion informacion = informacionService.obtenerPorId(id).get();
		return new ResponseEntity<Informacion>(informacion, HttpStatus.OK);
	}
	
	@PostMapping("/nuevo/{id}")
	public ResponseEntity<?> create (@RequestBody Informacion informacion, @PathVariable Long id) {
		Optional<Usuario> usuarioOptional = usuarioService.getById(id);
		Usuario usuario = new Usuario();
		usuario = usuarioOptional.get();
		informacion.setUsuario(usuario);
		Tipo t = new Tipo();
		t.setId(informacion.getTipo().getId());
		informacion.setTipo(t);
		informacionService.guardar(informacion);
		return new ResponseEntity(new Mensaje("informacion guardada"), HttpStatus.CREATED);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> update(@RequestBody Informacion informacion, @PathVariable Long id) {
		Informacion informacionUpdate = informacionService.obtenerPorId(id).get();
		informacionUpdate.setTitulo(informacion.getTitulo());
		informacionUpdate.setContenido(informacion.getContenido());
		informacionUpdate.setTipo(informacion.getTipo());
		informacionUpdate.setUsuario(informacion.getUsuario());
		informacionService.guardar(informacion);
		return new ResponseEntity(new Mensaje("informacion actualizada"), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/borrar/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if(!informacionService.existePorId(id))
			return new ResponseEntity(new Mensaje("no existe esa informacion"), HttpStatus.NOT_FOUND);
		informacionService.borrar(id);
		
		return new ResponseEntity(new Mensaje("informacion eliminada"), HttpStatus.OK);
	}
}
