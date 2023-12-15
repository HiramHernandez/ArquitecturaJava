package com.muebleria.polizas.models;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Inventario")
public class Inventario {
    @Id
    @Column(name = "IdInventario")
    private int IdInventario;
    @Column(name = "SKU")
    private int SKU;
    @Column(name = "Nombre")
    private String Nombre;
    @Column(name = "Cantidad")
    private int Cantidad;

    // Constructor, getters y setters
    public Inventario(int idInventario, int sku, String nombre, int cantidad) {
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
