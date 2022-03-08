package com.mgbt.web;

import com.mgbt.domain.*;
import com.mgbt.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ControladorInicio {

    @Autowired
    private LugarServiceImpl lugarService;

    @Autowired
    private ActividadServiceImpl actividadService;

    @Autowired
    private ContactoServiceImpl contactoService;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private RolServiceImpl rolService;

    @GetMapping("/registrarse")
    public String registrarse(Model model, @RequestParam(value = "mensaje", required = false) String mensaje) {
        model.addAttribute("mensaje", mensaje);
        return "registro";
    }

    @PostMapping("/verificar")
    public String verificar(String usuario, String email, String password, String confpassword,
            RedirectAttributes redirectAttributes) {
        //Se pueden poner más comprobaciones/verificaciones
        if (password.equals(confpassword)) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            password = encoder.encode(password);

            Rol rol = new Rol();
            rol.setNombre("ROLE_USER");
            rol = rolService.encontrar(rol);

            usuarioService.guardar(new Usuario(usuario, email, password, rol));
            return "redirect:/login";
        } else {
            String mensaje = "Las contraseñas no coinciden";
            redirectAttributes.addAttribute("mensaje", mensaje);
            return "redirect:/registrarse";
        }
    }

    @GetMapping("/")
    public String inicio(Model model, @RequestParam(value = "mensaje", required = false) String mensaje) {
        var lugares = lugarService.listar();
        var actividades = actividadService.listarAleatorios();
        model.addAttribute("lugares", lugares);
        model.addAttribute("actividades", actividades);
        model.addAttribute("mensaje", mensaje);
        return "index";
    }

    @GetMapping("/mostrar-lugar/{idLugar}")
    public String detalles(Model model, Lugar lugar) {
        lugar = lugarService.encontrar(lugar);
        int numero = (int) (Math.random() * 4 + 1);
        model.addAttribute("lugar", lugar);
        model.addAttribute("numero", numero);
        return "lugar";
    }

    @GetMapping("/listar-actividades")
    public String verActividades(Model model) {
        var actividades = actividadService.listar();
        model.addAttribute("actividades", actividades);
        return "actividades";
    }

    @PostMapping("/contactar")
    public String contactar(RedirectAttributes redirectAttributes, String nombre, String email, String comentario) {
        Contacto contacto = new Contacto();
        contacto.setEmail(email);
        if (contactoService.encontrarContactoPorEmail(contacto) == null) {
            contacto.setNombre(nombre);
            contacto.setComentario(comentario);
            contactoService.guardar(contacto);
            redirectAttributes.addAttribute("mensaje", "Enviado. ¡Muchas gracias!");
        } else {
            redirectAttributes.addAttribute("mensaje", "Usted ya envío un mensaje con esta dirección de Email.");
        }
        return "redirect:/";
    }
}
