package com.franperu.tfg.fileUpload;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.franperu.tfg.DTO.Mensaje;
import com.franperu.tfg.fileUpload.UploadFileService;

@RestController
@RequestMapping("/api/upload")
@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.1.38:4200"})
public class UploadFileController {
	
	@Autowired
	private UploadFileService uploadFileService;
	
	@PostMapping("/file")
	public ResponseEntity<?> subirArchivo(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return new ResponseEntity<Object>(new Mensaje("Seleccionar un archvo"), HttpStatus.OK);
		}
		
		try {
			uploadFileService.guardarArchivo(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Object>(new Mensaje("Archivo subido correctamente"), HttpStatus.OK);
	}
}
