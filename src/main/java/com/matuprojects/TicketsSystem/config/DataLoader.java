package com.matuprojects.TicketsSystem.config;

import com.matuprojects.TicketsSystem.model.Rol;
import com.matuprojects.TicketsSystem.model.Usuario;
import com.matuprojects.TicketsSystem.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void run(String... args) throws Exception {

        if (usuarioRepository.count() == 0)
        {
            usuarioRepository.save(Usuario.builder()
                    .nombre("Ana")
                    .correo("usuario@ejemplo.com")
                    .contraseña(passwordEncoder.encode("1234"))
                    .rol(Rol.USUARIO)
                    .build());
            usuarioRepository.save(Usuario.builder()
                    .nombre("Juan")
                    .correo("goldenretiver@ejemplo.com")
                    .contraseña(passwordEncoder.encode("1258"))
                    .rol(Rol.INFORMATICO)
                    .build());
            usuarioRepository.save(Usuario.builder()
                    .nombre("Tom")
                    .correo("gatuno@ejemp.com")
                    .contraseña(passwordEncoder.encode("4577"))
                    .rol(Rol.ADMINISTRADOR)
                    .build());
        }
    }
}
