package com.muebleria.polizas.utils;

public class DataMessage {
    private Message Mensaje;
    private String IDMensaje;
    public DataMessage(String IDMensaje) {
        IDMensaje = IDMensaje;
        Mensaje = new Message(IDMensaje);
    }

    public Message getMensaje() { return Mensaje; }

    public void setMensaje(Message mensaje) { Mensaje = mensaje; }

    public class Message{
        private String IDMensaje;
        public Message(String idMensaje) {
            IDMensaje = idMensaje;
        }
        public String getIDMensaje() {
            return IDMensaje;
        }
    }

}


