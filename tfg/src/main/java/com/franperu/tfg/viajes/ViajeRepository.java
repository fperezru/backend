package com.franperu.tfg.viajes;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.franperu.tfg.login.Usuario;

public interface ViajeRepository extends JpaRepository<Viaje, Long> {

	List<Viaje>findByUsuario(Usuario usuario);
	
	Optional<Viaje>findByLugar(String lugar);
}
