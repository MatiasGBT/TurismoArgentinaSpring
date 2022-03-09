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
            double precio, Model model, Authentication auth) throws IOException {

        lugar = lugarService.encontrar(lugar);
        lugar.setNombre(nombre);
        lugar.setPrecio(precio);

        System.out.println(descripcion);
        if (!descripcion.equals("")) {
            lugar.setDescripcion(descripcion);
        }

        if (!portada.isEmpty()) {
            eliminarArchivo(lugar.getPortada());
            lugar.setPortada(guardarImagen(portada, nombre, "lugares", "portada"));
        }
        if (!foto1.isEmpty()) {
            eliminarArchivo(lugar.getFoto1());
            lugar.setFoto1(guardarImagen(foto1, nombre, "lugares", "foto1"));
        }
        if (!foto2.isEmpty()) {
            eliminarArchivo(lugar.getFoto2());
            lugar.setFoto2(guardarImagen(foto2, nombre, "lugares", "foto2"));
        }
        if (!foto3.isEmpty()) {
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
        realizarAuditoría("Eliminar lugar", lugar.getNombre(), auth);
        lugarService.eliminar(lugar);
        eliminarArchivo(lugar.getPortada());
        eliminarArchivo(lugar.getFoto1());
        eliminarArchivo(lugar.getFoto2());
        eliminarArchivo(lugar.getFoto3());
        return "redirect:/panel/";
    }

    @GetMapping("/agregar-lugar")
    public String agregarLugar() {
        return "layout/panel/acciones/agregar-lugar";
    }

    @PostMapping("/insertar-lugar")
    public String insertarLugar(String nombre, String descripcion,
            @RequestParam("portada") MultipartFile portada, @RequestParam("foto1") MultipartFile foto1,
            @RequestParam("foto2") MultipartFile foto2, @RequestParam("foto3") MultipartFile foto3,
            double precio, Model model, Authentication auth) throws IOException {

        //Se agrega el lugar a la BD
        Lugar lugar = new Lugar();
        lugar.setNombre(nombre);
        lugar.setDescripcion(descripcion);
        //Se guardan las imagenes en el proyecto y retorna los nombres de las rutas a almacenar en la BD
        lugar.setPortada(guardarImagen(portada, nombre, "lugares", "portada"));
        lugar.setFoto1(guardarImagen(foto1, nombre, "lugares", "foto1"));
        lugar.setFoto2(guardarImagen(foto2, nombre, "lugares", "foto2"));
        lugar.setFoto3(guardarImagen(foto3, nombre, "lugares", "foto3"));
        lugar.setPrecio(precio);
        lugarService.guardar(lugar);

        realizarAuditoría("Agregar lugar", lugar.getNombre(), auth);
        return "redirect:/panel/";
    }

    //  ---  ACTIVIDADES  ---
    @GetMapping("/mostrar-actividad/{idActividad}")
    public String mostrarActividad(Actividad actividad, Model model) {
        actividad = actividadService.encontrar(actividad);
        model.addAttribute("actividad", actividad);
        return "layout/panel/acciones/mostrar-actividad";
    }

    @GetMapping("/editar-actividad/{idActividad}")
    public String editarActividad(Actividad actividad, Model model) {
        actividad = actividadService.encontrar(actividad);
        model.addAttribute("actividad", actividad);
        return "layout/panel/acciones/editar-actividad";
    }

    @PostMapping("/modificar-actividad/{idActividad}")
    public String modificarActividad(Actividad actividad, String nombre,
            @RequestParam(name = "fotoImagen", required = false) MultipartFile imagen,
            double precio, Model model, Authentication auth) throws IOException {

        actividad = actividadService.encontrar(actividad);
        actividad.setNombre(nombre);
        actividad.setPrecio(precio);

        if (!imagen.isEmpty()) {
            eliminarArchivo(actividad.getImagen());
            actividad.setImagen(guardarImagen(imagen, nombre, "actividades", "imagen"));
        }
        actividadService.guardar(actividad);

        realizarAuditoría("Editar actividad", actividad.getNombre(), auth);
        return "redirect:/panel/";
    }
    
    @GetMapping("/borrar-actividad/{idActividad}")
    public String borrarActividad(Actividad actividad, Authentication auth) {
        actividad = actividadService.encontrar(actividad);
        realizarAuditoría("Eliminar actividad", actividad.getNombre(), auth);
        actividadService.eliminar(actividad);
        eliminarArchivo(actividad.getImagen());
        return "redirect:/panel/";
    }

    @GetMapping("/agregar-actividad")
    public String agregarActividad() {
        return "layout/panel/acciones/agregar-actividad";
    }

    @PostMapping("/insertar-actividad")
    public String insertarActividad(String nombre,
            @RequestParam("imagen") MultipartFile imagen, double precio,
            Model model, Authentication auth) throws IOException {

        //Se agrega la actividad a la BD
        Actividad actividad = new Actividad();
        actividad.setNombre(nombre);
        //Se guarda la imagen en el proyecto y retorna el nombre de la ruta a almacenar en la BD
        actividad.setImagen(guardarImagen(imagen, nombre, "actividades", "imagen"));
        actividad.setPrecio(precio);
        actividadService.guardar(actividad);

        realizarAuditoría("Agregar actividad", actividad.getNombre(), auth);
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

    private String guardarImagen(MultipartFile imagen, String nombre, String categoria, String tipo) throws IOException {
        int index = imagen.getOriginalFilename().indexOf(".");
        String ruta = "src/main/resources/static/img/" + categoria;
        String extension = "." + imagen.getOriginalFilename().substring(index + 1);
        String nombreFoto = nombre + " - " + tipo + extension;
        Path rutaAbsoluta = Paths.get(ruta + "//" + nombreFoto.replace(" ", ""));
        if (!imagen.isEmpty()) {
            Files.write(rutaAbsoluta, imagen.getBytes());
        }
        return "/img/" + categoria + "/" + nombreFoto.replace(" ", "");
    }
    
    private void eliminarArchivo(String path) {
        File file = new File("src/main/resources/static" + path);
        file.delete();
    }
}
