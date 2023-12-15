package com.muebleria.polizas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.transaction.annotation.Transactional;
import com.muebleria.polizas.repositories.IPolizaRepository;
import com.muebleria.polizas.dto.ConsultaPolizaResult;


import java.util.List;

@RestController
@RequestMapping("/api/poliza")
public class PolizaController {

    @Autowired
    private IPolizaRepository polizaRepository;

    @GetMapping("/empleado/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<List<ConsultaPolizaResult> >ObtenerPolizasPorEmpleado(@PathVariable int id)
    {
        try
        {
            List<ConsultaPolizaResult> polizasPrint = polizaRepository.consultarPolizaPorEmpleadoWithPrint(id);
            System.out.println("Polizas para el empleado con ID " + id + ": " + polizasPrint);
            return ResponseEntity.ok(polizasPrint);
        }catch (Exception e)
        {
            System.out.println("Ha ocurrido el siguiente error: " + e.getMessage());
        }
        return ResponseEntity.ok(null);
    }

}
