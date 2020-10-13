package com.franperu.tfg.viajes;

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
@RequestMapping("/api/viajes")
@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.0.21:4200"})
public class ViajeController {

	@Autowired
	ViajeService viajeService;
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/lista")
    public ResponseEntity<List<Viaje>> getLista(){
        List<Viaje> lista = viajeService.obtenerViajes();
        return new ResponseEntity<List<Viaje>>(lista, HttpStatus.OK);
    }
	
	@GetMapping("/lista/{id}")
    public ResponseEntity<List<Viaje>> getListaViajes(@PathVariable Long id){
		Usuario usuario = new Usuario();
		usuario.setId(id);
        List<Viaje> lista = viajeService.obtenerViajesUsuario(usuario);
        return new ResponseEntity<List<Viaje>>(lista, HttpStatus.OK);
    }
	
	@GetMapping("/detalle/{id}")
	public ResponseEntity<Viaje> getOne(@PathVariable Long id){
	    if(!viajeService.existePorId(id))
	       return new ResponseEntity(new Mensaje("no existe ese viaje"), HttpStatus.NOT_FOUND);
	        
	    Viaje viaje = viajeService.obtenerPorId(id).get();
	    return new ResponseEntity<Viaje>(viaje, HttpStatus.OK);
	}
	
	@GetMapping("/detalles/{lugar}")
	public ResponseEntity<Viaje> getOneLugar(@PathVariable String lugar){
	        
	    Viaje viaje = viajeService.obtenerPorLugar(lugar).get();
	    return new ResponseEntity<Viaje>(viaje, HttpStatus.OK);
	}
	
	@PostMapping("/nuevo/{id}")
	public ResponseEntity<?> create(@RequestBody Viaje viaje, @PathVariable Long id) {
		Optional<Usuario> usuarioOptional = usuarioService.getById(id);
		Usuario usuario = new Usuario();
		usuario = usuarioOptional.get();
		viaje.setUsuario(usuario);
		viajeService.guardar(viaje);
		return new ResponseEntity<Viaje>(viaje, HttpStatus.CREATED);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> update(@RequestBody Viaje viaje, @PathVariable("id") Long id) {
		Viaje viajeUpdate = viajeService.obtenerPorId(id).get();
		viajeUpdate.setLugar(viaje.getLugar());
		viajeUpdate.setDescripcion(viaje.getDescripcion());
		viajeService.guardar(viajeUpdate);
		return new ResponseEntity(new Mensaje("viaje actualizado"), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/borrar/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if(!viajeService.existePorId(id))
			return new ResponseEntity(new Mensaje("no existe ese mascota"), HttpStatus.NOT_FOUND);
		viajeService.borrar(id);
		
		return new ResponseEntity(new Mensaje("viaje eliminado"), HttpStatus.OK);
	}
}
