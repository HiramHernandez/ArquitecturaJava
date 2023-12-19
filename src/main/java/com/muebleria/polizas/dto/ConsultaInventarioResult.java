package com.muebleria.polizas.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class ConsultaInventarioResult {
    @Id
    private  int IdInventario;
    private int SKU;
    private String Nombre;
    private int Cantidad;

    public ConsultaInventarioResult(){}

    public ConsultaInventarioResult(int idInventario, int sku, String nombre, int cantidad){
        IdInventario = idInventario;
        SKU = sku;
        Nombre = nombre;
        Cantidad = cantidad;
    }


    public int getIdInventario() {
        return IdInventario;
    }

    public void setIdInventario(int idInventario) {
        IdInventario = idInventario;
    }

    public int getSKU() {
        return SKU;
    }

    public void setSKU(int SKU) {
        this.SKU = SKU;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }
}
