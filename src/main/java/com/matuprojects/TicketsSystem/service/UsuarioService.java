package com.matuprojects.TicketsSystem.service;

import com.matuprojects.TicketsSystem.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> listarTodos();
    Optional<Usuario> buscarPorId(Long id);
    void guardar (Usuario usuario);
    void eliminar (Long id);
}
