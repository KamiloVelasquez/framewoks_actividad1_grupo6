package com.rollerspeed.rollerspeed.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    @GetMapping("/")
    public String inicio() {
        return "layout";
    }

    @GetMapping("/Misión")
    public String Mision() {
        return "Misión";
    }

    @GetMapping("/Visión")
    public String Vision() {
        return "Visión";
    }

    @GetMapping("/Servicios")
    public String Servicios() {
        return "Servicios";
    }

    @GetMapping("/Eventos")
    public String Eventos_de_la_escuela() {
        return "Eventos de la escuela";
    }
}