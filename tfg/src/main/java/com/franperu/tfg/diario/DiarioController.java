package com.franperu.tfg.diario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/api/diario")
@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.1.34:4200"})
public class DiarioController {

	@Autowired
	DiarioService diarioService;
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/lista/{id}")
    public ResponseEntity<List<Diario>> getListaPaginas(@PathVariable Long id){
		Usuario usuario = new Usuario();
		usuario.setId(id);
		List<Diario> lista = diarioService.obtenerDiarioUsuario(usuario);
		return new ResponseEntity<List<Diario>>(lista, HttpStatus.OK);
    }
	
	@GetMapping("/detalle/{id}")
	public ResponseEntity<Diario> getOne(@PathVariable Long id){
	    if(!diarioService.existePorId(id))
	       return new ResponseEntity(new Mensaje("no existe esa página del diario"), HttpStatus.NOT_FOUND);
	        
	    Diario diario = diarioService.obtenerDiarioPorId(id).get();
	    return new ResponseEntity<Diario>(diario, HttpStatus.OK);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> update(@RequestBody Diario diario, @PathVariable Long id) {
		Diario diarioUpdate = diarioService.obtenerDiarioPorId(id).get();
		diarioUpdate.setTitulo(diario.getTitulo());
		diarioUpdate.setDiario(diario.getDiario());
		diarioService.guardar(diarioUpdate);
		return new ResponseEntity(new Mensaje("diario actualizado"), HttpStatus.CREATED);
	}
	
	@PostMapping("/nuevo/{id}")
	public ResponseEntity<?> create(@RequestBody Diario diario, @PathVariable Long id) {
		Optional<Usuario> usuarioOptional = usuarioService.getById(id);
		Usuario usuario = new Usuario();
		usuario = usuarioOptional.get();
		diario.setUsuario(usuario);
		diarioService.guardar(diario);
		return new ResponseEntity(new Mensaje("diario guardado"), HttpStatus.CREATED);
	}
}
