package com.muebleria.polizas.services;

import java.util.Date;
import java.util.List;

import com.muebleria.polizas.dto.ConsultaPolizaResult;
import com.sun.jdi.event.ExceptionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.muebleria.polizas.repositories.IPolizaRepository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Service
public class PolizaService {
    private static final Logger logger = LoggerFactory.getLogger(PolizaService.class);
    @Autowired
    private IPolizaRepository polizaRepository;

    @Autowired
    private EntityManager entityManager;

    public List<ConsultaPolizaResult> consultarPolizaPorEmpleado(int idEmpleado)
    {
        return polizaRepository.consultarPolizaPorEmpleado(idEmpleado);
    }

    public  ConsultaPolizaResult consultarPoliza(int idPoliza)
    {
        return polizaRepository.consultarPoliza(idPoliza);
    }

    public List<ConsultaPolizaResult> consultarPolizas()
    {
        return polizaRepository.consultarPolizas();
    }

    public int savePoliza(int empleadoGenero, int sku, int cantidad, Date fecha)
    {
        try{
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("CrearPoliza");

            query.registerStoredProcedureParameter("EmpleadoGenero", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("SKU", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("Cantidad", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("Fecha", Date.class, ParameterMode.IN);

            query.registerStoredProcedureParameter("idPoliza", Integer.class, ParameterMode.OUT);

            query.setParameter("EmpleadoGenero", empleadoGenero);
            query.setParameter("SKU", sku);
            query.setParameter("Cantidad", cantidad);
            query.setParameter("Fecha", fecha);

            query.execute();
            Integer idPoliza = (Integer) query.getOutputParameterValue("idPoliza");
            return idPoliza;
        }catch (Exception e){
            logger.error("Ha ocurrido un error al guardar la poliza: {}", e.getMessage());
            return 0;
        }

    }

    public boolean editPoliza(int idPoliza, int empleadoGenero, int sku, int cantidad)
    {
        try{
            System.out.println(idPoliza);
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("ActualizarPoliza");

            query.registerStoredProcedureParameter("IdPoliza", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("EmpleadoGenero", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("SKU", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("Cantidad", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("FechaModifico", Date.class, ParameterMode.IN);

            query.setParameter("IdPoliza", idPoliza);
            query.setParameter("EmpleadoGenero", empleadoGenero);
            query.setParameter("SKU", sku);
            query.setParameter("Cantidad", cantidad);
            query.setParameter("FechaModifico", new Date());

            query.execute();

            return true;
        }catch (Exception e){
            System.out.println("Ha ocurrido un error: " + e.getMessage());
            logger.error("Ha ocurrido un error al guardar la poliza: {}", e.getMessage());
            return false;
        }

    }

    public boolean removePoliza(int idPoliza)
    {
        try{
            polizaRepository.removePoliza(idPoliza);
            return true;
        }catch (Exception e)
        {
            logger.error("Ha ocurrido un error al eliminar la poliza: {}", e.getMessage());
            return false;
        }
    }
}
