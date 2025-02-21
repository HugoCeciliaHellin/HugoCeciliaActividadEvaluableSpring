package com.example.gestion_proyectos.controller;

import com.example.gestion_proyectos.entity.Proyecto;
import com.example.gestion_proyectos.entity.Tarea;
import com.example.gestion_proyectos.service.ProyectoService;
import com.example.gestion_proyectos.service.TareaService;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tareas")
public class TareaController {

    private final TareaService tareaService;
    private final ProyectoService proyectoService;

    public TareaController(TareaService tareaService, ProyectoService proyectoService) {
        this.tareaService = tareaService;
        this.proyectoService = proyectoService;
    }

    // Mostrar formulario de creación de tarea
    @GetMapping("/crear")
    public String mostrarFormularioCreacion(@RequestParam Long proyectoId, Model model) {
        Tarea tarea = new Tarea();
        Proyecto proyecto = proyectoService.obtenerProyecto(proyectoId).orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado"));
        tarea.setProyecto(proyecto);
        model.addAttribute("tarea", tarea);
        return "tareas/crear";
    }

    // Guardar tarea
    @PostMapping
    public String guardarTarea(@ModelAttribute Tarea tarea) {
        tareaService.guardarTarea(tarea);
        return "redirect:/proyectos/" + tarea.getProyecto().getId();
    }

    // Eliminar tarea por id
    @GetMapping("/eliminar/{id}")
    public String eliminarTarea(@PathVariable Long id) {
        Tarea tarea = tareaService.obtenerTarea(id).orElseThrow(() -> new IllegalArgumentException("Tarea no encontrada"));
        Long proyectoId = tarea.getProyecto().getId();
        tareaService.eliminarTarea(id);
        return "redirect:/proyectos/" + proyectoId;
    }
    // Mostrar formulario de edición de tarea
@GetMapping("/editar/{id}")
public String mostrarFormularioEdicionTarea(@PathVariable Long id, Model model) {
    Optional<Tarea> tareaOpt = tareaService.obtenerTarea(id);
    if (tareaOpt.isPresent()) {
        model.addAttribute("tarea", tareaOpt.get());
        return "tareas/editar"; 
    } else {
        
        return "redirect:/proyectos";
    }
}

// Actualizar tarea
@PostMapping("/editar/{id}")
public String actualizarTarea(@PathVariable Long id, @ModelAttribute Tarea tarea) {
    // Asigna el id de la tarea a actualizar
    tarea.setId(id);
    tareaService.guardarTarea(tarea);
    // Redirige al detalle del proyecto
    return "redirect:/proyectos/" + tarea.getProyecto().getId();
}
}
