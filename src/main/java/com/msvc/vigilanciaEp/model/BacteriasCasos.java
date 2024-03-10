package com.msvc.vigilanciaEp.model;

import java.util.List;

public class BacteriasCasos {
    private String nombreBacterias;
    private List<MesCasos> casos;

    public String getNombreBacterias() {
        return nombreBacterias;
    }

    public void setNombreBacterias(String nombreBacterias) {
        this.nombreBacterias = nombreBacterias;
    }

    public List<MesCasos> getCasos() {
        return casos;
    }

    public void setCasos(List<MesCasos> casos) {
        this.casos = casos;
    }
}