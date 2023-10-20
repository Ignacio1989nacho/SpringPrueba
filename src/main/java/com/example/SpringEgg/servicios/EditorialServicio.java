/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringEgg.servicios;

import com.example.SpringEgg.entidades.Editorial;
import com.example.SpringEgg.repositorio.EditorialRepositorio;
import com.example.SpringEgg.exepciones.MiException;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio {

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearEditorial(String nombre) throws MiException {
        validacionNombre(nombre);
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);

        editorialRepositorio.save(editorial);

    }

    public List<Editorial> listarEditorial() {
        List<Editorial> listaEditorial = new ArrayList();
        listaEditorial = editorialRepositorio.findAll();
        return listaEditorial;
    }

    @Transactional
    public void modificarEditorial(String nombre, String id) throws MiException {
        validacion(nombre, id);
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        Editorial editorial = new Editorial();
        if (respuesta.isPresent()) {
            editorial.setNombre(nombre);
            editorial.setId(id);
            editorialRepositorio.save(editorial);
        }

    }

    private void validacion(String nombre, String id) throws MiException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("NO PUEDE SER NULO O ESTAR VACIO");
        }
        if (id.isEmpty() || id == null) {
            throw new MiException("NO PUEDE SER NULO O ESTAR VACIO");
        }
    }

    private void validacionNombre(String nombre) throws MiException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("NO PUEDE SER NULO O ESTAR VACIO");
        }
    }
}
