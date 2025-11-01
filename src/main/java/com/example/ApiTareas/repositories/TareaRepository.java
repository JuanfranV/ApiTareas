package com.example.ApiTareas.repositories;

import com.example.ApiTareas.models.TareaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TareaRepository extends JpaRepository<TareaModel, Long>{

    // Buscar solo tareas del usuario actual
    List<TareaModel> findByUserId(Long userId);

}
