package com.muebleria.polizas.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.muebleria.polizas.models.Empleado;
import com.muebleria.polizas.utils.DataMessage;
import com.muebleria.polizas.utils.Meta;
import com.muebleria.polizas.models.Poliza;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.muebleria.polizas.dto.*;
import com.muebleria.polizas.services.PolizaService;
import com.muebleria.polizas.utils.BaseResponseConsultar;
import com.muebleria.polizas.utils.Constants;
import org.yaml.snakeyaml.events.Event;

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
            List<ConsultaPolizaResult> polizasPrint = polizaService.consultarPolizaPorEmpleado(id);
            List<PolizaResponse> polizaResponses = new ArrayList<>();
            BaseResponseConsultar<List<PolizaResponse>> response = new BaseResponseConsultar<List<PolizaResponse>>();
            Meta meta = new Meta();
            if(polizasPrint.isEmpty()){
                meta.setStatus(Constants.MESSAGE_FAILURE);
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
            meta.setStatus(Constants.MESSAGE_OK);
            response.setMeta(meta);
            response.setData(polizaResponses);
            return ResponseEntity.ok(response);
        }catch (Exception e)
        {
            logger.error("Ha ocurrido un error al obtener polizas para el empleado con ID {}: {}", id, e.getMessage(), e);
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping("")
    public  ResponseEntity<BaseResponseConsultar<PolizaResponse>> savePoliza(@RequestBody Poliza poliza)
    {
         BaseResponseConsultar<PolizaResponse> response = new BaseResponseConsultar<PolizaResponse>();

         int idPolizaGenerada =
                 polizaService.savePoliza(poliza.getEmpleadoGenero(), poliza.getSKU(), poliza.getCantidad(), new Date());

        Meta meta = new Meta();
        PolizaResponse polizaResponse = new PolizaResponse();
        if(idPolizaGenerada != 0){
            meta.setStatus(Constants.MESSAGE_OK);
            ConsultaPolizaResult consultaPoliza = polizaService.consultarPoliza(idPolizaGenerada);

            polizaResponse.setPoliza(new PolizaDto(consultaPoliza.getIdPoliza(), consultaPoliza.getCantidadPoliza()));
            polizaResponse.setEmpleado(new EmpleadoDto(consultaPoliza.getNombreEmpleado(), consultaPoliza.getApellidoEmpleado()));
            polizaResponse.setDetalleArticulo(new DetalleArticuloDto(consultaPoliza.getSKUDetalleArticulo(), consultaPoliza.getNombreDetalleArticulo()));
        }else{
            meta.setStatus(Constants.MESSAGE_FAILURE);
        }
        response.setData(polizaResponse);
        response.setMeta(meta);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{idPoliza}")
    public ResponseEntity<BaseResponseConsultar<DataMessage>> removePoliza(@RequestBody Poliza poliza){
        BaseResponseConsultar<DataMessage> response = new BaseResponseConsultar<DataMessage>();

        boolean success = polizaService.editPoliza(poliza.getIdPoliza(),poliza.getEmpleadoGenero(), poliza.getSKU(), poliza.getCantidad());
        String status;
        String IDMensaje;
        if(success){
            status = Constants.MESSAGE_OK;
            IDMensaje = "Se actualizó correctamente la poliza ## ";
        }else{
            status = Constants.MESSAGE_FAILURE;
            IDMensaje = "Ha ocurrido un error al intentar actualizar la póliza";
        }
        Meta meta = new Meta(status);
        DataMessage dataMessage = new DataMessage(IDMensaje);
        response.setMeta(meta);
        response.setData(dataMessage);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{idPoliza}")
    public ResponseEntity<BaseResponseConsultar<DataMessage>> removePoliza(@PathVariable int idPoliza)
    {
        boolean success = polizaService.removePoliza(idPoliza);
        BaseResponseConsultar<DataMessage> response = new BaseResponseConsultar<DataMessage>();
        String status;
        String IDMensaje;
        if(success)
        {
            status = Constants.MESSAGE_OK;
            IDMensaje = "Se eliminó correctamente la poliza ##";
        }
        else{
            status = Constants.MESSAGE_FAILURE;
            IDMensaje = "Ha ocurrido un error al intentar eliminar la póliza.";
        }
        Meta meta = new Meta(status);
        DataMessage dataMessage = new DataMessage(IDMensaje);
        response.setData(dataMessage);
        response.setMeta(meta);
        return ResponseEntity.ok(response);
    }



}
