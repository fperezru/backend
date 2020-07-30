package com.franperu.tfg.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.franperu.tfg.login.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findById(Long id);
    Optional<Usuario> findByNombreUsuario(String nu);
    boolean existsByNombreUsuario(String nu);
    boolean existsByEmail(String email);
    List<Usuario> findByRoles(Rol rol);
    
    @Query("SELECT u from Usuario u WHERE u.familiar = familiar")
    List<Usuario> findByFamiliar(@Param("familiar") Long familiar);
}
