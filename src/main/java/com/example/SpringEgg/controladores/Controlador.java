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
   
  
   



}
