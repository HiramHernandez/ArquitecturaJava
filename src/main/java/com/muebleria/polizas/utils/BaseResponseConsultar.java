package com.muebleria.polizas.utils;
import com.muebleria.polizas.models.Poliza;
import com.muebleria.polizas.models.Empleado;

public class BaseResponseConsultar<T> {
    private Meta Meta;
    private T Data;

    public BaseResponseConsultar(){}
    public BaseResponseConsultar(Meta Meta, T Data){
        this.Meta = Meta;
        this.Data = Data;
    }
    public Meta getMeta() {
        return Meta;
    }
    public void setMeta(Meta meta) {
        this.Meta = meta;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }
}
