package com.msvc.vigilanciaEp.model;

public class BacteriasCasos {
    private String nombre;
    private String mes;
    private int casosHistoricos;
    private int casosPredictibles;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCasosHistoricos() {
        return casosHistoricos;
    }

    public void setCasosHistoricos(int casosHistoricos) {
        this.casosHistoricos = casosHistoricos;
    }

    public int getCasosPredictibles() {
        return casosPredictibles;
    }

    public void setCasosPredictibles(int casosPredictibles) {
        this.casosPredictibles = casosPredictibles;
    }
}