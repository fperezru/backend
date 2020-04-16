package com.franperu.tfg.otros_recuerdos;

import java.util.List;

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

@RestController
@RequestMapping("/api/otros")
@CrossOrigin(origins = "http://localhost:4200")
public class OtroRecuerdoController {
	
	@Autowired
	OtroRecuerdoService otrosRecuerdosService;
	
	@GetMapping("/lista")
    public ResponseEntity<List<OtroRecuerdo>> getLista(){
        List<OtroRecuerdo> lista = otrosRecuerdosService.obtenerMascotas();
        return new ResponseEntity<List<OtroRecuerdo>>(lista, HttpStatus.OK);
    }
	
	@GetMapping("/detalle/{id}")
	public ResponseEntity<OtroRecuerdo> getOne(@PathVariable Long id){
	    if(!otrosRecuerdosService.existePorId(id))
	       return new ResponseEntity(new Mensaje("no existe ese recuerdo"), HttpStatus.NOT_FOUND);
	        
	    OtroRecuerdo recuerdo = otrosRecuerdosService.obtenerPorId(id).get();
	    return new ResponseEntity<OtroRecuerdo>(recuerdo, HttpStatus.OK);
	}
	
	@PostMapping("/nuevo")
	public ResponseEntity<?> create(@RequestBody OtroRecuerdo recuerdo) {
		otrosRecuerdosService.guardar(recuerdo);
		return new ResponseEntity(new Mensaje("recuerdo guardado"), HttpStatus.CREATED);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> update(@RequestBody OtroRecuerdo recuerdo, @PathVariable("id") Long id) {
		OtroRecuerdo recuerdoUpdate = otrosRecuerdosService.obtenerPorId(id).get();
		recuerdoUpdate.setTipo(recuerdo.getTipo());
		recuerdoUpdate.setDescripcion(recuerdo.getDescripcion());
		recuerdoUpdate.setImagen1(recuerdo.getImagen1());
		recuerdoUpdate.setImagen2(recuerdo.getImagen2());
		recuerdoUpdate.setImagen3(recuerdo.getImagen3());
		recuerdoUpdate.setImagen4(recuerdo.getImagen4());
		recuerdoUpdate.setVideo1(recuerdo.getVideo1());
		recuerdoUpdate.setVideo2(recuerdo.getVideo2());
		otrosRecuerdosService.guardar(recuerdoUpdate);
		return new ResponseEntity(new Mensaje("recuerdo actualizado"), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/borrar/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if(!otrosRecuerdosService.existePorId(id))
			return new ResponseEntity(new Mensaje("no existe ese recuerdo"), HttpStatus.NOT_FOUND);
		otrosRecuerdosService.borrar(id);
		
		return new ResponseEntity(new Mensaje("recuerdo eliminado"), HttpStatus.OK);
	}

}
