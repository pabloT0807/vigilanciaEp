package com.msvc.vigilanciaEp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MesCasos {

    private String mes;
    private int casosHistoricos;
    private int casosPredictibles;

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
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
