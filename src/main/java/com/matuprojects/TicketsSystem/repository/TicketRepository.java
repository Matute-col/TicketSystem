package com.matuprojects.TicketsSystem.repository;

import com.matuprojects.TicketsSystem.model.Ticket;
import com.matuprojects.TicketsSystem.model.Usuario;
import com.matuprojects.TicketsSystem.model.EstadoTicket;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long>{

 List<Ticket> findByUsuarioCreador(Usuario usuario);
 List<Ticket> findByInformaticoAsignado(Usuario informatico);
 List<Ticket> findByEstado(EstadoTicket estado);
 List<Ticket> findByInformaticoAsignadoAndEstadoNot(Usuario usuario, EstadoTicket estado);

}
