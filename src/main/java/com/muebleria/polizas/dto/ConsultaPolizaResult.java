package com.muebleria.polizas.dto;

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

    public ConsultaPolizaResult(int idPoliza, int cantidadPoliza, String nombreEmpleado,
                                String apellidoEmpleado, int skuDetalleArticulo,
                                String nombreDetalleArticulo) {
        IdPoliza = idPoliza;
        CantidadPoliza = cantidadPoliza;
        NombreEmpleado = nombreEmpleado;
        ApellidoEmpleado = apellidoEmpleado;
        SKUDetalleArticulo = skuDetalleArticulo;
        NombreDetalleArticulo = nombreDetalleArticulo;
    }

    public int getIdPoliza() {
        return IdPoliza;
    }

    public void setIdPoliza(int idPoliza) {
        IdPoliza = idPoliza;
    }

    public int getCantidadPoliza() {
        return CantidadPoliza;
    }

    public void setCantidadPoliza(int cantidadPoliza) {
        CantidadPoliza = cantidadPoliza;
    }

    public String getNombreEmpleado() {
        return NombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        NombreEmpleado = nombreEmpleado;
    }

    public String getApellidoEmpleado() {
        return ApellidoEmpleado;
    }

    public void setApellidoEmpleado(String apellidoEmpleado) {
        ApellidoEmpleado = apellidoEmpleado;
    }

    public int getSKUDetalleArticulo() {
        return SKUDetalleArticulo;
    }

    public void setSKUDetalleArticulo(int SKUDetalleArticulo) {
        this.SKUDetalleArticulo = SKUDetalleArticulo;
    }

    public void setNombreDetalleArticulo(String nombreDetalleArticulo) {
        NombreDetalleArticulo = nombreDetalleArticulo;
    }
}
