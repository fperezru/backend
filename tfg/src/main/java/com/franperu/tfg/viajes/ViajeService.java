package com.franperu.tfg.viajes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ViajeService {

	@Autowired
	ViajeRepository viajeRepository;
	
	public List<Viaje> obtenerMascotas() {
		List<Viaje> lista_mascotas = viajeRepository.findAll();
		return lista_mascotas;
	}
	
	public Optional<Viaje> obtenerPorId(Long id) {
		return viajeRepository.findById(id);
	}
	
	public void guardar(Viaje viaje) {
		viajeRepository.save(viaje);
	}
	
	public void borrar(Long id) {
		viajeRepository.deleteById(id);
	}
	
	public boolean existePorId(Long id) {
		return viajeRepository.existsById(id);
	}
}
