package com.tesis.farmacia.controller;

import com.tesis.farmacia.entity.Rol;
import com.tesis.farmacia.entity.Usuario;
import com.tesis.farmacia.paginator.PageRender;
import com.tesis.farmacia.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

@Controller
@SessionAttributes("usuario")
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IRolService rolService;

    @Autowired
    private IDepartamentoService departamentoService;

    @Autowired
    private IProvinciaService provinciaService;

    @Autowired
    private IDistritoService distritoService;

    @Autowired
    private IUploadFileService uploadFileService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping(value = "/listar")
    public String index(@RequestParam(name="page",defaultValue = "0")int page,Model model) {
        Pageable pageRequest = PageRequest.of(page, 10);
        Page<Usuario> usuarios = usuarioService.findAll(pageRequest);
        PageRender<Usuario> pageRender = new PageRender<>("/listar",usuarios);

        model.addAttribute("titulo", "Listado de usuarios");
        model.addAttribute("usuarios", usuarioService.findAll());
        model.addAttribute("page", pageRender);
        return "usuarios/listar";
    }

    @GetMapping(value = "/form")
    public String crear(Map<String, Object> model) {
        Usuario usuario= new Usuario();
        model.put("usuario",usuario);
        model.put("titulo", "Registro de usuario");
        model.put("departamentos",departamentoService.findAll());
        model.put("roles", rolService.findAll());
        return "usuarios/form";
    }

    @RequestMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

        Usuario usuario = null;

        if (id > 0) {
            usuario = usuarioService.findOne(id);
            if (usuario == null) {
                flash.addFlashAttribute("error", "El ID del usuario no existe en la BBDD!");
                return "redirect:/usuario/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El ID del ususario no puede ser cero!");
            return "redirect:/usuario/listar";
        }

        model.put("departamentos",departamentoService.findAll());
        model.put("provincias",provinciaService.findAll());
        model.put("distritos",distritoService.findAll());
        model.put("roles", rolService.findAll());
        model.put("usuario", usuario);
        model.put("titulo", "Editar Usuario");
        return "usuarios/editar";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String guardar(@Valid Usuario usuario, BindingResult result, Model model,
                          @RequestParam("file") MultipartFile foto,
                          @RequestParam(name = "rol_id", required = false) Long rolId,
                          RedirectAttributes flash,SessionStatus status) {
        if(result.hasErrors()) {
            model.addAttribute("usuario",usuario);
            model.addAttribute("departamentos",departamentoService.findAll());
            model.addAttribute("provincias",provinciaService.findAll());
            model.addAttribute("distritos",distritoService.findAll());
            model.addAttribute("roles", rolService.findAll());
            model.addAttribute("titulo", "Editar Usuario");
            return "usuarios/editar";
        }
        if (rolId == null || rolId == 0) {
            model.addAttribute("titulo", "Formulario de Usuarios");
            model.addAttribute("usuario", usuario);
            model.addAttribute("departamentos",departamentoService.findAll());
            model.addAttribute("provincias",provinciaService.findAll());
            model.addAttribute("distritos",distritoService.findAll());
            model.addAttribute("roles", rolService.findAll());
            model.addAttribute("error", "Error: Debe elegir un rol");
            if (usuario.getId() != null && usuario.getId() > 0 ) {
                return "usuarios/editar";
            }else{
                return "usuarios/form";
            }
        }

        if (!foto.isEmpty()) {
            if (usuario.getId() != null && usuario.getId() > 0 && usuario.getFoto() != null && usuario.getFoto().length() > 0) {
                uploadFileService.delete(usuario.getFoto());
            }
            String uniqueFilename = null;
            try {
                uniqueFilename = uploadFileService.copy(foto);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
            usuario.setFoto(uniqueFilename);
        }
        if (usuario.getPassword().isEmpty()){
            Usuario usuarioPrev = usuarioService.findOne(usuario.getId());
            usuario.setPassword(usuarioPrev.getPassword());
        }else {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        Rol rol= rolService.findOne(rolId);
        usuario.addRol(rol);
        usuarioService.save(usuario);
        status.setComplete();
        return "redirect:/usuario/listar";
    }

    @RequestMapping(value="/eliminar/{id}")
    public String eliminar(@PathVariable(value="id") Long id) {

        if(id > 0) {
            usuarioService.delete(id);
        }
        return "redirect:/usuario/listar";
    }

    @RequestMapping(value = "/perfil")
    public String clientePerfil( Map<String, Object> model, RedirectAttributes flash) {
        Usuario usuario = usuarioService.getUsuario();
        model.put("departamentos",departamentoService.findAll());
        model.put("provincias",provinciaService.findAll());
        model.put("distritos",distritoService.findAll());
        model.put("usuario", usuario);
        model.put("titulo", "Editar Perfil");
        return "usuarios/perfil";
    }

    @RequestMapping(value = "/perfil/save", method = RequestMethod.POST)
    public String guardarPerfil(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status,RedirectAttributes flash) {
        if(result.hasErrors()) {
            model.addAttribute("departamentos",departamentoService.findAll());
            model.addAttribute("provincias",provinciaService.findAll());
            model.addAttribute("distritos",distritoService.findAll());
            model.addAttribute("usuario", usuario);
            model.addAttribute("titulo", "Editar Perfil");
            return "usuarios/perfil";
        }
        if (usuario.getPassword().isEmpty()){
            Usuario usuarioPrev = usuarioService.findOne(usuario.getId());
            usuario.setPassword(usuarioPrev.getPassword());
        }else {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        usuarioService.save(usuario);
        status.setComplete();
        flash.addFlashAttribute("success", "Los cambios se han guardado correctamente");
        return "redirect:/perfil";
    }

    @GetMapping(value = "/uploads/{filename:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

        Resource recurso = null;

        try {
            recurso = uploadFileService.load(filename);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
                .body(recurso);
    }
}
