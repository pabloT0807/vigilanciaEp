package com.msvc.vigilanciaEp.service;


import com.msvc.vigilanciaEp.model.MesCasos;
import com.msvc.vigilanciaEp.model.Vigilancia;
import com.msvc.vigilanciaEp.model.VirusCasos;
import com.msvc.vigilanciaEp.repository.VigilanciaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import java.util.logging.Logger;


@Service
@Slf4j /*LOG*/
public class VigilanciaService {

    /*INYECTAMOS LOS REPOSITORIOS*/
    @Autowired
    private VigilanciaRepository vigilanciaRepository;

    private static final Logger logger = Logger.getLogger(VigilanciaService.class.getName());

    public void guardarMes(Vigilancia vigilancia) {
        vigilanciaRepository.save(vigilancia);
    }
    //bola

    public List<Vigilancia> buscarPorNombreAlcaldia(String alcaldia) {
        return vigilanciaRepository.findByAlcaldia(alcaldia);
    }

    /*metodo*/
    public void calcularCasosPredectibles(String nombreVirus, String mes) {
        List<Vigilancia> vigilancia = vigilanciaRepository.findByAlcaldia("Iztapalapa");

        for (Vigilancia vigilancia1 : vigilancia) {
            for (VirusCasos virus : vigilancia1.getVirusCasos()) {
                if (virus.getNombreVirus().equals(nombreVirus)) {
                    List<MesCasos> casos = virus.getCasos();

                    // // Buscar el mes anterior al mes actual

                    MesCasos mesAnterior = null;
                    for (MesCasos m : casos) {
                        if (m.getMes().equals(mes)) {
                            // El mes anterior es el mes anterior al mes actual
                            break;
                        }
                        // Asigna el mes anterior en cada iteración
                        mesAnterior = m;
                    }

                    if (mesAnterior != null) {
                        // Realiza el cálculo de los casos predecibles
                        double alpha = 0.2;
                        int casosPredecibles = (int) Math.round(alpha * mesAnterior.getCasosHistoricos()
                                + (1 - alpha) * mesAnterior.getCasosPredictibles());

                        // Busca el mes actual
                        MesCasos mesActual = null;
                        for (MesCasos m : casos) {
                            if (m.getMes().equals(mes)) {
                                mesActual = m;
                                break;
                            }
                        }

                        if (mesActual != null) {
                            // Establece los casos predecibles en el mes actual
                            mesActual.setCasosPredictibles(casosPredecibles);
                            vigilanciaRepository.save(vigilancia1);
                            return;
                        } else {
                            logger.severe("Error al predecir: mes actual no encontrado");
                        }
                    } else {
                        logger.severe("Error al hacer la predicción: mes anterior no encontrado");
                    }
                }

            }
        }

    }

    /*para iztapalapa agregar mas virus*/
    public void agregarVirusIztapalapa(VirusCasos virusCasos) {
        List<Vigilancia> optionalVigilancia = vigilanciaRepository.findByAlcaldia("Iztapalapa");

        if (optionalVigilancia != null) {

            Vigilancia vigilanciaIztapalapa = optionalVigilancia.get(0);
            vigilanciaIztapalapa.getVirusCasos().add(virusCasos);
            vigilanciaRepository.save(vigilanciaIztapalapa);

        } else {
            //si no hay vigilancias para iztapalapa
            logger.severe("no se encuentran vigilancias ");
        }

    }

    /*agregar un nuevo mes a iztapalapa y al virus*/
    public void agregarMes(String alcaldia, String nombreVirus, MesCasos nuevoMes) {
        List<Vigilancia> listVigilancia = vigilanciaRepository.findByAlcaldia(alcaldia);

        for (Vigilancia vigilancia : listVigilancia) {
            //busacmis el virus por su nombre
            List<VirusCasos> virusCasos = vigilancia.getVirusCasos();

            for (VirusCasos virusCasos1 : virusCasos) {
                if (virusCasos1.getNombreVirus().equals(nombreVirus)) {
                    //agregamos el nuevo mes
                    List<MesCasos> casos = virusCasos1.getCasos();
                    casos.add(nuevoMes);

                    //guardamos
                    vigilanciaRepository.save(vigilancia);
                    return;
                }
            }

        }

    }

    /*para iztapalapa*/
    public void deletePorNombreVirus(String nombreVirus) {
        // Buscar la vigilancia por alcaldía (supongamos que se llama "Iztapalapa")
        List<Vigilancia> vigilancias = vigilanciaRepository.findByAlcaldia("Iztapalapa");

        for (Vigilancia vigilancia : vigilancias) {
            List<VirusCasos> virusCasos = vigilancia.getVirusCasos();

            // Filtrar los virus y mantener solo aquellos cuyo nombre no coincida con el que se va a eliminar
            List<VirusCasos> virusFiltrados = virusCasos.stream()
                    .filter(virus -> !virus.getNombreVirus().equals(nombreVirus))
                    .collect(Collectors.toList());

            // Actualizar la lista de virus en la vigilancia
            vigilancia.setVirusCasos(virusFiltrados);

            // Guardar los cambios en la base de datos
            vigilanciaRepository.save(vigilancia);
        }
    }

    /*actualizar casos historicos*/
    public void actualizarCasosHistoricos(String alcaldia, String nombreVirus, String mes, int nuevosCasosHistoricos) {
        List<Vigilancia> vigilancia = vigilanciaRepository.findByAlcaldia(alcaldia);


        for (Vigilancia vigilancia1 : vigilancia) {
            for (VirusCasos virus : vigilancia1.getVirusCasos()) {
                if (virus.getNombreVirus().equals(nombreVirus)) {
                    List<MesCasos> casos = virus.getCasos();

                    // Buscamos el mes específico
                    for (MesCasos m : casos) {
                        if (m.getMes().equals(mes)) {
                            // Actualizar casosHistoricos para el mes específico
                            m.setCasosHistoricos(nuevosCasosHistoricos);
                            // Guardar los cambios en el mongo
                            vigilanciaRepository.save(vigilancia1);
                            return;
                        }
                    }
                }
            }
        }
    }
}
