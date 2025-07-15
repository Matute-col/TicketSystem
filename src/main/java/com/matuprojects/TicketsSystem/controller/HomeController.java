package com.matuprojects.TicketsSystem.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    // Login
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Panel de usuario
    @GetMapping("/usuario/panel")
    public String panelUsuario() {
        return "usuario/panel";
    }



    // Panel de administrador
    @GetMapping("/admin/panel")
    public String panelAdmin() {
        return "admin/panel";
    }
}