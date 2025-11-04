package com.example.ApiTareas.controllers;

import com.example.ApiTareas.models.UsuarioModel;
import com.example.ApiTareas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auten")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioReposity;

    @PostMapping("/registrar")
    public String registrar(@RequestBody UsuarioModel user) {
        if (usuarioReposity.findByUsername(user.getUsername()) != null) {
            return "Usuario ya existe";
        }
        usuarioReposity.save(user);
        return "Usuario creado";
    }

    @PostMapping("/login")
    public String login(@RequestBody UsuarioModel user) {
        UsuarioModel encontrado = usuarioReposity.findByUsername(user.getUsername());

        if (encontrado == null || !encontrado.getPassword().equals(user.getPassword())) {
            return "Credenciales incorrectas";
        }

        return "Login exitoso";
    }

}
