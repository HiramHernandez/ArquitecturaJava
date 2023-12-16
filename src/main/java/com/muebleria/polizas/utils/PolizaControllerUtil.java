package com.muebleria.polizas.utils;

import java.util.ArrayList;
import java.util.List;

import com.muebleria.polizas.dto.ConsultaPolizaResult;
import com.muebleria.polizas.dto.PolizaResponse;
import com.muebleria.polizas.dto.*;

public class PolizaControllerUtil {

    public List<PolizaResponse> mapPolizaResponse(List<ConsultaPolizaResult> polizas){
        List<PolizaResponse> polizasResponse = new ArrayList<>();
        for(ConsultaPolizaResult poliza : polizas)
        {
            PolizaResponse polizaResponse = new PolizaResponse();
            polizaResponse.setPoliza(new PolizaDto(poliza.getIdPoliza(), poliza.getCantidadPoliza()));
            polizaResponse.setEmpleado(new EmpleadoDto(poliza.getNombreEmpleado(), poliza.getApellidoEmpleado()));
            polizaResponse.setDetalleArticulo(new DetalleArticuloDto(poliza.getSKUDetalleArticulo(), poliza.getNombreDetalleArticulo()));
            polizasResponse.add(polizaResponse);
        }
        return polizasResponse;
    }
}
