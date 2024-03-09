package com.msvc.vigilanciaEp.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "VigilanciaCDMX")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class Vigilancia {

    @Id
    private String _id;
    private String alcaldia;

    private List<VirusCasos> virusCasos;
    private List<BacteriasCasos> bacteriasCasos;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAlcaldia() {
        return alcaldia;
    }

    public void setAlcaldia(String alcaldia) {
        this.alcaldia = alcaldia;
    }

    public List<VirusCasos> getVirusCasos() {
        return virusCasos;
    }

    public void setVirusCasos(List<VirusCasos> virusCasos) {
        this.virusCasos = virusCasos;
    }

    public List<BacteriasCasos> getBacteriasCasos() {
        return bacteriasCasos;
    }

    public void setBacteriasCasos(List<BacteriasCasos> bacteriasCasos) {
        this.bacteriasCasos = bacteriasCasos;
    }
}
