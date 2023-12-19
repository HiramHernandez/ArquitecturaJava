package com.muebleria.polizas.controllers;

import java.util.List;

import com.muebleria.polizas.utils.BaseResponseConsultar;
import com.muebleria.polizas.utils.DataMessage;
import com.muebleria.polizas.utils.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.muebleria.polizas.models.Empleado;
import com.muebleria.polizas.services.EmpleadoService;
import com.muebleria.polizas.utils.Constants;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;
    @GetMapping("")
    public ResponseEntity<BaseResponseConsultar<List<Empleado>>> getListEmpleados(){
        BaseResponseConsultar<List<Empleado>> response =
                new BaseResponseConsultar<List<Empleado>>();
        List<Empleado> empleados = empleadoService.getListEmpleados();
        String status = (empleados.isEmpty()) ? Constants.MESSAGE_FAILURE: Constants.MESSAGE_OK;
        Meta meta = new Meta();
        meta.setStatus(status);
        response.setMeta(meta);
        response.setData(empleados);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public  ResponseEntity<BaseResponseConsultar<DataMessage>> saveEmpleado(@RequestBody Empleado empleado)
    {
        boolean success =
                empleadoService.saveEmpleado(empleado.getNombre(), empleado.getApellido(), empleado.getPuesto());
        BaseResponseConsultar<DataMessage> response = new BaseResponseConsultar<DataMessage>();

        String status = "";
        String IDMensaje;
        if(success){
            status = Constants.MESSAGE_CREATE;
            IDMensaje = "Se creó el empleado";
        }else{
            status = Constants.MESSAGE_INTERNAL_ERRROR;
            IDMensaje = "Ocurrio un error";
        }
        Meta meta = new Meta(status);
        DataMessage dataMessage = new DataMessage(IDMensaje);
        response.setMeta(meta);
        response.setData(dataMessage);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{idEmpleado}")
    public  ResponseEntity<BaseResponseConsultar<DataMessage>> editEmpleado(@RequestBody Empleado empleado, @PathVariable Integer idEmpleado)
    {
        boolean success =
                empleadoService.editmpleado(idEmpleado, empleado.getNombre(), empleado.getApellido(), empleado.getPuesto());
        BaseResponseConsultar<DataMessage> response = new BaseResponseConsultar<DataMessage>();

        String status = "";
        String IDMensaje;
        if(success){
            status = Constants.MESSAGE_CREATE;
            IDMensaje = "Se actualizó el empleado";
        }else{
            status = Constants.MESSAGE_INTERNAL_ERRROR;
            IDMensaje = "Ocurrio un error";
        }
        Meta meta = new Meta(status);
        DataMessage dataMessage = new DataMessage(IDMensaje);
        response.setMeta(meta);
        response.setData(dataMessage);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{idEmpleado}")
    public  ResponseEntity<BaseResponseConsultar<DataMessage>> removeEmpleado(@PathVariable Integer idEmpleado)
    {
        boolean success = empleadoService.removeEmpleado(idEmpleado);
        BaseResponseConsultar<DataMessage> response = new BaseResponseConsultar<DataMessage>();
        String status;
        String IDMensaje;
        if(success)
        {
            status = Constants.MESSAGE_OK;
            IDMensaje = "Se eliminó correctamente el empleado ##";
        }
        else{
            status = Constants.MESSAGE_FAILURE;
            IDMensaje = "Ha ocurrido un error al intentar eliminar el empleado.";
        }
        Meta meta = new Meta(status);
        DataMessage dataMessage = new DataMessage(IDMensaje);
        response.setData(dataMessage);
        response.setMeta(meta);
        return ResponseEntity.ok(response);
    }

}
