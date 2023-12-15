package com.muebleria.polizas.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @GetMapping("")
    public String GetEmpleados()
    {
        return "Hola";
    }

    @PostMapping("")
    public String SaveEmpleado()
    {
        return "";
    }

    @PutMapping("")
    public String UpdateEmpleado()
    {
        return "";
    }

}
