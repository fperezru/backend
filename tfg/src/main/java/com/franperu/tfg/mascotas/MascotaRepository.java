package com.franperu.tfg.mascotas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.franperu.tfg.login.Usuario;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {

	List<Mascota>findByUsuario(Usuario usuario);
}
