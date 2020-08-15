package com.franperu.tfg.localizacion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.franperu.tfg.login.Usuario;

@Service
@Transactional
public class LocalizacionService {

	@Autowired
	LocalizacionRepository localizacionRepository;
	
	public List<Localizacion> obtenerLocalizaciones() {
		List<Localizacion> lista_localizaciones = localizacionRepository.findAll();
		return lista_localizaciones;
	}
	
	public List<Localizacion> obtenerLocalizacionesUsuario(Usuario usuario) {
		List<Localizacion> lista_localizaciones = localizacionRepository.findByUsuario(usuario);
		return lista_localizaciones;
	}
	
	public Optional<Localizacion> obtenerPorId(Long id) {
		return localizacionRepository.findById(id);
	}
	
	public void guardar(Localizacion localizacion) {
		localizacionRepository.save(localizacion);
	}
	
	public void borrar(Long id) {
		localizacionRepository.deleteById(id);
	}
	
	public boolean existePorId(Long id) {
		return localizacionRepository.existsById(id);
	}
	
	
}
