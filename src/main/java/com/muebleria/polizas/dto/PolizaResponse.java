package com.muebleria.polizas.dto;

public class PolizaResponse{
    private PolizaDto Poliza;
    private EmpleadoDto Empleado;
    private DetalleArticuloDto DetalleArticulo;

    public PolizaResponse(){}

    public PolizaDto getPoliza() { return Poliza; }

    public void setPoliza(PolizaDto poliza) { Poliza = poliza; }

    public EmpleadoDto getEmpleado() { return Empleado; }

    public void setEmpleado(EmpleadoDto empleado) { Empleado = empleado; }

    public DetalleArticuloDto getDetalleArticulo() { return DetalleArticulo; }

    public void setDetalleArticulo(DetalleArticuloDto detalleArticulo) { DetalleArticulo = detalleArticulo; }
}
