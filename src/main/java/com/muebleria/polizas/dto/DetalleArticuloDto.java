package com.muebleria.polizas.dto;

public class DetalleArticuloDto {
    private int SKU;
    private String Nombre;

    public DetalleArticuloDto(){}

    public DetalleArticuloDto(int SKU, String Nombre)
    {
        this.SKU = SKU;
        this.Nombre = Nombre;
    }

    public int getSKU() { return SKU; }

    public void setSKU(int SKU) { this.SKU = SKU; }

    public String getNombre() { return Nombre; }

    public void setNombre(String nombre) { Nombre = nombre; }
}
