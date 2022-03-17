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

@Controller
@RequestMapping("carrito/")
public class ControladorCarrito {

    @Autowired
    private LugarServiceImpl lugarService;

    @Autowired
    private ActividadServiceImpl actividadService;

    @Autowired
    private AuditoriaServiceImpl auditoriaService;

    @Autowired
    private UsoServiceImpl usoService;

    List<Lugar> lugares = new ArrayList<>();
    List<Actividad> actividades = new ArrayList<>();
    double total = 0;
    Uso uso;

    @GetMapping("/")
    public String verCarrito(Model model, @RequestParam(value = "uso", required = false) Uso usoCanjeado) {
        String mensaje = "";
        total = 0;
        double descuento = 0;

        lugares.forEach(l -> {
            total += l.getPrecio();
        });
        actividades.forEach(a -> {
            total += a.getPrecio();
        });
        if (usoCanjeado != null) {
            uso = usoCanjeado;
        }
        if (uso != null && uso.getCupon().getDescuento() != 0) {
            descuento = (uso.getCupon().getDescuento() * total) / 100;
        }

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
        model.addAttribute("total", total - descuento);
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
        lugar = lugarService.encontrar(lugar);
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
        actividad = actividadService.encontrar(actividad);
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
            double descuento = 0;
            if (uso != null && uso.getCupon().getDescuento() != 0) {
                descuento = (uso.getCupon().getDescuento() * total) / 100;
            }
            Auditoria auditoria = new Auditoria();
            auditoria.setAccion("Compra: " + crearAccion(lugares, actividades, total - descuento));
            auditoria.setUsuario(auth.getName());
            auditoria.setFecha(new Date());
            auditoria.setTipo(2);
            auditoriaService.guardar(auditoria);

            if (uso != null) {
                uso.setUsado(true);
                usoService.guardar(uso);
            }

            lugares.clear();
            actividades.clear();
            total = 0;
            uso = null;

            return "redirect:/";
        } else {
            return "/login";
        }
    }

    private String crearAccion(List<Lugar> lugares, List<Actividad> actividades, double total) {
        int sizeL = lugares.size() - 1;
        int sizeA = actividades.size() - 1;
        String lugar;
        String actividad;

        lugar = switch (lugares.size()) {
            case 0 ->
                null;
            case 1 ->
                lugares.get(0).getNombre();
            default ->
                lugares.get(0).getNombre() + " + " + sizeL;
        };

        actividad = switch (actividades.size()) {
            case 0 ->
                null;
            case 1 ->
                actividades.get(0).getNombre();
            default ->
                actividades.get(0).getNombre() + " + " + sizeA;
        };

        if (lugar != null && actividad != null) {
            return lugar + ", " + actividad + ", Total: " + total;
        } else if (lugar != null && actividad == null) {
            return lugar + ", Total: " + total;
        } else {
            return actividad + ", Total: " + total;
        }
    }
}
