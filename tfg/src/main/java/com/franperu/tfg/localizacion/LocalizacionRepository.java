package com.franperu.tfg.localizacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.franperu.tfg.login.Usuario;

public interface LocalizacionRepository extends JpaRepository<Localizacion, Long> {

	List<Localizacion>findByUsuario(Usuario usuario);
}
