package com.mgbt.web;

import com.mgbt.domain.*;
import com.mgbt.service.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("cuenta/")
public class ControladorCuenta {

    @Autowired
    AuditoriaServiceImpl auditoriaService;

    @Autowired
    UsuarioServiceImpl usuarioService;

    @GetMapping("/")
    public String detalles(Authentication auth, Model model) {
        Usuario usuario = usuarioService.encontrarPorNombre(auth.getName());
        model.addAttribute("usuario", usuario);
        return "cuenta-detalles";
    }

    @GetMapping("/compras")
    public String compras(Authentication auth, Model model,
            @RequestParam(value = "mensajeError", required = false) String mensaje) {
        List<Auditoria> auditorias = auditoriaService.encontrarPorTipoYUsuario(2, auth.getName());
        Collections.reverse(auditorias);
        model.addAttribute("auditorias", auditorias);
        model.addAttribute("mensajeErrorCuentaDetalles", mensaje);
        return "cuenta-compras";
    }

    @GetMapping("/compras/reembolso/{idAuditoria}")
    public String reembolso(Auditoria auditoria, RedirectAttributes redirectAttributes, Authentication auth) {
        auditoria = auditoriaService.encontrar(auditoria);
        Date date = new Date();
        if (auditoria.getFecha().getDate() == date.getDate() || auditoria.getFecha().getDate() == date.getDate() - 1
                || auditoria.getFecha().getDate() == date.getDate() - 2) {

            auditoria.setAccion(auditoria.getAccion().replace("Compra:", "Reembolso:"));
            auditoria.setFecha(date);
            auditoria.setTipo(3);
            auditoriaService.guardar(auditoria);
            return "redirect:/cuenta/compras";
        } else {
            redirectAttributes.addAttribute("mensajeError", "Solo se puede reembolsar una compra dos días"
                    + " despues de realizada dicha compra.");
            return "redirect:/cuenta/compras";
        }
    }

    @PostMapping("/cambiar-usuario")
    public String actualizarCredenciales(Usuario usuario, Authentication auth) {
        Usuario usuarioBD = usuarioService.encontrar(usuario);
        if (usuario.getUsername() != null) {
            usuarioBD.setUsername(usuario.getUsername());
        }
        if (usuario.getEmail() != null) {
            usuarioBD.setEmail(usuario.getEmail());
        }
        if (usuario.getPassword() != null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String password = encoder.encode(usuario.getPassword());
            usuarioBD.setPassword(password);
        }
        if (usuario.getUsername() != null || usuario.getEmail() != null || usuario.getPassword() != null) {
            usuarioService.guardar(usuarioBD);
            return "/login";
        } else {
            return "redirect:/cuenta/";
        }
    }

    @PostMapping("/eliminar-usuario")
    public String eliminarUsuario(int idUsuario) {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);
        usuario = usuarioService.encontrar(usuario);
        usuarioService.eliminar(usuario);
        return "/login";
    }
}
