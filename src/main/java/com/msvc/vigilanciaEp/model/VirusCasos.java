package com.msvc.vigilanciaEp.model;

import java.util.List;

public class VirusCasos {

    private String nombreVirus;
    private List<MesCasos> casos;

    public String getNombreVirus() {return nombreVirus;}

    public void setNombreVirus(String nombreVirus) {
        this.nombreVirus = nombreVirus;
    }

    public List<MesCasos> getCasos() {
        return casos;
    }

    public void setCasos(List<MesCasos> casos) {
        this.casos = casos;
    }

}