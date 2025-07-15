package com.matuprojects.TicketsSystem.controller;


import com.matuprojects.TicketsSystem.model.Ticket;
import com.matuprojects.TicketsSystem.model.Usuario;
import com.matuprojects.TicketsSystem.repository.UsuarioRepository;
import com.matuprojects.TicketsSystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/informatico")

public class TecnicoController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Mostrar panel con tickets pendientes
    @GetMapping("/panel")
    public String panelTecnico(Model model, Authentication auth)
    {
        List<Ticket> pendientes = ticketService.listarPorPendientes();
        model.addAttribute("ticketsPendientes", pendientes);
        return "informatico/panel";
    }

    //Aceptar Ticket
    @PostMapping("/tomar/{id}")
    public String tomarTicket(@PathVariable("id") Long ticketId, Authentication auth)
    {
        Usuario tecnico = usuarioRepository.findByCorreo(auth.getName())
                .orElseThrow();
        ticketService.asignarATecnico(ticketId,tecnico);
        return "redirect:/informatico/panel";
    }

    @GetMapping("/asignados")
    public String ticketAsignados(Authentication auth, Model model)
    {
      Usuario tecnico = usuarioRepository.findByCorreo(auth.getName()).orElseThrow();
      List<Ticket> asignados = ticketService.listarAsignados(tecnico);
      model.addAttribute("tickets",asignados);
      return "informatico/asignados";
    }

    @PostMapping("/resolver")
    public String resolverTicket (@RequestParam Long ticketId)
    {
        ticketService.marcarComoResuelto(ticketId);
        return "redirect:/informatico/asignados";
    }

}
