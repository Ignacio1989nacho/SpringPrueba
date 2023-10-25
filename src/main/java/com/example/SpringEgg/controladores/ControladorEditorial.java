/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringEgg.controladores;

import com.example.SpringEgg.entidades.Editorial;
import com.example.SpringEgg.exepciones.MiException;
import com.example.SpringEgg.servicios.EditorialServicio;
import java.util.List;
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
@RequestMapping("/editorial")//localhost:8080/editorial
public class ControladorEditorial {

    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/registrar")//localhost:8080/editorial/registrar
    public String crearEditorial() {
        log.info("editorial");
        return "editorial.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo) {
        System.out.println("EL NOMBRE DE LA EDITORIAL ES: " + nombre);
        try {
            editorialServicio.crearEditorial(nombre);
            System.out.println("POST RECIBIDO NOMBRE: " + nombre);
            modelo.put("exito", "La editorial fue cargada correctamente!");
            log.info("EDITORIAL REGISTRO CORRECTO");
        } catch (MiException ex) {
            System.out.println("");
            modelo.put("error", ex.getMessage());
            return "editorial.html";
        }
        return "index.html";
    }

    @GetMapping("/lista")
    public String listaEditorial(ModelMap modelo) {
        List<Editorial> listadoEditorial = editorialServicio.listarEditorial();
        modelo.addAttribute("listadoEditorial", listadoEditorial);
        return "listado_editorial.html";
    }
}
