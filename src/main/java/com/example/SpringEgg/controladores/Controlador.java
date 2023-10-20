package com.example.SpringEgg.controladores;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/")
@Slf4j
public class Controlador {
    
    @GetMapping("/")
    public String inicio() {
        log.info("INICIO");
        return "index.html";
    }
   
    @GetMapping("/autor")
    public String autor() {
        log.info("AUTOR");
        return "autor.html";
    }
   

//   @Autowired
//    private AutorServicio autorServicio;
//
//    @GetMapping("/registrar")
//    public String registrar() { //localhost:8080/autor/registrar  
//       
//        return "autor.html";
//    }
//
//    @PostMapping("/registro")
//    public String registro(@RequestParam String nombre) {
//        System.out.println("EL NOMBRE ES: " + nombre);
//        try {
//            autorServicio.crearAutor(nombre);
//            System.out.println("POST RECIBIDO NOMBRE: "+nombre);
//            log.info("AUTOR REGISTRO CORRECTO");
//        } catch (MiException ex) {
//            System.out.println("");
//            return "autor.html";
//        }
//        return "index.html";
//    }

}
