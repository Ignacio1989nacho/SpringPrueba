/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringEgg.servicios;

import com.example.SpringEgg.entidades.Autor;
import com.example.SpringEgg.exepciones.MiException;
import com.example.SpringEgg.repositorio.AutorRepositorio;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Transactional
    public void crearAutor(String nombre) throws MiException {

        validacionNombre(nombre);
        Autor autor = new Autor();
        autor.setNombre(nombre);

        autorRepositorio.save(autor);
    }

    public List<Autor> listarAutor() {

        List<Autor> listaAutor = new ArrayList();
        listaAutor = autorRepositorio.findAll();
        return listaAutor;
    }

    @Transactional
    public void modificarAutor(String nombre, String id) throws MiException {

        validacion(nombre, id);
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        Autor autor = new Autor();

        if (respuesta.isPresent()) {
            autor.setId(id);
            autor.setNombre(nombre);
            autorRepositorio.save(autor);
        }
    }

    @Transactional
    public Autor getOne(String id) {
        return autorRepositorio.getOne(id);
    }

    @SuppressWarnings("null")
    private void validacion(String nombre, String id) throws MiException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("EL CAMPO NOMBRE NO PUEDE SER NULO O ESTAR VACIO");
        }
        if (id == null || id.isEmpty()) {
            throw new MiException("EL CAMPO ID NO PUEDE SER NULO O ESTAR VACIO");
        }

    }

    private void validacionNombre(String nombre) throws MiException {

        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("ERROR EN EL CAMPO NOMBRE");
        }
    }

}
