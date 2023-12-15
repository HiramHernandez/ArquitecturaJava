package com.muebleria.polizas.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ConsultaPolizaResult {
    @Id
    private int IdPoliza;
    private int CantidadPoliza;
    private String NombreEmpleado;
    private String ApellidoEmpleado;
    private int SKUDetalleArticulo;
    private String NombreDetalleArticulo;
}
