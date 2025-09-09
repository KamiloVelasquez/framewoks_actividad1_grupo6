package com.rollerspeed.rollerspeed.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    @GetMapping("/")
    public String inicio() {
        return "layout";
    }

    @GetMapping("/mision")
    public String mision() {
        return "mision";  // -> busca templates/mision.html
    }

    @GetMapping("/vision")
    public String vision() {
        return "vision";  // -> templates/vision.html
    }

    @GetMapping("/servicios")
    public String servicios() {
        return "servicios"; // -> templates/servicios.html
    }

    @GetMapping("/eventos")
    public String eventos() {
        return "eventos"; // -> templates/eventos.html
    }

    @GetMapping("/valores")
    public String valores() {
    return "valores"; // -> templates/valores.html
    }

    @GetMapping("/objetivos")
    public String objetivos() {
        return "objetivos"; // -> templates/objetivos.html
    }

    @GetMapping("/contacto")
    public String contacto() {
        return "contacto"; // -> templates/contacto.html
    }

    
}
