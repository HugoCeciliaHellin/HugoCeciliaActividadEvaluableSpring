package com.example.gestion_proyectos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestion_proyectos.entity.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
    // Métodos de consulta personalizados (si es necesario)
}