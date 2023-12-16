package com.muebleria.polizas.services;

import java.util.Date;
import java.util.List;

import com.muebleria.polizas.dto.ConsultaPolizaResult;
import com.sun.jdi.event.ExceptionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.muebleria.polizas.models.Empleado;
import com.muebleria.polizas.repositories.IPolizaRepository;

@Service
public class PolizaService {
    private static final Logger logger = LoggerFactory.getLogger(PolizaService.class);
    @Autowired
    private IPolizaRepository polizaRepository;

    public List<ConsultaPolizaResult> consultarPolizaPorEmpleadoWithPrint(int idEmpleado)
    {
        return polizaRepository.consultarPolizaPorEmpleadoWithPrint(idEmpleado);
    }

    public boolean savePoliza(String empleadoGenero, int sku, int cantidad, Date fecha)
    {
        try{
            polizaRepository.savePoliza(empleadoGenero, sku, cantidad, fecha);
            return true;
        }catch (Exception e){
            logger.error("Ha ocurrido un error al guardar la poliza: {}", e.getMessage());
        }
        return false;
    }
}
