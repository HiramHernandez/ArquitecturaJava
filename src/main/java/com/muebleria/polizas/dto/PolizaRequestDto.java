package com.muebleria.polizas.dto;



public class PolizaRequestDto {
    private int EmpleadoGenero;
    private int SKU;
    private int Cantidad;

    public PolizaRequestDto(){}

    public int getEmpleadoGenero() { return EmpleadoGenero; }
    public void setEmpleadoGenero(int empleadoGenero) { EmpleadoGenero = empleadoGenero; }
    public int getSKU() { return SKU; }
    public void setSKU(int SKU) { this.SKU = SKU; }
    public int getCantidad() { return Cantidad; }
    public void setCantidad(int cantidad) { Cantidad = cantidad; }
}
