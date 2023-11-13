package com.noticias.noticias.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import com.noticias.noticias.entidades.Noticia;
import com.noticias.noticias.servicios.NoticiaServicio;

@Controller
@RequestMapping("/noticia")
public class NoticiaControlador {

    @Autowired
    private NoticiaServicio noticiaServicio;

    // Muestra el formulario de registro
    @GetMapping("/registrar")
    public String mostrarFormularioRegistro() {
        return "crear_noticia.html";
    }

    // Procesa el registro de noticias
    @PostMapping("/registro")
    public String registrarNoticia(@RequestParam(required = false) Long id, @RequestParam String titulo,
            @RequestParam String cuerpo, ModelMap modelo) {

        try {

            noticiaServicio.crearNoticia(titulo, cuerpo);
            modelo.put("exito", "La noticia se cargo correctamente");

        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "panel_admin.html";
        }

        return "inicio.html";
    }

    // Muestra la lista de noticias
    @GetMapping("/lista")
    public String listarNoticia(ModelMap modelo) {

        List<Noticia> noticias = noticiaServicio.listarNoticias();

        modelo.addAttribute("noticias", noticias);

        return "inicio.html";

    }

    // Muestra una noticia
    @GetMapping("/ver/{id}")
    public String verNoticia(@PathVariable Long id, ModelMap modelo) {

        Noticia noticia = noticiaServicio.getOne(id);

        if (noticia != null) {

            modelo.addAttribute("noticia", noticia);
            return "noticia.html";

        } else {
            modelo.addAttribute("error", "La noticia no se encontro");
            return "inicio.html";
        }

    }

    // Muestra el formulario de modificación
    @GetMapping("/modificar/{id}")
    public String mostrarFormularioModificacion(@PathVariable Long id, ModelMap modelo) {
        modelo.put("noticia", noticiaServicio.getOne(id));

        return "panel_admin.html";
    }

    // Procesa la modificación de noticias
    @PatchMapping("/modificar/{id}")
    public String modificarNoticia(@PathVariable Long id, String titulo, String cuerpo, ModelMap modelo) {

        try {

            noticiaServicio.modificarNoticia(id, titulo, cuerpo);
            return "redirect:../lista";

        } catch (Exception e) {
            modelo.put("Error", e.getMessage());
            return "panel_admin.html";
        }

    }

    // Elimina una noticia
    @DeleteMapping("/eliminar/{id}")
    public String eliminarNoticia(@PathVariable Long id, ModelMap modelo) {
        noticiaServicio.eliminarNoticia(id);

        return "panel_admin.html";
    }
}
