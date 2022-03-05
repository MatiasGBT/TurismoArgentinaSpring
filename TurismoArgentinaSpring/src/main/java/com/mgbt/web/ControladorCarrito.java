package com.mgbt.web;

import com.mgbt.domain.*;
import com.mgbt.service.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("carrito/")
public class ControladorCarrito {

    @Autowired
    private ILugarService lugarService;

    @Autowired
    private IActividadService actividadService;

    @Autowired
    private IAuditoriaService auditoriaService;

    List<Lugar> lugares = new ArrayList<>();
    List<Actividad> actividades = new ArrayList<>();
    double total = 0;

    @GetMapping("/")
    public String verCarrito(Model model) {
        String mensaje = "";

        total = lugares.stream().map(l -> l.getPrecio()).reduce(total, (accumulator, _item) -> accumulator + _item);
        total = actividades.stream().map(a -> a.getPrecio()).reduce(total, (accumulator, _item) -> accumulator + _item);

        //Algoritmo para verificar si hay alguna actividad agregada al carrito la cual no posea su sitio turístico definido.
        boolean existe = false;
        if (!lugares.isEmpty() && !actividades.isEmpty()) {
            for (Actividad a : actividades) {
                for (Lugar l : lugares) {
                    if (!a.getNombre().contains("(" + l.getNombre() + ")")) {
                        existe = true;
                    } else {
                        existe = false;
                        break;
                    }
                }
                if (existe) {
                    break;
                }
            }
            if (existe) {
                mensaje = "Advertencia: alguna/s de las actividades seleccionadas no corresponden a los lugares seleccionados en el carrito.";
            }
        }

        model.addAttribute("lugaresCarrito", lugares);
        model.addAttribute("actividadesCarrito", actividades);
        model.addAttribute("total", total);
        model.addAttribute("mensaje", mensaje);
        return "carrito";
    }

    @GetMapping("/reservar-lugar/{idLugar}")
    public String irFormularioLugar(Lugar lugar, Model model) {
        model.addAttribute("lugar", lugar);
        return "formulario-lugar";
    }

    @GetMapping("/reservar-actividad/{idActividad}")
    public String irFormularioActividad(Actividad actividad, Model model) {
        model.addAttribute("actividad", actividad);
        return "formulario-actividad";
    }

    @PostMapping("/agregar-lugar/{idLugar}")
    public String enviarFormularioLugar(Lugar lugar, int cantidad, Model model) {
        lugar = lugarService.encontrarLugar(lugar);
        lugar.setPrecio(lugar.getPrecio() * cantidad);
        String mensaje = "";

        //Algoritmo para detectar si el lugar seleccionado ya esta en el carrito
        boolean existe = false;
        for (Lugar l : lugares) {
            if (l.getNombre().equals(lugar.getNombre())) {
                existe = true;
                break;
            }
        }

        if (!existe) {
            lugares.add(lugar);
        } else {
            mensaje = "Advertencia: el lugar seleccionado ya se encuentra en el carrito, debe eliminarlo si quiere agregarlo de nuevo.";
        }

        model.addAttribute("mensaje", mensaje);
        return "redirect:/carrito/";
    }

    @PostMapping("/agregar-actividad/{idActividad}")
    public String enviarFormularioActividad(Actividad actividad, int cantidad, Model model) {
        actividad = actividadService.encontrarActividad(actividad);
        actividad.setPrecio(actividad.getPrecio() * cantidad);
        String mensaje = "";

        //Algoritmo para detectar si el lugar seleccionado ya esta en el carrito
        boolean existe = false;
        for (Actividad a : actividades) {
            if (a.getNombre().equals(actividad.getNombre())) {
                existe = true;
                break;
            }
        }

        if (!existe) {
            actividades.add(actividad);
        } else {
            mensaje = "Advertencia: la actividad seleccionada ya se encuentra en el carrito, debe eliminarlo si quiere agregarlo de nuevo.";
        }

        model.addAttribute("mensaje", mensaje);
        return "redirect:/carrito/";
    }

    @GetMapping("/borrar-lugar/{idLugar}")
    public String quitarLugarCarrito(Lugar lugar) {
        lugares.removeIf(l -> l.getIdLugar() == lugar.getIdLugar());
        return "redirect:/carrito/";
    }

    @GetMapping("/borrar-actividad/{idActividad}")
    public String quitarActividadCarrito(Actividad actividad) {
        actividades.removeIf(a -> a.getIdActividad() == actividad.getIdActividad());
        return "redirect:/carrito/";
    }

    @GetMapping("/facturar")
    public String facturar(Authentication auth) {
        if (auth != null) {
            Auditoria auditoria = new Auditoria();
            auditoria.setAccion("Compra: " + total);
            auditoria.setUsuario(auth.getName());
            auditoria.setFecha(new Date());
            auditoria.setTipo(2);
            auditoriaService.guardar(auditoria);

            lugares.clear();
            actividades.clear();
            total = 0;

            return "redirect:/";
        } else {
            return "/login";
        }
    }
}
