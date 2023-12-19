package com.muebleria.polizas.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.muebleria.polizas.dto.ConsultaInventarioResult;
import com.muebleria.polizas.dto.ConsultaPolizaResult;
import com.sun.jdi.event.ExceptionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.muebleria.polizas.repositories.IInventarioRepository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Service
public class InventarioService {

    private static final Logger logger = LoggerFactory.getLogger(PolizaService.class);
    @Autowired
    private IInventarioRepository inventarioRepository;

    @Autowired
    private EntityManager entityManager;

    public List<ConsultaInventarioResult> consultarInventario() {
        List<ConsultaInventarioResult> inventarios;
        try {
            inventarios = inventarioRepository.consultarInventario();
        }catch (Exception e)
        {
            logger.error("Ha ocurrido un error al consultar los inventarios: {}", e.getMessage());
            System.out.println("Ha ocurrido un error al consultar los inventarios: " + e.getMessage());
            inventarios = new ArrayList<>();
        }
        return inventarios;
    }

    public boolean saveIneventario(int sku, String nombre, int cantidad)
    {
        try{
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("InsertarInventario");

            query.registerStoredProcedureParameter("SKU", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("Nombre", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("Cantidad", Integer.class, ParameterMode.IN);

            query.setParameter("SKU", sku);
            query.setParameter("Nombre", nombre);
            query.setParameter("Cantidad", cantidad);

            query.execute();
            return true;
        }catch (Exception e){
            logger.error("Ha ocurrido un error al guardar el inventario: {}", e.getMessage());
            return false;
        }

    }

    public boolean editInventario(int idInventario, int sku, String nombre, int cantidad)
    {
        try{
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("ActualizarInventario");

            query.registerStoredProcedureParameter("IdInventario", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("SKU", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("Nombre", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("Cantidad", Integer.class, ParameterMode.IN);

            query.setParameter("IdInventario", idInventario);
            query.setParameter("SKU", sku);
            query.setParameter("Nombre", nombre);
            query.setParameter("Cantidad", cantidad);

            query.execute();
            return true;
        }catch (Exception e){
            logger.error("Ha ocurrido un error al actualizar el inventario: {}", e.getMessage());
            return false;
        }

    }

}
