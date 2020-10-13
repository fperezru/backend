package com.franperu.tfg.login;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.franperu.tfg.DTO.Mensaje;
import com.franperu.tfg.enums.RolNombre;
import com.franperu.tfg.mascotas.Mascota;
import com.franperu.tfg.security.jwt.JwtProvider;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.0.21:4200"})
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;
    
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos vacíos o email inválido"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existePorNombre(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existePorEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
        Usuario usuario =
                new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getApellidos(), nuevoUsuario.getIdentificacion(),
                		nuevoUsuario.getFechaNacimiento(), nuevoUsuario.getDomicilio(), nuevoUsuario.getTelefono(), nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()), 
                		nuevoUsuario.getFamiliar(), nuevoUsuario.getPermiso());
        Set<String> rolesStr = nuevoUsuario.getRoles();
        Set<Rol> roles = new HashSet<>();
        for (String rol : rolesStr) {
            switch (rol) {
                case "admin":
                    Rol rolAdmin = rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get();
                    roles.add(rolAdmin);
                    break;
                case "familiar":
                    Rol rolFamiliar = rolService.getByRolNombre(RolNombre.ROLE_FAMILIAR).get();
                    roles.add(rolFamiliar);
                    break;
                default:
                    Rol rolUser = rolService.getByRolNombre(RolNombre.ROLE_USER).get();
                    roles.add(rolUser);
            }
        }
        usuario.setRoles(roles);
        usuarioService.guardar(usuario);
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos vacíos o email inválido"), HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<JwtDTO>(jwtDTO, HttpStatus.OK);
    }
    
    @GetMapping("/user/{n}")
	public ResponseEntity<Usuario> getOne(@PathVariable String n){
	    if(!usuarioService.existePorNombre(n))
	       return new ResponseEntity(new Mensaje("no existe ese usuario"), HttpStatus.NOT_FOUND);
	        
	    Usuario usuario = usuarioService.getByNombreUsuario(n).get();
	    return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
    
    @GetMapping("/usuario/{id}")
	public ResponseEntity<Usuario> getOne(@PathVariable Long id){
	    if(!usuarioService.existePorId(id))
	       return new ResponseEntity(new Mensaje("no existe ese usuario"), HttpStatus.NOT_FOUND);
	        
	    Usuario usuario = usuarioService.getById(id).get();
	    return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
}
