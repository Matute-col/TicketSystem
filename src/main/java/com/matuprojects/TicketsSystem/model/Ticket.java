package com.matuprojects.TicketsSystem.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Ticket {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Titulo Obligatorio")
    private String titulo;

    @NotBlank(message = "Descripcion Obligatoria")
    private String descripcion;


    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private EstadoTicket estado = EstadoTicket.ABIERTO;

    @Enumerated(EnumType.STRING)
    private PrioridadTicket prioridad;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuarioCreador;


    @ManyToOne
    @JoinColumn(name = "informatico_id")
    private Usuario informaticoAsignado;

    @PrePersist
    public void asignarFechaCreacion()
    {
        this.fechaCreacion = LocalDateTime.now();

    }

}
