package com.franperu.tfg.informacion;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.franperu.tfg.enums.TipoNombre;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Long> {
	Optional<Tipo>findByTipoNombre(TipoNombre tipoNombre);

}
