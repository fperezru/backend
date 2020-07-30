package com.franperu.tfg.diario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.franperu.tfg.login.Usuario;

@Service
@Transactional
public class DiarioService {

	@Autowired
	DiarioRepository diarioRepository;
	
	public List<Diario> obtenerDiarioUsuario(Usuario usuario) {
		List<Diario> lista_paginas = diarioRepository.findByUsuario(usuario);
		return lista_paginas;
	}
	
	public Optional<Diario> obtenerDiarioPorId(Long id) {
		return diarioRepository.findById(id);
	}
	
	public void guardar(Diario diario) {
		diarioRepository.save(diario);
	}
	
	public void borrar(Long id) {
		diarioRepository.deleteById(id);
	}
	
	public boolean existePorId(Long id) {
		return diarioRepository.existsById(id);
	}
}
