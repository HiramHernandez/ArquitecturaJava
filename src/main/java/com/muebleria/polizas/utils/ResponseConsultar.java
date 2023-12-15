package com.muebleria.polizas.utils;
import com.muebleria.polizas.models.Poliza;
import com.muebleria.polizas.models.Empleado;

public class ResponseConsultar {
    private Poliza Poliza;
    private Empleado Empleado;
    private DetalleArticulo DetalleArticulo;

    static class DetalleArticulo{
        private String SKU;
        private String Nombre;
    }

}
