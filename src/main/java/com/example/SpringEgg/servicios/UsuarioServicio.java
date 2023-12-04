/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringEgg.servicios;

import com.example.SpringEgg.entidades.Usuario;
import com.example.SpringEgg.enumeraciones.Rol;
import com.example.SpringEgg.exepciones.MiException;
import com.example.SpringEgg.repositorio.UsuarioRepositorio;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void registrar(String nombre, String email, String password, String passwordDos) throws MiException {

        validar(nombre, email, password, passwordDos);

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.setRol(Rol.USER);
        usuarioRepositorio.save(usuario);

    }

    private void validar(String nombre, String email, String password, String passwordDos) throws MiException {

        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("EL NOMBRE NO PUEDE ESTAR VACIO");
        }
        if (email.isEmpty() || email == null) {
            throw new MiException("EL EMAIL NO PUEDE ESTAR VACIO");
        }
        if (password.isEmpty() || password == null || password.length() <= 5) {
            throw new MiException("EL PASSWORD DEBE SER IGUAL Y TENER MAS DE 5 DIGITOS");
        }
        if (!password.equalsIgnoreCase(passwordDos)) {
            throw new MiException("PASSWORDS DISTINTOS");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.buscarUsuarioPorEmail(email);

        if (usuario != null) {
            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
            permisos.add(p);
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", usuario);
            return new User(usuario.getEmail(), usuario.getPassword(), permisos);

        } else {
            return null;
        }
    }
}
