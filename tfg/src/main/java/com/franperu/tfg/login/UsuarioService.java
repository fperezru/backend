package com.franperu.tfg.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.franperu.tfg.login.Usuario;
import com.franperu.tfg.login.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerUsusarios() {
    	List<Usuario> lista_usuarios = usuarioRepository.findAll();
    	return lista_usuarios;
    }
    
    public List<Usuario> obtenerFamiliares(Rol rol) {
    	List<Usuario> lista_familiares = usuarioRepository.findByRoles(rol);
    	return lista_familiares;
    }
    
    public List<Usuario> obtenerPacientes(Long familiar) {
    	List<Usuario> lista_pacientes = usuarioRepository.findByFamiliar(familiar);
    	return lista_pacientes;
    }
    
    public Optional<Usuario> getById(Long id){
        return usuarioRepository.findById(id);
    }
    
    public Optional<Usuario> getByNombreUsuario(String nu){
        return usuarioRepository.findByNombreUsuario(nu);
    }

    public boolean existePorNombre(String nu){
        return usuarioRepository.existsByNombreUsuario(nu);
    }

    public  boolean existePorEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public void guardar(Usuario usuario){
        usuarioRepository.save(usuario);
    }
    
    public boolean existePorId(Long id) {
		return usuarioRepository.existsById(id);
	}
    
    public void borrar(Long id) {
    	usuarioRepository.deleteById(id);
    }
}
