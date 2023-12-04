package com.example.SpringEgg.controladores;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.example.SpringEgg.entidades.Usuario;
import com.example.SpringEgg.exepciones.MiException;
import com.example.SpringEgg.servicios.UsuarioServicio;
import jakarta.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
@Slf4j
public class Controlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/")
    public String inicio(HttpSession session) {
        log.info("INICIO");
        Usuario logeado = (Usuario) session.getAttribute("usuariosession");
        if (logeado.getRol().toString().equalsIgnoreCase("ADMIN")) {
            return "redirect:admin/dashboard";
        }

        return "index.html";
    }

    @GetMapping("/registrar")
    public String registro() {
        log.info("REGISTRAR");
        return "registrar.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam String email, @RequestParam String password, @RequestParam String passwordDos, ModelMap modelo) {
        try {
            usuarioServicio.registrar(nombre, email, password, passwordDos);

            modelo.put("exito", "usuario registrado correctamente");
            return "login.html";
        } catch (MiException ex) {
            modelo.put("ERROR", ex.getMessage());
            return "registro.html";
        }

    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {
        if (error != null) {

            modelo.put("error", "USUARIO O CLAVE INCORRECTOS");
        }
        log.info("login");

        return "login.html";
    }

}
