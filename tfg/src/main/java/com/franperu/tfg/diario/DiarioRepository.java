package com.franperu.tfg.diario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.franperu.tfg.login.Usuario;

public interface DiarioRepository extends JpaRepository<Diario, Long> {
	
	List<Diario>findByUsuario(Usuario usuario);
}
