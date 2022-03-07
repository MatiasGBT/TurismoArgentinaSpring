package com.mgbt.web;

import com.mgbt.domain.*;
import com.mgbt.service.*;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("panel/")
public class ControladorPanel {

    @Autowired
    IUsuarioService usuarioService;

    @Autowired
    ILugarService lugarService;

    @Autowired
    IActividadService actividadService;

    @Autowired
    IAuditoriaService auditoriaService;

    @Autowired
    IContactoService contactoService;

    @GetMapping("/")
    public String inicio(Model model) {
        //CARDS
        model.addAttribute("totalUsuarios", usuarioService.listarUsuarios().size());

        //COMPRAS (AUDITORÍAS INICIO)
        List<Auditoria> compras = auditoriaService.encontrarPorTipo(2);
        Date date = new Date();
        compras.removeIf(c -> c.getFecha().getMonth() != date.getMonth());
        model.addAttribute("compras", compras);
        model.addAttribute("totalCompras", compras.size());

        //LUGARES
        List<Lugar> lugares = lugarService.listarLugares();
        model.addAttribute("lugares", lugares);
        model.addAttribute("totalLugares", lugares.size());

        //ACTIVIDADES
        List<Actividad> actividades = actividadService.listarActividades();
        model.addAttribute("actividades", actividades);
        model.addAttribute("totalActividades", actividades.size());

        //CONTACTO
        List<Contacto> contactos = contactoService.listarContactos();
        model.addAttribute("contactos", contactos);

        //AUDITORÍA
        List<Auditoria> auditorias = auditoriaService.encontrarPorTipo(1);
        auditorias.removeIf(a -> a.getFecha().getMonth() != date.getMonth());
        model.addAttribute("auditorias", auditorias);
        model.addAttribute("totalAuditorias", auditorias.size());

        return "panel";
    }

    //LUGARES
    @GetMapping("/editar-lugar/{idLugar}")
    public String editarLugar(Lugar lugar, Model model) {
        lugar = lugarService.encontrarLugar(lugar);
        model.addAttribute("lugar", lugar);
        return "layout/panel/acciones/editar-lugar";
    }

    @GetMapping("/borrar-lugar/{idLugar}")
    public String borrarLugar(Lugar lugar, Authentication auth) {
        lugar = lugarService.encontrarLugar(lugar);
        realizarAuditoría("Eliminar lugar", lugar.getNombre(), auth);
        lugarService.eliminar(lugar);
        return "redirect:/panel/";
    }

    @GetMapping("/agregar-lugar")
    public String agregarLugar() {
        return "layout/panel/acciones/agregar-lugar";
    }

    //ACTIVIDADES
    @GetMapping("/mostrar-actividad/{idActividad}")
    public String mostrarActividad(Actividad actividad, Model model) {
        actividad = actividadService.encontrarActividad(actividad);
        model.addAttribute("actividad", actividad);
        return "layout/panel/acciones/mostrar-actividad";
    }

    @GetMapping("/editar-actividad/{idActividad}")
    public String editarActividad(Actividad actividad, Model model) {
        actividad = actividadService.encontrarActividad(actividad);
        model.addAttribute("actividad", actividad);
        return "layout/panel/acciones/editar-actividad";
    }

    @GetMapping("/borrar-actividad/{idActividad}")
    public String borrarActividad(Actividad actividad, Authentication auth) {
        actividad = actividadService.encontrarActividad(actividad);
        realizarAuditoría("Eliminar actividad", actividad.getNombre(), auth);
        actividadService.eliminar(actividad);
        return "redirect:/panel/";
    }

    @GetMapping("/agregar-actividad")
    public String agregarActividad() {
        return "layout/panel/acciones/agregar-actividad";
    }

    //CONTACTOS
    @GetMapping("/mostrar-contacto/{idContacto}")
    public String mostrarContacto(Contacto contacto, Model model) {
        contacto = contactoService.encontrarContacto(contacto);
        model.addAttribute("contacto", contacto);
        return "layout/panel/acciones/mostrar-contacto";
    }

    @GetMapping("/borrar-contacto/{idContacto}")
    public String borrarContacto(Contacto contacto, Authentication auth) {
        contacto = contactoService.encontrarContacto(contacto);
        realizarAuditoría("Eliminar contacto", contacto.getNombre(), auth);
        contactoService.eliminar(contacto);
        return "redirect:/panel/";
    }

    private void realizarAuditoría(String accion, String nombre, Authentication auth) {
        Auditoria auditoria = new Auditoria();
        auditoria.setAccion(accion + ": " + nombre);
        auditoria.setUsuario(auth.getName());
        auditoria.setFecha(new Date());
        auditoria.setTipo(1);
        auditoriaService.guardar(auditoria);
    }
}
