package com.muebleria.polizas.models;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="Polizas")
public class Poliza {
    @Id
    @Column(name = "IdPoliza")
    private int IdPoliza;
    @Column(name = "EmpleadoGenero")
    private int EmpleadoGenero;
    @Column(name = "SKU")
    private int SKU;
    @Column(name = "Cantidad")
    private int Cantidad;
    @Column(name = "Fecha")
    private Date Fecha;
    // Constructor, getters y setters
    public Poliza(int idPoliza, int empleadoGenero, int sku, int cantidad, Date fecha) {
        IdPoliza = idPoliza;
        EmpleadoGenero = empleadoGenero;
        SKU = sku;
        Cantidad = cantidad;
        Fecha = fecha;
    }
    public int getIdPoliza() {
        return IdPoliza;
    }
    public void setIdPoliza(int idPoliza) {
        IdPoliza = idPoliza;
    }
    public int getEmpleadoGenero() {
        return EmpleadoGenero;
    }
    public void setEmpleadoGenero(int empleadoGenero) {
        EmpleadoGenero = empleadoGenero;
    }
    public int getSKU() {
        return SKU;
    }
    public void setSKU(int SKU) {
        this.SKU = SKU;
    }
    public int getCantidad() {
        return Cantidad;
    }
    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }
    public Date getFecha() {
        return Fecha;
    }
    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

}
