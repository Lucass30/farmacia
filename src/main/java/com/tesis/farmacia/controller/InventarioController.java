package com.tesis.farmacia.controller;

import com.tesis.farmacia.entity.Inventario;
import com.tesis.farmacia.paginator.PageRender;
import com.tesis.farmacia.service.interfaces.IInventarioService;
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
@SessionAttributes("inventario")
@RequestMapping(value="/inventario")
public class InventarioController {
    @Autowired
    private IInventarioService inventarioService;

    @RequestMapping(value= "/form", method = RequestMethod.GET)
    public String crear(Map<String, Object> model) {
        Inventario inventario = new Inventario();
        model.put("inventario", inventario);
        model.put("titulo", "Formulario de Inventario");
        return "sucursales/form";

    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String guardar(@Valid Inventario inventario, BindingResult result, Model model,
                          RedirectAttributes flash, SessionStatus status) {

        if (result.hasErrors()) {
            model.addAttribute("inventarios",inventario);
            model.addAttribute("titulo", "Formulario de Inventario");
            return "sucursales/form";
        }
        String mensajeFlash = (inventario.getId() != null) ? "Inventario editado con éxito!" : "Inventario creado con éxito!";

        inventarioService.save(inventario);
        status.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:/inventario/listar";
    }

    @RequestMapping(value={"/listar"}, method = RequestMethod.GET)
    public String listar(@RequestParam(name="page",defaultValue = "0")int page, Model model){
        Pageable pageRequest = PageRequest.of(page, 10);
        Page<Inventario> inventario = inventarioService.findAll(pageRequest);
        PageRender<Inventario> pageRender = new PageRender<>("/listar",inventario);
        model.addAttribute("titulo", "Listado de Sucursales");
        model.addAttribute("inventario", inventario);
        model.addAttribute("page", pageRender);
        return "sucursales/listar";
    }

    @GetMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id")Long id,Map<String, Object> model,RedirectAttributes flash) {
        Inventario inventario =null;
        if (id > 0) {
            inventario = inventarioService.findOne(id);
            if (inventario == null) {
                flash.addFlashAttribute("error", "El ID del tipo no existe en la BBDD!");
                return "redirect:/inventario/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El ID del tipo no puede ser cero!");
            return "redirect:/inventario/listar";
        }
        model.put("inventario", inventario);
        model.put("titulo", "Editar Inventario");
        return "sucursales/form";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

        if (id != null) {
            inventarioService.delete(id);
            flash.addFlashAttribute("success", "Inventario eliminado con éxito!");

        }
        return "redirect:/inventario/listar";
    }
}
