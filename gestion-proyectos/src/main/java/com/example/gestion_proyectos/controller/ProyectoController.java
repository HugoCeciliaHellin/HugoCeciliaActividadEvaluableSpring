package com.example.gestion_proyectos.controller;
import java.util.List;
import java.util.Optional;

import com.example.gestion_proyectos.entity.Proyecto;
import com.example.gestion_proyectos.service.ProyectoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/proyectos")
public class ProyectoController {

    private final ProyectoService proyectoService;

    public ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }
    
    // Listar todos los proyectos
    @GetMapping
public String listarProyectos(Model model) {
    List<Proyecto> proyectos = proyectoService.listarProyectos();
    if (proyectos == null || proyectos.isEmpty()) {
        System.out.println("No se encontraron proyectos.");
    } else {
        System.out.println("Proyectos cargados: " + proyectos.size());
    }
    
    model.addAttribute("proyectos", proyectos);
    return "proyectos/listado";
}


    // Mostrar formulario de creación de proyecto
    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        return "proyectos/crear";
    }

    // Guardar nuevo proyecto
    @PostMapping
    public String guardarProyecto(@ModelAttribute Proyecto proyecto) {
        proyectoService.guardarProyecto(proyecto);
        return "redirect:/proyectos";
    }

    // Ver detalles de un proyecto y sus tareas
    @GetMapping("/{id}")
    public String verDetalle(@PathVariable Long id, Model model) {
        proyectoService.obtenerProyecto(id).ifPresent(proyecto -> model.addAttribute("proyecto", proyecto));
        return "proyectos/detalle";
    }

    // Eliminar un proyecto
    @GetMapping("/eliminar/{id}")
    public String eliminarProyecto(@PathVariable Long id) {
        proyectoService.eliminarProyecto(id);
        return "redirect:/proyectos";
    }
      // Mostrar formulario de edición de proyecto
@GetMapping("/editar/{id}")
public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
    // Busca el proyecto por id
    Optional<Proyecto> proyectoOpt = proyectoService.obtenerProyecto(id);
    if (proyectoOpt.isPresent()) {
        model.addAttribute("proyecto", proyectoOpt.get());
        return "proyectos/editar"; // Se mostrará la vista "editar.html" en la carpeta "proyectos"
    } else {
        // Si no se encuentra, redirige al listado
        return "redirect:/proyectos";
    }
}

// Actualizar proyecto editado
@PostMapping("/editar/{id}")
public String actualizarProyecto(@PathVariable Long id, @ModelAttribute Proyecto proyecto) {
    // Asegúrate de asignar el id al proyecto antes de guardar
    proyecto.setId(id);
    proyectoService.guardarProyecto(proyecto);
    return "redirect:/proyectos";
}
}
