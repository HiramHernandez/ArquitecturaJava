package com.muebleria.polizas.utils;

import com.muebleria.polizas.controllers.BaseController;

public class BaseResponse<T> {
    private Meta Meta;
    private T Data;

    public BaseResponse(Meta meta, T data) {
        this.Meta = meta;
        this.Data = data;
    }

    public String getMessage() {
        return this.Meta.getStatus();
    }

    public void setMessage(String message) {
        this.Meta.status = message;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        this.Data = data;
    }
    private class Meta {
        private String status;

        public Meta(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }
    }



}

