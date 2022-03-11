package com.mgbt.web;

import com.mgbt.domain.*;
import com.mgbt.service.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("panel/")
public class ControladorPanel {

    @Autowired
    UsuarioServiceImpl usuarioService;

    @Autowired
    LugarServiceImpl lugarService;

    @Autowired
    ActividadServiceImpl actividadService;

    @Autowired
    AuditoriaServiceImpl auditoriaService;

    @Autowired
    ContactoServiceImpl contactoService;

    @GetMapping("/")
    public String inicio(Model model) {
        //CARDS
        model.addAttribute("totalUsuarios", usuarioService.listar().size());

        //COMPRAS (AUDITORÍAS INICIO)
        List<Auditoria> compras = auditoriaService.encontrarPorTipo(2);
        Date date = new Date();
        compras.removeIf(c -> c.getFecha().getMonth() != date.getMonth());
        model.addAttribute("compras", compras);
        model.addAttribute("totalCompras", compras.size());

        //LUGARES
        List<Lugar> lugares = lugarService.listar();
        model.addAttribute("lugares", lugares);
        model.addAttribute("totalLugares", lugares.size());

        //ACTIVIDADES
        List<Actividad> actividades = actividadService.listar();
        model.addAttribute("actividades", actividades);
        model.addAttribute("totalActividades", actividades.size());

        //CONTACTO
        List<Contacto> contactos = contactoService.listar();
        model.addAttribute("contactos", contactos);

        //AUDITORÍA
        List<Auditoria> auditorias = auditoriaService.encontrarPorTipo(1);
        auditorias.removeIf(a -> a.getFecha().getMonth() != date.getMonth());
        model.addAttribute("auditorias", auditorias);
        model.addAttribute("totalAuditorias", auditorias.size());

        return "panel";
    }

    //  ---  LUGARES  ---
    @GetMapping("/agregar-lugar")
    public String agregarLugar(@RequestParam(value = "mensajeErrorLugar", required = false) String mensaje, Model model) {
        model.addAttribute("mensajeErrorLugar", mensaje);
        return "layout/panel/acciones/agregar-lugar";
    }

    @PostMapping("/insertar-lugar")
    public String insertarLugar(String nombre, String descripcion,
            @RequestParam("portada") MultipartFile portada, @RequestParam("foto1") MultipartFile foto1,
            @RequestParam("foto2") MultipartFile foto2, @RequestParam("foto3") MultipartFile foto3,
            double precio, RedirectAttributes redirectAttributes, Authentication auth) throws IOException {

        Lugar lugar = new Lugar();
        lugar.setNombre(nombre);
        lugar.setDescripcion(descripcion);
        lugar.setPrecio(precio);

        String mensaje;
        mensaje = guardarImagen(portada, nombre, "lugares", "portada");
        if (mensaje.contains("Error")) {
            redirectAttributes.addAttribute("mensajeErrorLugar", mensaje);
            return "redirect:/panel/agregar-lugar";
        } else {
            lugar.setPortada(mensaje);
        }

        mensaje = guardarImagen(foto1, nombre, "lugares", "foto1");
        if (mensaje.contains("Error")) {
            redirectAttributes.addAttribute("mensajeErrorLugar", mensaje);
            return "redirect:/panel/agregar-lugar";
        } else {
            lugar.setFoto1(mensaje);
        }

        mensaje = guardarImagen(foto2, nombre, "lugares", "foto2");
        if (mensaje.contains("Error")) {
            redirectAttributes.addAttribute("mensajeErrorLugar", mensaje);
            return "redirect:/panel/agregar-lugar";
        } else {
            lugar.setFoto2(mensaje);
        }

        mensaje = guardarImagen(foto3, nombre, "lugares", "foto3");
        if (mensaje.contains("Error")) {
            redirectAttributes.addAttribute("mensajeErrorLugar", mensaje);
            return "redirect:/panel/agregar-lugar";
        } else {
            lugar.setFoto3(mensaje);
        }

        lugarService.guardar(lugar);
        realizarAuditoría("Agregar lugar", lugar.getNombre(), auth);
        return "redirect:/panel/";
    }
    
    @GetMapping("/editar-lugar/{idLugar}")
    public String editarLugar(Lugar lugar, Model model) {
        lugar = lugarService.encontrar(lugar);
        model.addAttribute("lugar", lugar);
        return "layout/panel/acciones/editar-lugar";
    }

    @PostMapping("/modificar-lugar/{idLugar}")
    public String modificarLugar(Lugar lugar, String nombre, String descripcion,
            @RequestParam(name = "imagenPortada", required = false) MultipartFile portada,
            @RequestParam(name = "imagenFoto1", required = false) MultipartFile foto1,
            @RequestParam(name = "imagenFoto2", required = false) MultipartFile foto2,
            @RequestParam(name = "imagenFoto3", required = false) MultipartFile foto3,
            double precio, Authentication auth) throws IOException {

        lugar = lugarService.encontrar(lugar);
        lugar.setNombre(nombre);
        lugar.setPrecio(precio);

        System.out.println(descripcion);
        if (!descripcion.equals("")) {
            lugar.setDescripcion(descripcion);
        }

        if (!portada.isEmpty() && portada.getSize() <= 5000000) {
            eliminarArchivo(lugar.getPortada());
            lugar.setPortada(guardarImagen(portada, nombre, "lugares", "portada"));
        }
        if (!foto1.isEmpty() && foto1.getSize() <= 5000000) {
            eliminarArchivo(lugar.getFoto1());
            lugar.setFoto1(guardarImagen(foto1, nombre, "lugares", "foto1"));
        }
        if (!foto2.isEmpty() && foto2.getSize() <= 5000000) {
            eliminarArchivo(lugar.getFoto2());
            lugar.setFoto2(guardarImagen(foto2, nombre, "lugares", "foto2"));
        }
        if (!foto3.isEmpty() && foto3.getSize() <= 5000000) {
            eliminarArchivo(lugar.getFoto3());
            lugar.setFoto3(guardarImagen(foto3, nombre, "lugares", "foto3"));
        }
        lugarService.guardar(lugar);

        realizarAuditoría("Editar lugar", lugar.getNombre(), auth);
        return "redirect:/panel/";
    }

    @GetMapping("/borrar-lugar/{idLugar}")
    public String borrarLugar(Lugar lugar, Authentication auth) {
        lugar = lugarService.encontrar(lugar);
        lugarService.eliminar(lugar);
        eliminarArchivo(lugar.getPortada());
        eliminarArchivo(lugar.getFoto1());
        eliminarArchivo(lugar.getFoto2());
        eliminarArchivo(lugar.getFoto3());
        realizarAuditoría("Eliminar lugar", lugar.getNombre(), auth);
        return "redirect:/panel/";
    }
    
    
    
    //  ---  ACTIVIDADES  ---
    @GetMapping("/mostrar-actividad/{idActividad}")
    public String mostrarActividad(Actividad actividad, Model model) {
        actividad = actividadService.encontrar(actividad);
        model.addAttribute("actividad", actividad);
        return "layout/panel/acciones/mostrar-actividad";
    }

    @GetMapping("/agregar-actividad")
    public String agregarActividad(@RequestParam(value = "mensajeErrorActividad", required = false) String mensaje, Model model) {
        model.addAttribute("mensajeErrorActividad", mensaje);
        return "layout/panel/acciones/agregar-actividad";
    }

    @PostMapping("/insertar-actividad")
    public String insertarActividad(String nombre,
            @RequestParam("imagen") MultipartFile imagen, double precio,
            RedirectAttributes redirectAttributes, Authentication auth) throws IOException {

        Actividad actividad = new Actividad();
        String mensaje = guardarImagen(imagen, nombre, "actividades", "imagen");
        if (!mensaje.contains("Error")) {
            actividad.setImagen(mensaje);
            actividad.setNombre(nombre);
            actividad.setPrecio(precio);
            actividadService.guardar(actividad);
            realizarAuditoría("Agregar actividad", actividad.getNombre(), auth);
            return "redirect:/panel/";
        } else {
            redirectAttributes.addAttribute("mensajeErrorActividad", mensaje);
            return "redirect:/panel/agregar-actividad";
        }
    }
    
    @GetMapping("/editar-actividad/{idActividad}")
    public String editarActividad(Actividad actividad, @RequestParam(value = "mensajeErrorActividad", required = false) String mensaje,
            Model model) {
        actividad = actividadService.encontrar(actividad);
        model.addAttribute("actividad", actividad);
        model.addAttribute("mensajeErrorActividad", mensaje);
        return "layout/panel/acciones/editar-actividad";
    }

    @PostMapping("/modificar-actividad/{idActividad}")
    public String modificarActividad(Actividad actividad, String nombre,
            @RequestParam(name = "fotoImagen", required = false) MultipartFile imagen,
            double precio, RedirectAttributes redirectAttributes, Authentication auth) throws IOException {

        actividad = actividadService.encontrar(actividad);
        actividad.setNombre(nombre);
        actividad.setPrecio(precio);

        if (!imagen.isEmpty() && imagen.getSize() <= 5000000) {
            eliminarArchivo(actividad.getImagen());
            actividad.setImagen(guardarImagen(imagen, nombre, "actividades", "imagen"));
            actividadService.guardar(actividad);
            realizarAuditoría("Editar actividad", actividad.getNombre(), auth);
            return "redirect:/panel/";
        } else {
            redirectAttributes.addAttribute("mensajeErrorActividad", "La imagen esta vacía o pesa más de 5MB.");
            return "redirect:/panel/editar-actividad/" + actividad.getIdActividad();
        }
    }

    @GetMapping("/borrar-actividad/{idActividad}")
    public String borrarActividad(Actividad actividad, Authentication auth) {
        actividad = actividadService.encontrar(actividad);
        realizarAuditoría("Eliminar actividad", actividad.getNombre(), auth);
        actividadService.eliminar(actividad);
        eliminarArchivo(actividad.getImagen());
        return "redirect:/panel/";
    }

    

    //  ---  CONTACTOS  ---
    @GetMapping("/mostrar-contacto/{idContacto}")
    public String mostrarContacto(Contacto contacto, Model model) {
        contacto = contactoService.encontrar(contacto);
        model.addAttribute("contacto", contacto);
        return "layout/panel/acciones/mostrar-contacto";
    }

    @GetMapping("/borrar-contacto/{idContacto}")
    public String borrarContacto(Contacto contacto, Authentication auth) {
        contacto = contactoService.encontrar(contacto);
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

    //Este método guarda una imagen en el proyecto. Ruta: src/main/resources/static/img/lugares o actividades
    private String guardarImagen(MultipartFile imagen, String nombre, String categoria, String tipo) throws IOException {
        int index = imagen.getOriginalFilename().indexOf(".");
        String ruta = "src/main/resources/static/img/" + categoria;
        String extension = "." + imagen.getOriginalFilename().substring(index + 1);
        String nombreFoto = nombre + " - " + tipo + extension;
        Path rutaAbsoluta = Paths.get(ruta + "//" + nombreFoto.replace(" ", ""));
        if (!imagen.isEmpty() && imagen.getSize() <= 5000000) {
            Files.write(rutaAbsoluta, imagen.getBytes());
            return "/img/" + categoria + "/" + nombreFoto.replace(" ", "");
        } else {
            return "Error: La imagen esta vacía o pesa más de 5MB.";
        }
    }

    private void eliminarArchivo(String path) {
        File file = new File("src/main/resources/static" + path);
        file.delete();
    }
}
