/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controladores;


import com.example.exepciones.MiException;
import com.example.servicios.AutorServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/autor")//localhost:8080/autor
@Slf4j
public class ControladorAutor {

    @Autowired
    private AutorServicio autorServicio;

    @GetMapping("/registrar")
    public String registrar() { //localhost:8080/autor/registrar  
       
        return "autor";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre) {
        System.out.println("EL NOMBRE ES: " + nombre);
        try {
            autorServicio.crearAutor(nombre);
            System.out.println("POST RECIBIDO NOMBRE: "+nombre);
            log.info("AUTOR REGISTRO CORRECTO");
        } catch (MiException ex) {
            System.out.println("");
            return "autor";
        }
        return "index";
    }
}
