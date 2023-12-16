package com.muebleria.polizas.dto;

public class PolizaDto {
    private int IDPoliza;
    private int Cantidad;
    public PolizaDto(){}
    public PolizaDto(int IDPoliza, int Cantidad){
        this.IDPoliza = IDPoliza;
        this.Cantidad = Cantidad;
    }

    public int getIDPoliza() { return IDPoliza; }

    public void setIDPoliza(int IDPoliza) { this.IDPoliza = IDPoliza; }

    public int getCantidad() { return Cantidad; }

    public void setCantidad(int cantidad) { Cantidad = cantidad; }
}
