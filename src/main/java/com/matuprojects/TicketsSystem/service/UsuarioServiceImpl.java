package com.matuprojects.TicketsSystem.service;

import com.matuprojects.TicketsSystem.model.Usuario;
import com.matuprojects.TicketsSystem.repository.UsuarioRepository;
import com.matuprojects.TicketsSystem.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UsuarioServiceImpl implements UsuarioService {


    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

@Autowired
public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder )
{
    this.usuarioRepository = usuarioRepository;
    this.passwordEncoder = passwordEncoder;
}



@Override
public List<Usuario> listarTodos()
{
    return usuarioRepository.findAll();
}

@Override
    public Optional<Usuario> buscarPorId(Long id)
{
    return usuarioRepository.findById(id);
}

@Override
    public void  guardar(Usuario usuario)
{
    usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));
    usuarioRepository.save(usuario);

}

@Override
    public void eliminar (Long id)
{
    usuarioRepository.deleteById(id);

}

}
