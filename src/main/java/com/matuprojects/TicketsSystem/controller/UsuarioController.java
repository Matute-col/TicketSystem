package com.matuprojects.TicketsSystem.controller;

import com.matuprojects.TicketsSystem.model.EstadoTicket;
import com.matuprojects.TicketsSystem.model.Ticket;
import com.matuprojects.TicketsSystem.model.Usuario;
import com.matuprojects.TicketsSystem.repository.UsuarioRepository;
import com.matuprojects.TicketsSystem.service.TicketService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final TicketService ticketService;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(TicketService ticketService, UsuarioRepository usuarioRepository) {
        this.ticketService = ticketService;
        this.usuarioRepository = usuarioRepository;
    }

    // Muestra el formulario para crear nuevo ticket
    @GetMapping("/nuevo")
    public String mostrarFormularioCrearTicket(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "usuario/nuevo";
    }

    // Guarda el ticket enviado desde el formulario
    @PostMapping("/nuevo/guardar")
    public String guardarTicket(@Valid @ModelAttribute("ticket") Ticket ticket,
                                BindingResult result,
                                Model model,
                                Authentication auth) {
        if (result.hasErrors()) {
            return "usuario/nuevo"; //
        }

        Usuario creador = usuarioRepository.findByCorreo(auth.getName()).orElseThrow();
        ticket.setUsuarioCreador(creador);
        ticket.setEstado(EstadoTicket.ABIERTO);
        ticketService.guardar(ticket);

        return "redirect:/usuario/tickets"; //
    }

    // Muestra todos los tickets del usuario autenticado

    @GetMapping({"/tickets", "/panel/tickets"})
    public String verMisTickets(Model model, Authentication auth) {
        Usuario usuario = usuarioRepository.findByCorreo(auth.getName()).orElseThrow();
        List<Ticket> tickets = ticketService.listarPorUsuario(usuario);
        model.addAttribute("tickets", tickets);
        return "usuario/tickets";
    }

}