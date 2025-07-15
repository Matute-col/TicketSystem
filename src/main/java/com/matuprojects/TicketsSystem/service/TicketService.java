package com.matuprojects.TicketsSystem.service;

import com.matuprojects.TicketsSystem.model.EstadoTicket;
import com.matuprojects.TicketsSystem.model.Ticket;
import com.matuprojects.TicketsSystem.model.Usuario;
import com.matuprojects.TicketsSystem.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    //guardar un ticket
    public Ticket guardar(Ticket ticket)
    {
        return ticketRepository.save(ticket);
    }

    //listar tickets creados por un usuario
    public List<Ticket> listarPorUsuario(Usuario usuario)
    {
        return ticketRepository.findByUsuarioCreador(usuario);
    }

    //Listar Tickets pendientes (Abierto)
    public List<Ticket> listarPorPendientes()
    {
        return ticketRepository.findByEstado(EstadoTicket.ABIERTO);
    }

    @Transactional
    //Asignar ticket a informatico + Marca proceso
    public Ticket asignarATecnico(Long ticketId, Usuario tecnico)
    {
        Ticket t = ticketRepository.findById(ticketId)
                .orElseThrow(()-> new RuntimeException("Ticket no encontrado"));
        t.setInformaticoAsignado(tecnico);
        t.setEstado(EstadoTicket.EN_PROCESO);
        return ticketRepository.save(t);
    }

    public List<Ticket> listarAsignados(Usuario tecnico)
    {
        return ticketRepository.findByInformaticoAsignadoAndEstadoNot(tecnico, EstadoTicket.CERRADO);
    }

    public void marcarComoResuelto(Long ticketId)
    {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();
        ticket.setEstado(EstadoTicket.CERRADO);
        ticketRepository.save(ticket);
    }

    public List<Ticket> obtenerTodosLosTickets()
    {
        return ticketRepository.findAll();

    }


    public List<Ticket> obtenerTicketsPorEstado (EstadoTicket estado)
    {
        return ticketRepository.findByEstado(estado);

    }


}
