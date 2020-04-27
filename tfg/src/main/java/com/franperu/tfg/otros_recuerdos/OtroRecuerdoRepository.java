package com.franperu.tfg.otros_recuerdos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.franperu.tfg.login.Usuario;

public interface OtroRecuerdoRepository extends JpaRepository<OtroRecuerdo, Long> {
	
	List<OtroRecuerdo>findByUsuario(Usuario usuario);

}
