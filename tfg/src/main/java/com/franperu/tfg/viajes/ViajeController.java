package com.franperu.tfg.viajes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.franperu.tfg.DTO.Mensaje;


@RestController
@RequestMapping("/api/viajes")
public class ViajeController {

	@Autowired
	ViajeService viajeService;
	
	@GetMapping("/lista")
    public ResponseEntity<List<Viaje>> getLista(){
        List<Viaje> lista = viajeService.obtenerMascotas();
        return new ResponseEntity<List<Viaje>>(lista, HttpStatus.OK);
    }
	
	@GetMapping("/detalle/{id}")
	public ResponseEntity<Viaje> getOne(@PathVariable Long id){
	    if(!viajeService.existePorId(id))
	       return new ResponseEntity(new Mensaje("no existe ese viaje"), HttpStatus.NOT_FOUND);
	        
	    Viaje viaje = viajeService.obtenerPorId(id).get();
	    return new ResponseEntity<Viaje>(viaje, HttpStatus.OK);
	}
	
	@PostMapping("/nuevo")
	public ResponseEntity<?> create(@RequestBody Viaje viaje) {
		viajeService.guardar(viaje);
		return new ResponseEntity(new Mensaje("viaje guardado"), HttpStatus.CREATED);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> update(@RequestBody Viaje viaje, @PathVariable("id") Long id) {
		Viaje viajeUpdate = viajeService.obtenerPorId(id).get();
		viajeUpdate.setLugar(viaje.getLugar());
		viajeUpdate.setDescripcion(viaje.getDescripcion());
		viajeUpdate.setImagen1(viaje.getImagen1());
		viajeUpdate.setImagen2(viaje.getImagen2());
		viajeUpdate.setImagen3(viaje.getImagen3());
		viajeUpdate.setImagen4(viaje.getImagen4());
		viajeUpdate.setVideo1(viaje.getVideo1());
		viajeUpdate.setVideo2(viaje.getVideo2());
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
