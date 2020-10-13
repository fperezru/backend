package com.franperu.tfg.mascotas;

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
import com.franperu.tfg.personas.Persona;


@RestController
@RequestMapping("/api/mascotas")
@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.0.21:4200"})
public class MascotaController {

	@Autowired
	MascotaService mascotaService;
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/lista")
    public ResponseEntity<List<Mascota>> getLista(){
        List<Mascota> lista = mascotaService.obtenerMascotas();
        return new ResponseEntity<List<Mascota>>(lista, HttpStatus.OK);
    }
	
	@GetMapping("/lista/{id}")
    public ResponseEntity<List<Mascota>> getListaMacotas(@PathVariable Long id){
		Usuario usuario = new Usuario();
		usuario.setId(id);
		List<Mascota> lista = mascotaService.obtenerMascotasUsuario(usuario);
		return new ResponseEntity<List<Mascota>>(lista, HttpStatus.OK);
    }
	
	@GetMapping("/detalle/{id}")
	public ResponseEntity<Mascota> getOne(@PathVariable Long id){
	    if(!mascotaService.existePorId(id))
	       return new ResponseEntity(new Mensaje("no existe esa mascota"), HttpStatus.NOT_FOUND);
	        
	    Mascota mascota = mascotaService.obtenerPorId(id).get();
	    return new ResponseEntity<Mascota>(mascota, HttpStatus.OK);
	}
	
	@PostMapping("/nuevo/{id}")
	public ResponseEntity<?> create(@RequestBody Mascota mascota, @PathVariable Long id) {
		Optional<Usuario> usuarioOptional = usuarioService.getById(id);
		Usuario usuario = new Usuario();
		usuario = usuarioOptional.get();
		mascota.setUsuario(usuario);
		mascotaService.guardar(mascota);
		return new ResponseEntity<Mascota>(mascota, HttpStatus.OK);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> update(@RequestBody Mascota mascota, @PathVariable("id") Long id) {
		Mascota mascotaUpdate = mascotaService.obtenerPorId(id).get();
		mascotaUpdate.setNombre(mascota.getNombre());
		mascotaUpdate.setEspecie(mascota.getEspecie());
		mascotaUpdate.setDescripcion(mascota.getDescripcion());
		mascotaService.guardar(mascotaUpdate);
		return new ResponseEntity(new Mensaje("mascota actualizada"), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/borrar/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if(!mascotaService.existePorId(id))
			return new ResponseEntity(new Mensaje("no existe ese mascota"), HttpStatus.NOT_FOUND);
		mascotaService.borrar(id);
		
		return new ResponseEntity(new Mensaje("mascota eliminada"), HttpStatus.OK);
	}
}
