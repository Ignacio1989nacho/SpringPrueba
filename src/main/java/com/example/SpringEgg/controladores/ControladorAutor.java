/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringEgg.controladores;

import com.example.SpringEgg.entidades.Autor;
import com.example.SpringEgg.exepciones.MiException;

import com.example.SpringEgg.servicios.AutorServicio;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/autor")//localhost:8080/pruebabbdd
@Slf4j
public class ControladorAutor {

    @Autowired
    private AutorServicio autorServicio;

    @GetMapping("/registrar")
    public String registrar() { //localhost:8080/pruebabbdd/registrar  
        log.info("autor");
        return "autor.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo) {
        System.out.println("EL NOMBRE ES: " + nombre);
        try {
            autorServicio.crearAutor(nombre);
            System.out.println("POST RECIBIDO NOMBRE: " + nombre);
            modelo.put("exito", "El autor fue cargado correctamente!");
            log.info("AUTOR REGISTRO CORRECTO");
        } catch (MiException ex) {
            System.out.println("");
            modelo.put("error", ex.getMessage());
            return "autor.html";
        }
        return "index.html";
    }

    @GetMapping("/lista")
    public String listaAutores(ModelMap modelo) {
        List<Autor> listaAutores = autorServicio.listarAutor();
        modelo.addAttribute("listaAutores", listaAutores);

        return "autor_listado.html";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo){
        modelo.put("autor",autorServicio.getOne(id));
        return "autor_modificar.html";
    }
    
}
