/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringEgg.servicios;

import com.example.SpringEgg.entidades.Autor;
import com.example.SpringEgg.entidades.Editorial;
import com.example.SpringEgg.entidades.Libro;
import com.example.SpringEgg.repositorio.AutorRepositorio;
import com.example.SpringEgg.repositorio.EditorialRepositorio;
import com.example.SpringEgg.repositorio.LibroRepositorio;

import com.example.SpringEgg.exepciones.MiException;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    //METODOS QUE MODIFIQUEN LA BBDD SI O SI TRANSACTIONAL!
    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiException {
        
   
        validacion(isbn,titulo,ejemplares,idAutor,idEditorial);
        Libro libro = new Libro();
        Autor autor = (Autor) autorRepositorio.findById(idAutor).get();
        Editorial editorial = (Editorial) editorialRepositorio.findById(idAutor).get();

        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setFechaAlta(new Date());

        libro.setAutor(autor);
        libro.setEditorial(editorial);

        libroRepositorio.save(libro);
    }

    public List<Libro> listarLibros() {
        List<Libro> listaLibros = new ArrayList();
        listaLibros = libroRepositorio.findAll();
        return listaLibros;

    }

    @Transactional
    public void modificarLibro(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares)throws MiException {
        
        validacion(isbn,titulo,ejemplares,idAutor,idEditorial);
        
        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);
        Autor autor = new Autor();
        Editorial editorial = new Editorial();
        if (respuestaAutor.isPresent()) {
            autor = respuestaAutor.get();

        }
        if (respuestaEditorial.isPresent()) {
            editorial = respuestaEditorial.get();

        }
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setEjemplares(ejemplares);
            libroRepositorio.save(libro);
        }
    }
    
    private void validacion(Long isbn,String titulo,Integer ejemplares,String idAutor,String idEditorial) throws MiException{
    
         if (isbn == null) {
            throw new MiException("el isbn no puede ser nulo");
        }
        if (titulo.isEmpty() || titulo == null) {
            throw new MiException("el titulo no puede ser nulo o estar vacio");
        }
        if (ejemplares == null) {
            throw new MiException("error en ejemplares");
        }
        if (idAutor.isEmpty() || idAutor == null) {
            throw new MiException("el autor no puede estar vacio o ser nulo");
        }
        if (idEditorial.isEmpty() || idEditorial == null) {
            throw new MiException("la editorial no puede ser nulo o estar vacio");
        }
    
    }
}
