package com.franperu.tfg.informacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InformacionRepository extends JpaRepository<Informacion, Long> {
	
	List<Informacion>findByTipo(Tipo tipo);
}
