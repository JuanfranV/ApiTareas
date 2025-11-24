package com.example.ApiTareas.controllers;

import com.example.ApiTareas.models.UsuarioModel;
import com.example.ApiTareas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auten")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioReposity;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody UsuarioModel user) {

        if (usuarioReposity.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.status(409)
                    .body(Map.of("error", "Usuario ya existe"));
        }

        usuarioReposity.save(user);

        return ResponseEntity.ok(Map.of("mensaje", "Usuario creado"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioModel user) {

        UsuarioModel encontrado = usuarioReposity.findByUsername(user.getUsername());

        if (encontrado == null || !encontrado.getPassword().equals(user.getPassword())) {
            return ResponseEntity.status(401).body(Map.of("error", "Credenciales incorrectas"));
        }

        return ResponseEntity.ok(Map.of(
                "id", encontrado.getId(),
                "username", encontrado.getUsername(),
                "mensaje", "Login exitoso"
        ));
    }


    @GetMapping("/{username}")
    public Map<String, Object> obtenerUsuario(@PathVariable String username) {

        UsuarioModel u = usuarioReposity.findByUsername(username);

        if (u == null) {
            return Map.of("error", "Usuario no encontrado");
        }

        return Map.of(
                "id", u.getId(),
                "username", u.getUsername()
        );
    }


}
