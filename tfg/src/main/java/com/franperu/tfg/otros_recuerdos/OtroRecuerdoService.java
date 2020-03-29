package com.franperu.tfg.otros_recuerdos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class OtroRecuerdoService {
	
	@Autowired
	OtroRecuerdoRepository otrosRecuerdosRepository;
	
	public List<OtroRecuerdo> obtenerMascotas() {
		List<OtroRecuerdo> lista_mascotas = otrosRecuerdosRepository.findAll();
		return lista_mascotas;
	}
	
	public Optional<OtroRecuerdo> obtenerPorId(Long id) {
		return otrosRecuerdosRepository.findById(id);
	}
	
	public void guardar(OtroRecuerdo mascota) {
		otrosRecuerdosRepository.save(mascota);
	}
	
	public void borrar(Long id) {
		otrosRecuerdosRepository.deleteById(id);
	}
	
	public boolean existePorId(Long id) {
		return otrosRecuerdosRepository.existsById(id);
	}

}
