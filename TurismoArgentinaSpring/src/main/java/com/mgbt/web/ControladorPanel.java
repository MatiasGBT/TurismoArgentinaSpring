package com.mgbt.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("panel/")
public class ControladorPanel {
    
    @GetMapping("/")
    public String inicio() {
        return "registro";
    }
}
