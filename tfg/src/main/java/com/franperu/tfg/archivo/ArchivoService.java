package com.franperu.tfg.archivo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.franperu.tfg.login.Usuario;

@Service
@Transactional
public class ArchivoService {

	@Autowired
	ArchivoRepository archivoRepository;
	
	public List<Archivo> obtenerMascotasUsuario(Usuario usuario) {
		List<Archivo> lista_archivos = archivoRepository.findArchivosMascota(usuario);
		return lista_archivos;
	}
	
	public List<Archivo> obtenerPersonasUsuario(Usuario usuario) {
		List<Archivo> lista_archivos = archivoRepository.findArchivosPersona(usuario);
		return lista_archivos;
	}
	
	public List<Archivo> obtenerViajessUsuario(Usuario usuario) {
		List<Archivo> lista_archivos = archivoRepository.findArchivosViaje(usuario);
		return lista_archivos;
	}
	
	public List<Archivo> obtenerOtrosUsuario(Usuario usuario) {
		List<Archivo> lista_archivos = archivoRepository.findArchivosOtros(usuario);
		return lista_archivos;
	}
	
	public void guardar(Archivo archivo){
        archivoRepository.save(archivo);
    }
}
