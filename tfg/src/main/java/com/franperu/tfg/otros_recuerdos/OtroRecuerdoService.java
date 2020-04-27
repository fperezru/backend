package com.franperu.tfg.otros_recuerdos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.franperu.tfg.login.Usuario;


@Service
@Transactional
public class OtroRecuerdoService {
	
	@Autowired
	OtroRecuerdoRepository otrosRecuerdosRepository;
	
	public List<OtroRecuerdo> obtenerRecuerdos() {
		List<OtroRecuerdo> lista_recuerdos = otrosRecuerdosRepository.findAll();
		return lista_recuerdos;
	}
	
	public List<OtroRecuerdo> obtenerRecuerdosUsuario(Usuario usuario) {
		List<OtroRecuerdo> lista_recuerdos = otrosRecuerdosRepository.findByUsuario(usuario);
		return lista_recuerdos;
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
