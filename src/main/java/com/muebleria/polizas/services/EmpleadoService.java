package com.muebleria.polizas.services;

import java.util.List;

import com.sun.jdi.event.ExceptionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muebleria.polizas.models.Empleado;
import com.muebleria.polizas.repositories.IEmpleadoRepository;

@Service
public class EmpleadoService {

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
            System.out.println("Ho ocurrido un error: " + e.getMessage());
        }
        return false;
    }
}
