package com.matuprojects.TicketsSystem.security;

import com.matuprojects.TicketsSystem.model.Usuario;
import com.matuprojects.TicketsSystem.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        System.out.println("Uusuario encontrado:" + usuario.getCorreo());

        return User.builder()
                .username(usuario.getCorreo())
                .password(usuario.getContraseña()) // ya cifrada
                .roles(usuario.getRol().name())
                .build();
    }
}
