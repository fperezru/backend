package com.franperu.tfg.informacion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.franperu.tfg.enums.TipoNombre;

@Service
@Transactional
public class TipoService {
	
	@Autowired
	TipoRepository tipoRepository;
	
	public List<Tipo> obtenerTipos() {
		List<Tipo> lista_tipos = tipoRepository.findAll();
		return lista_tipos;
	}
	
	public Optional<Tipo> getByTipoNombre(TipoNombre tipoNombre) {
		return tipoRepository.findByTipoNombre(tipoNombre);
	}
}
