package com.example.gestion_proyectos.service;

import com.example.gestion_proyectos.entity.Tarea;
import com.example.gestion_proyectos.repository.TareaRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    // Método para listar todas las tareas
    public List<Tarea> listarTareas() {
        return tareaRepository.findAll();
    }

    // Método para obtener una tarea por su id
    public Optional<Tarea> obtenerTarea(Long id) {
        return tareaRepository.findById(id);
    }

    // Método para guardar o actualizar una tarea
    public Tarea guardarTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    // Método para eliminar una tarea por su id
    public void eliminarTarea(Long id) {
        tareaRepository.deleteById(id);
    }
}
