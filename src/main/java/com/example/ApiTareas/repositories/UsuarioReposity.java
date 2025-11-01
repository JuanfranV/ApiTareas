package com.example.ApiTareas.repositories;

import com.example.ApiTareas.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioReposity extends JpaRepository<UsuarioModel, Long> {
    UsuarioModel findByUsername(String username);


}
