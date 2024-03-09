package com.msvc.vigilanciaEp.model;

import java.util.List;

public class BacteriasCasos {
    private String nombreBacterias;
    private List<MesCasos> casosBacterias;

    public String getNombreBacterias() {
        return nombreBacterias;
    }

    public void setNombreBacterias(String nombreBacterias) {
        this.nombreBacterias = nombreBacterias;
    }

    public List<MesCasos> getCasosBacterias() {
        return casosBacterias;
    }

    public void setCasosBacterias(List<MesCasos> casosBacterias) {
        this.casosBacterias = casosBacterias;
    }
}