package com.muebleria.polizas.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import com.muebleria.polizas.utils.BaseResponse;
import com.muebleria.polizas.utils.ResponseActualizar;
import com.muebleria.polizas.utils.ResponseConsultar;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BaseController {

    @GetMapping("/respuesta-ejemplo")
    public RespuestaEjemplo obtenerRespuestaEjemplo() {
        return new RespuestaEjemplo();
    }

    static class RespuestaEjemplo {
        private Meta meta;
        private Data data;

        public RespuestaEjemplo() {
            this.meta = new Meta("OK");
            this.data = new Data(
                    new Poliza(12, 1),
                    new Empleado("Pedro", "Ramos"),
                    new DetalleArticulo(200500, "Trapeador")
            );
        }

        public Meta getMeta() {
            return meta;
        }

        public Data getData() {
            return data;
        }
    }

    static class Meta {
        private String status;

        public Meta(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }
    }

    static class Data {
        private Poliza poliza;
        private Empleado empleado;
        private DetalleArticulo detalleArticulo;

        public Data(Poliza poliza, Empleado empleado, DetalleArticulo detalleArticulo) {
            this.poliza = poliza;
            this.empleado = empleado;
            this.detalleArticulo = detalleArticulo;
        }

        public Poliza getPoliza() {
            return poliza;
        }

        public Empleado getEmpleado() {
            return empleado;
        }

        public DetalleArticulo getDetalleArticulo() {
            return detalleArticulo;
        }
    }

    static class Poliza {
        private int idPoliza;
        private int cantidad;

        public Poliza(int idPoliza, int cantidad) {
            this.idPoliza = idPoliza;
            this.cantidad = cantidad;
        }

        public int getIdPoliza() {
            return idPoliza;
        }

        public int getCantidad() {
            return cantidad;
        }
    }

    static class Empleado {
        private String nombre;
        private String apellido;

        public Empleado(String nombre, String apellido) {
            this.nombre = nombre;
            this.apellido = apellido;
        }

        public String getNombre() {
            return nombre;
        }

        public String getApellido() {
            return apellido;
        }
    }

    static class DetalleArticulo {
        private int sku;
        private String nombre;

        public DetalleArticulo(int sku, String nombre) {
            this.sku = sku;
            this.nombre = nombre;
        }

        public int getSku() {
            return sku;
        }

        public String getNombre() {
            return nombre;
        }
    }
}