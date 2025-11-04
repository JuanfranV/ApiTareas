package com.example.ApiTareas.controllers;

import com.example.ApiTareas.models.TareaModel;
import com.example.ApiTareas.models.UsuarioModel;
import com.example.ApiTareas.repositories.TareaRepository;
import com.example.ApiTareas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    // Obtener tareas por usuario
    @GetMapping("/{userId}")
    public List<TareaModel> listarTareas(@PathVariable Long userId) {
        return tareaRepository.findByUserId(userId);
    }

    // Crear tarea
    @PostMapping
    public TareaModel crearTarea(
            @RequestBody TareaModel tarea,
            @RequestParam String username) {

        UsuarioModel usuario = usuarioRepository.findByUsername(username);

        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        tarea.setUserId(usuario.getId()); // asignar ID automáticamente
        return tareaRepository.save(tarea);
    }

    // Actualizar tarea (editar)
    @PutMapping("/{id}")
    public TareaModel editarTarea(@PathVariable Long id, @RequestBody TareaModel tareaData) {
        TareaModel tarea = tareaRepository.findById(id).orElseThrow();
        tarea.setTitulo(tareaData.getTitulo());
        tarea.setDescripcion(tareaData.getDescripcion());
        tarea.setEstado(tareaData.getEstado());
        return tareaRepository.save(tarea);
    }

    // Eliminar tarea
    @DeleteMapping("/{id}")
    public void borrarTarea(@PathVariable Long id) {
        tareaRepository.deleteById(id);
    }

}
