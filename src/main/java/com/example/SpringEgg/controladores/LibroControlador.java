/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringEgg.controladores;

import com.example.SpringEgg.exepciones.MiException;
import com.example.SpringEgg.servicios.AutorServicio;
import com.example.SpringEgg.servicios.EditorialServicio;
import com.example.SpringEgg.servicios.LibroServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("/libro")//localhost:8080/libro
public class LibroControlador {

    @Autowired
    private EditorialServicio editorialServicio;
    @Autowired
    private AutorServicio autorServicio;
    @Autowired
    private LibroServicio libroServicio;

    @GetMapping("/registrar")//localhost:8080/libro/registrar
    public String crearLibro() {
        log.info("libro");
        return "libro.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String titulo, @RequestParam String idAutor, @RequestParam String idEditorial, @RequestParam(required=false) Integer ejemplares, @RequestParam(required=false) Long isbn, ModelMap modelo) {
        System.out.println(titulo);
        try {
            libroServicio.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
            modelo.put("exito","el libro fue cargado correctamente" );
            System.out.println("POST RECIBIDO NOMBRE");
            log.info("LIBRO REGISTRO CORRECTO");
        } catch (MiException ex) {
            modelo.put("error",ex.getMessage());
            return "libro.html";
        }
        return "index.html";

    }

}
