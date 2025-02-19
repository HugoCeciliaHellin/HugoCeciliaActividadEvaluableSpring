package com.example.gestion_proyectos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestion_proyectos.entity.Proyecto;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    // Métodos de consulta personalizados (si es necesario)
}