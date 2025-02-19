package com.example.gestion_proyectos.controller;

import com.example.gestion_proyectos.entity.Proyecto;
import com.example.gestion_proyectos.entity.Tarea;
import com.example.gestion_proyectos.service.ProyectoService;
import com.example.gestion_proyectos.service.TareaService;

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

    // Mostrar formulario para crear una nueva tarea
    @GetMapping("/crear")
    public String mostrarFormularioCreacion(@RequestParam Long proyectoId, Model model) {
        Tarea tarea = new Tarea();
        Proyecto proyecto = proyectoService.obtenerProyecto(proyectoId).orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado"));
        tarea.setProyecto(proyecto);
        model.addAttribute("tarea", tarea);
        return "tareas/crear";
    }

    // Guardar nueva tarea
    @PostMapping
    public String guardarTarea(@ModelAttribute Tarea tarea) {
        tareaService.guardarTarea(tarea);
        return "redirect:/proyectos/" + tarea.getProyecto().getId();
    }

    // Eliminar una tarea
    @GetMapping("/eliminar/{id}")
    public String eliminarTarea(@PathVariable Long id) {
        Tarea tarea = tareaService.obtenerTarea(id).orElseThrow(() -> new IllegalArgumentException("Tarea no encontrada"));
        Long proyectoId = tarea.getProyecto().getId();
        tareaService.eliminarTarea(id);
        return "redirect:/proyectos/" + proyectoId;
    }
}
