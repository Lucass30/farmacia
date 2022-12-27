package com.tesis.farmacia.controller;

import com.tesis.farmacia.entity.Proveedor;
import com.tesis.farmacia.paginator.PageRender;
import com.tesis.farmacia.service.interfaces.IProveedorService;
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
@SessionAttributes("proveedor")
@RequestMapping("/proveedor")
public class ProveedorController {
    @Autowired
    private IProveedorService proveedorService;


    @RequestMapping(value={"/listar"}, method = RequestMethod.GET)
    public String listar(@RequestParam(name="page",defaultValue = "0")int page, Model model){
        Pageable pageRequest = PageRequest.of(page, 10);
        Page<Proveedor> proveedor = proveedorService.findAll(pageRequest);
        PageRender<Proveedor> pageRender = new PageRender<>("/listar",proveedor);
        model.addAttribute("titulo", "Listado de Proveedores");
        model.addAttribute("proveedores", proveedor);
        model.addAttribute("page", pageRender);
        return "proveedores/listar";
    }

    @GetMapping(value = "/ver/{id}")
    public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

        Proveedor proveedor = proveedorService.findOne(id);
        if (proveedor == null) {
            flash.addFlashAttribute("error", "El proveedor no existe en la base de datos");
            return "redirect:/proveedor/listar";
        }

        model.put("proveedor", proveedor);
        model.put("titulo", "Detalle proveedor: " + proveedor.getNombre());
        return "proveedores/ver";
    }
    @RequestMapping(value="/form", method = RequestMethod.GET)
    public String crear (Map<String, Object> model){
        Proveedor proveedor= new Proveedor();
        //model.put("tipo",tipoService.findAll());
        model.put("proveedores",proveedor);
        model.put("titulo", "Formulario de Proveedores");
        return "proveedores/form";
    }

    @RequestMapping(value={"/form/{id}"})
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash){
        Proveedor proveedor= null;
        if(id>0){
            proveedor=proveedorService.findOne(id);
            if (proveedor== null){
                flash.addFlashAttribute("error", "El ID del proveedor no existe en la BBDD!");
                return "redirect:/proveedores/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El ID del proveedor no puede ser cero!");
            return "redirect:/proveedores/listar";
        }
        model.put("proveedores", proveedor);
        model.put("titulo", "Editar Proveedor");
        return "proveedores/form";
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String guardar(@Valid Proveedor proveedor, BindingResult result, Model model,
                          RedirectAttributes flash, SessionStatus status){
        if (result.hasErrors()){
            model.addAttribute("proveedores",proveedor);
            model.addAttribute("titulo","Registro de Proveedor");
            return "redirect:/proveedor/form";
        }
        String mensajeFlash = (proveedor.getId() != null) ? "Proveedor editado con éxito!" : "Proveedor creado con éxito!";

        proveedorService.save(proveedor);
        status.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:/proveedor/listar";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value="id") Long id){
        if(id > 0) {
            proveedorService.delete(id);
        }
        return "redirect:/proveedor/listar";
    }
}
