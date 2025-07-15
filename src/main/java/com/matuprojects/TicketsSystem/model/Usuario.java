package com.matuprojects.TicketsSystem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "usuarios")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true)
    private String correo;

    private String contraseña;

    @Enumerated(EnumType.STRING)
    private Rol rol;



}
