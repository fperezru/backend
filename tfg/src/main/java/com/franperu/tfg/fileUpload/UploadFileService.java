package com.franperu.tfg.fileUpload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class UploadFileService {
	
	private String ruta = "./src/main/resources/files/";
	
	public void guardarArchivo(MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(ruta + file.getOriginalFilename());
			Files.write(path, bytes);
		}
	}
}
