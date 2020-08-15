package com.franperu.tfg.informacion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.franperu.tfg.enums.TipoNombre;

@Service
@Transactional
public class InformacionService {

	@Autowired
	InformacionRepository informacionRepository;
	
	public List<Informacion> obtenerInformaciones() {
		List<Informacion> lista_informacion = informacionRepository.findAll();
		return lista_informacion;
	}
	
	public List<Informacion> obtenerInformacionesPorTipo(Tipo tipo) {
		List<Informacion> lista_informacion = informacionRepository.findByTipo(tipo);
		return lista_informacion;
	}
	
	public Optional<Informacion> obtenerPorId(Long id) {
		return informacionRepository.findById(id);
	}
	
	public void guardar(Informacion informacion) {
		informacionRepository.save(informacion);
	}
	
	public void borrar(Long id) {
		informacionRepository.deleteById(id);
	}
	
	public boolean existePorId(Long id) {
		return informacionRepository.existsById(id);
	}
}
