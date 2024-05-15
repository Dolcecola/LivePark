package com.example.liveparkgood;

public class DatosCompartidos {

    private static String nombre;
    private static String id;
    private static String fecha;
    private static int hora_inicio;
    private static int hora_final;

    public static String getFecha() {
        return fecha;
    }

    public static void setFecha(String fecha) {
        DatosCompartidos.fecha = fecha;
    }

    public static int getHora_inicio() {
        return hora_inicio;
    }

    public static void setHora_inicio(int hora_inicio) {
        DatosCompartidos.hora_inicio = hora_inicio;
    }

    public static int getHora_final() {
        return hora_final;
    }

    public static void setHora_final(int hora_final) {
        DatosCompartidos.hora_final = hora_final;
    }

    public static String getNombre() {
        return nombre;
    }

    public static void setNombre(String nombre) {
        DatosCompartidos.nombre = nombre;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        DatosCompartidos.id = id;
    }
}
