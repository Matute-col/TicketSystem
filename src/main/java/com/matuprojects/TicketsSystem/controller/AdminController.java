package com.matuprojects.TicketsSystem.controller;

import com.matuprojects.TicketsSystem.model.EstadoTicket;
import com.matuprojects.TicketsSystem.model.Ticket;
import com.matuprojects.TicketsSystem.model.Usuario;
import com.matuprojects.TicketsSystem.repository.UsuarioRepository;
import com.matuprojects.TicketsSystem.service.TicketService;
import com.matuprojects.TicketsSystem.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMINISTRADOR')")
public class AdminController {

    private final TicketService ticketService;
    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public AdminController(TicketService ticketService, UsuarioService usuarioService,
                           UsuarioRepository usuarioRepository) {
        this.ticketService = ticketService;
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    //  Ver todos los tickets o filtrarlos por estado
    @GetMapping("/tickets")
    public String verTickets(@RequestParam(name = "estado", required = false) EstadoTicket estado,
                             Model model) {
        List<Ticket> tickets;
        if (estado != null) {
            tickets = ticketService.obtenerTicketsPorEstado(estado);
            model.addAttribute("estadoFiltrado", estado);
        } else {
            tickets = ticketService.obtenerTodosLosTickets();
        }
        model.addAttribute("tickets", tickets);
        return "admin/tickets";
    }

    // Ver listado de usuarios
    @GetMapping("/usuarios")
    public String verUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.listarTodos();
        model.addAttribute("usuarios", usuarios);
        return "admin/usuarios";
    }

    // Mostrar formulario para crear nuevo usuario
    @GetMapping("/usuarios/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "admin/crear";
    }

    // Mostrar formulario para editar usuario existente
    @GetMapping("/usuarios/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        Usuario usuario = usuarioService.buscarPorId(id).orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("usuario", usuario);
        return "admin/crear"; //
    }

    // Guardar nuevo usuario o actualizar existente
    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuarioService.guardar(usuario);
        return "redirect:/admin/usuarios";
    }

    // Eliminar usuario
    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable("id") Long id) {
        usuarioService.eliminar(id);
        return "redirect:/admin/usuarios";
    }
}