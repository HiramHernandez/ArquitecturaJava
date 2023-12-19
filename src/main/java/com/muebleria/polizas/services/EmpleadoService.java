package com.muebleria.polizas.services;

import java.util.List;

import com.sun.jdi.event.ExceptionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muebleria.polizas.models.Empleado;
import com.muebleria.polizas.repositories.IEmpleadoRepository;

@Service
public class EmpleadoService {
    private static final Logger logger = LoggerFactory.getLogger(EmpleadoService.class);
    @Autowired
    private IEmpleadoRepository iEmpleadoRepository;

    public List<Empleado> getListEmpleados(){
        return iEmpleadoRepository.getListEmpleados();
    }

    public boolean saveEmpleado(String nombre, String apellido, String puesto)
    {
        try{
            iEmpleadoRepository.saveEmpleado(nombre, apellido, puesto);
            return true;
        }catch (Exception e){
            logger.error("Ha ocurrido un error al guardar el empleado: {}", e.getMessage());
            return false;
        }

    }

    public boolean editmpleado(int idEmpleado, String nombre, String apellido, String puesto)
    {
        try{
            iEmpleadoRepository.editEmpleado(idEmpleado, nombre, apellido, puesto);
            return true;
        }catch (Exception e){
            logger.error("Ha ocurrido un error al guardar el empleado: {}", e.getMessage());
            return false;
        }

    }

    public boolean removeEmpleado(int idEmpleado)
    {
        try{
            iEmpleadoRepository.removeEmpleado(idEmpleado);
            return true;
        }catch (Exception e)
        {
            logger.error("Ha ocurrido un error al eliminar la poliza: {}", e.getMessage());
            return false;
        }
    }

}
