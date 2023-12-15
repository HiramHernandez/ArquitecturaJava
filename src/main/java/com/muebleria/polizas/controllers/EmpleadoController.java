package com.muebleria.polizas.controllers;

import java.util.List;

import com.muebleria.polizas.utils.BaseResponseConsultar;
import com.muebleria.polizas.utils.DataMessage;
import com.muebleria.polizas.utils.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.muebleria.polizas.models.Empleado;
import com.muebleria.polizas.services.EmpleadoService;
import org.yaml.snakeyaml.events.Event;

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
        String status = (empleados.isEmpty()) ? "Not Content": "Ok";
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
            status = "Created";
            IDMensaje = "Se creó el empleado";
        }else{
            status = "Internal Error Server";
            IDMensaje = "Ocurrio un error";
        }
        Meta meta = new Meta(status);
        DataMessage dataMessage = new DataMessage(IDMensaje);
        response.setMeta(meta);
        response.setData(dataMessage);
        return ResponseEntity.ok(response);
    }

    @PutMapping("")
    public String UpdateEmpleado()
    {
        return "";
    }

}
