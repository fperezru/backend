package com.franperu.tfg.mascotas;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.franperu.tfg.login.Usuario;


@Service
@Transactional
public class MascotaService {
	
	@Autowired
	MascotaRepository mascotaRepository;
	
	public List<Mascota> obtenerMascotas() {
		List<Mascota> lista_mascotas = mascotaRepository.findAll();
		return lista_mascotas;
	}
	
	public List<Mascota> obtenerMascotasUsuario(Usuario usuario) {
		List<Mascota> lista_mascotas = mascotaRepository.findByUsuario(usuario);
		return lista_mascotas;
	}
	
	public Optional<Mascota> obtenerPorId(Long id) {
		return mascotaRepository.findById(id);
	}
	
	public void guardar(Mascota mascota) {
		mascotaRepository.save(mascota);
	}
	
	public void borrar(Long id) {
		mascotaRepository.deleteById(id);
	}
	
	public boolean existePorId(Long id) {
		return mascotaRepository.existsById(id);
	}

}
