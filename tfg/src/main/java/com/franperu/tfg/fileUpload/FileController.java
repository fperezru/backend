package com.franperu.tfg.fileUpload;

import java.io.IOException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.franperu.tfg.DTO.Mensaje;
import com.franperu.tfg.archivo.Archivo;
import com.franperu.tfg.archivo.ArchivoService;
import com.franperu.tfg.login.Usuario;
import com.franperu.tfg.login.UsuarioService;

@RestController
@RequestMapping("/api/upload")
@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.0.21:4200"})
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;
    
    @Autowired
    UsuarioService usuarioService;
    
    @Autowired
    ArchivoService archivoService;
    
    @PostMapping("/uploadFile/{tipo}/{idUsuario}/{idRecuerdo}")
    public Archivo uploadFile(@RequestParam("file") MultipartFile file, @PathVariable("tipo") String tipo, @PathVariable("idUsuario") Long idUsuario, @PathVariable("idRecuerdo") Long idRecuerdo ) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/upload/downloadFile/")
                .path(fileName)
                .toUriString();
        
        Usuario usuario = new Usuario();
        usuario = usuarioService.getById(idUsuario).get();
        
        Archivo archivo = new Archivo(tipo, fileDownloadUri, idRecuerdo, usuario);
        archivoService.guardar(archivo);
        return archivo;
    }

    @PostMapping("/uploadMultipleFiles/{tipo}/{idUsuario}/{idRecuerdo}")
    public List<Archivo> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files, @PathVariable("tipo") String tipo, @PathVariable("idUsuario") Long idUsuario, @PathVariable("idRecuerdo") Long idRecuerdo ) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file, tipo, idUsuario, idRecuerdo))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
