package com.example.ApiTareas.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tareas")
public class TareaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private String descripcion;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String estado;

    public TareaModel() {
    }

    public TareaModel(Long id, String titulo, String descripcion, Long userId, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.userId = userId;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
