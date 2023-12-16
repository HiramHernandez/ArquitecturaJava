package com.muebleria.polizas.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Empleado")
public class Empleado {
    @Id
    @Column(name = "IdEmpleado")
    private int IdEmpleado;
    @Column(name = "Nombre")
    private String Nombre;
    @Column(name = "Apellido")
    private String Apellido;
    @Column(name = "Puesto")
    private String Puesto;

    // Constructor, getters y setters
    public Empleado()
    {

    }
    public Empleado(int IdEmpleado, String Nombre, String Apellido, String Puesto)
    {
        this.IdEmpleado = IdEmpleado;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Puesto = Puesto;
    }
    public int getIdEmpleado() {
        return IdEmpleado;
    }
    public void setIdEmpleado(int idEmpleado) {
        IdEmpleado = idEmpleado;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    public String getApellido() {
        return Apellido;
    }
    public void setApellido(String apellido) {
        Apellido = apellido;
    }
    public String getPuesto(){
        return Puesto;
    }
    public void setPuesto(String puesto)
    {
        Puesto = puesto;
    }
}
