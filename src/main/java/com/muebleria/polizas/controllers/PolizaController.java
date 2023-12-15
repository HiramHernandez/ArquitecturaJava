package com.muebleria.polizas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.transaction.annotation.Transactional;
import com.muebleria.polizas.utils.BaseResponse;
import com.muebleria.polizas.utils.ResponseActualizar;
import com.muebleria.polizas.utils.ResponseConsultar;
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
    public List<ConsultaPolizaResult> ObtenerPolizasPorEmpleado(@PathVariable int id)
    {
        //return "Obteniendo poliza para el empleado con ID: " + id;
        try {
            /*List<ConsultaPolizaResult>  polizas = polizaRepository.consultarPolizaPorEmpleado(id);
            System.out.println("Hola");
            System.out.println(polizas);
            System.out.println("Adios");*/
            // Llamada al nuevo método con impresión
            List<ConsultaPolizaResult> polizasPrint = polizaRepository.consultarPolizaPorEmpleadoWithPrint(id);
            // Imprime los resultados o realiza otras operaciones necesarias
            System.out.println("Polizas para el empleado con ID " + id + ": " + polizasPrint);
            return polizasPrint;
        } catch (Exception e) {
            System.out.println("Ocurrio un error " + e.getMessage());
            // Loguea la excepción o imprímela para obtener más detalles
            e.printStackTrace();
            throw e; // Puedes ajustar esto según tus necesidades
        }

    }

}
