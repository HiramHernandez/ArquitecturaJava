package com.muebleria.polizas.controllers;

import com.muebleria.polizas.dto.*;
import com.muebleria.polizas.models.Empleado;
import com.muebleria.polizas.services.PolizaService;
import com.muebleria.polizas.utils.BaseResponseConsultar;
import com.muebleria.polizas.utils.DataMessage;
import com.muebleria.polizas.utils.Meta;
import com.muebleria.polizas.models.Poliza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.muebleria.polizas.repositories.IPolizaRepository;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/poliza")
public class PolizaController {
    private static final Logger logger = LoggerFactory.getLogger(PolizaController.class);
    @Autowired
    private PolizaService polizaService;

    @GetMapping("/empleado/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<BaseResponseConsultar<List<PolizaResponse>>>ObtenerPolizasPorEmpleado(@PathVariable int id)
    {
        try
        {
            List<ConsultaPolizaResult> polizasPrint = polizaService.consultarPolizaPorEmpleadoWithPrint(id);
            List<PolizaResponse> polizaResponses = new ArrayList<>();
            BaseResponseConsultar<List<PolizaResponse>> response = new BaseResponseConsultar<List<PolizaResponse>>();
            Meta meta = new Meta();
            if(polizasPrint.isEmpty()){
                meta.setStatus("Not Content");
                response.setMeta(meta);
                return ResponseEntity.ok(response);
            }
            for (ConsultaPolizaResult poliza: polizasPrint){
                PolizaResponse polizaResponse = new PolizaResponse();
                polizaResponse.setPoliza(new PolizaDto(poliza.getIdPoliza(), poliza.getCantidadPoliza()));
                polizaResponse.setEmpleado(new EmpleadoDto(poliza.getNombreEmpleado(), poliza.getApellidoEmpleado()));
                polizaResponse.setDetalleArticulo(new DetalleArticuloDto(poliza.getSKUDetalleArticulo(), poliza.getNombreDetalleArticulo()));
                polizaResponses.add(polizaResponse);
            }
            meta.setStatus("Ok");
            response.setMeta(meta);
            response.setData(polizaResponses);
            return ResponseEntity.ok(response);
        }catch (Exception e)
        {
            //log del error
            logger.error("Ha ocurrido un error al obtener polizas para el empleado con ID {}: {}", id, e.getMessage(), e);
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping("")
    public  ResponseEntity<BaseResponseConsultar<List<PolizaResponse>>> savePoliza(@RequestBody Poliza poliza)
    {
         BaseResponseConsultar<List<PolizaResponse>> response = new BaseResponseConsultar<List<PolizaResponse>>();
         System.out.println(poliza.getEmpleadoGenero());
         System.out.println(poliza.getSKU());
         System.out.println(poliza.getCantidad());
         System.out.println(new Date());
         boolean success =
                 polizaService.savePoliza("1", 1002, 3, new Date());
                //polizaService.savePoliza(String.valueOf(poliza.getEmpleadoGenero()), poliza.getSKU(), poliza.getCantidad(), new Date());

        Meta meta = new Meta();

        if(success){
            meta.setStatus("OK");
        }else{
            meta.setStatus("FAILURE");
        }

        return ResponseEntity.ok(response);
    }

}
