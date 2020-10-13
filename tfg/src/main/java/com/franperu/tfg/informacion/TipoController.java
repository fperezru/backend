package com.franperu.tfg.informacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tipo")
@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.0.21:4200"})
public class TipoController {

	@Autowired
	TipoService tipoService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<Tipo>> getLista() {
		List<Tipo> lista = tipoService.obtenerTipos();
		return new ResponseEntity<List<Tipo>>(lista, HttpStatus.OK);
	}
	
}
