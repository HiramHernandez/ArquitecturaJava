package com.muebleria.polizas.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.muebleria.polizas.models.Empleado;
import com.muebleria.polizas.models.Inventario;
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
import com.muebleria.polizas.services.InventarioService;
import com.muebleria.polizas.utils.BaseResponseConsultar;
import com.muebleria.polizas.utils.Constants;
import com.muebleria.polizas.utils.PolizaControllerUtil;



@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    private static final Logger logger = LoggerFactory.getLogger(PolizaController.class);
    @Autowired
    private InventarioService inventarioService;

    @GetMapping("")
    @Transactional(readOnly = true)
    public ResponseEntity<BaseResponseConsultar<?>>ObtenerInventarios()
    {
        try
        {
            List<ConsultaInventarioResult> inventarios = inventarioService.consultarInventario();
            Meta meta = new Meta();
            if(inventarios.isEmpty()){
                BaseResponseConsultar<DataMessage> responseEmpty = new BaseResponseConsultar<DataMessage>();
                meta.setStatus(Constants.MESSAGE_FAILURE);
                responseEmpty.setMeta(meta);
                DataMessage dataMessage = new DataMessage(Constants.MESSAGE_QUERY_FAILURE);
                responseEmpty.setData(dataMessage);
                return ResponseEntity.ok(responseEmpty);
            }

            BaseResponseConsultar<List<ConsultaInventarioResult>> response = new BaseResponseConsultar<List<ConsultaInventarioResult>>();
            meta.setStatus(Constants.MESSAGE_OK);
            response.setMeta(meta);
            response.setData(inventarios);
            return ResponseEntity.ok(response);
        }catch (Exception e)
        {
            logger.error("Ha ocurrido un error al obtener polizas las polizas");
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping("")
    public  ResponseEntity<BaseResponseConsultar<DataMessage>> saveInvenatrio(@RequestBody Inventario inventario)
    {
        BaseResponseConsultar<DataMessage> response = new BaseResponseConsultar<DataMessage>();
        boolean success =
                inventarioService.saveIneventario(inventario.getSKU(), inventario.getNombre(), inventario.getCantidad());
        String status;
        String IDMensaje;

        if(!success){
            status = Constants.MESSAGE_OK;
            IDMensaje = "Se guardo el inventario";
        }else{
            status = Constants.MESSAGE_FAILURE;
            IDMensaje = "Ha ocurrido un error al intentar guardar el inventario";
        }
        Meta meta = new Meta(status);
        DataMessage dataMessage = new DataMessage(IDMensaje);
        response.setMeta(meta);
        response.setData(dataMessage);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{idPoliza}")
    public ResponseEntity<BaseResponseConsultar<DataMessage>> editInventario(@RequestBody Inventario inventario, @PathVariable int idInventario){
        BaseResponseConsultar<DataMessage> response = new BaseResponseConsultar<DataMessage>();

        boolean success =
                inventarioService.editInventario(idInventario, inventario.getSKU(), inventario.getNombre(), inventario.getCantidad());

        String status;
        String IDMensaje;
        if(success){
            status = Constants.MESSAGE_OK;
            IDMensaje = "Se actualizó correctamente el inventario ## ";
        }else{
            status = Constants.MESSAGE_FAILURE;
            IDMensaje = "Ha ocurrido un error al intentar actualizar el inventario";
        }
        Meta meta = new Meta(status);
        DataMessage dataMessage = new DataMessage(IDMensaje);
        response.setMeta(meta);
        response.setData(dataMessage);
        return ResponseEntity.ok(response);
    }

}
