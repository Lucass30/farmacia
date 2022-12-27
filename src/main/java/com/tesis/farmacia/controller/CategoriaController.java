package com.tesis.farmacia.controller;

import com.tesis.farmacia.entity.Categoria;
import com.tesis.farmacia.paginator.PageRender;
import com.tesis.farmacia.service.interfaces.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

@Controller
@SessionAttributes("categoria")
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private ICategoriaService categoriaService;

    @RequestMapping(value={"/listar"}, method = RequestMethod.GET)
    public String listar(@RequestParam(name="page",defaultValue = "0")int page, Model model){
        Pageable pageRequest = PageRequest.of(page, 10);
        Page<Categoria> categorias = categoriaService.findAll(pageRequest);
        PageRender<Categoria> pageRender = new PageRender<>("/listar",categorias);
        model.addAttribute("titulo", "Listado de Categorias");
        model.addAttribute("categorias",categoriaService.findAll());
        model.addAttribute("page", pageRender);
        return "categorias/listar";
    }
    @RequestMapping(value="/form", method = RequestMethod.GET)
    public String crear (Map<String, Object> model){
        Categoria categoria= new Categoria();
        model.put("categoria",categoria);
        model.put("titulo", "Formulario de Categoria");
        return "categorias/form";
    }

    @GetMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id")Long id, Map<String, Object> model, RedirectAttributes flash) {
        Categoria categoria =null;
        if (id > 0) {
            categoria = categoriaService.findOne(id);
            if (categoria == null) {
                flash.addFlashAttribute("error", "El ID de la Categoria no existe en la BBDD!");
                return "redirect:/categoria/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El ID de la Categoria no puede ser cero!");
            return "redirect:/categoria/listar";
        }
        model.put("categoria", categoria);
        model.put("titulo", "Editar Categoria");
        return "categorias/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String guardar(@Valid Categoria categoria, BindingResult result, Model model,
                          RedirectAttributes flash, SessionStatus status) {

        if (result.hasErrors()) {
            model.addAttribute("categoria", categoria);
            model.addAttribute("titulo", "Formulario de Categorias");
            return "categorias/form";
        }

        String mensajeFlash = (categoria.getId() != null) ? "Tipo editado con éxito!" : "Tipo creado con éxito!";

        categoriaService.save(categoria);
        status.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:/categoria/listar";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

        if (id != null) {
            categoriaService.delete(id);
            flash.addFlashAttribute("success", "Categoria eliminado con éxito!");

        }
        return "redirect:/categoria/listar";
    }
}
