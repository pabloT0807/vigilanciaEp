package com.msvc.vigilanciaEp.controller;


import com.msvc.vigilanciaEp.model.MesCasos;
import com.msvc.vigilanciaEp.model.Vigilancia;
import com.msvc.vigilanciaEp.model.VirusCasos;
import com.msvc.vigilanciaEp.service.VigilanciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/v1/vigilancia")
public class VigilanciaController {


    /*importante
    *
    * @RequestParam se usa para extraer los parametros de la URL o hacer consultas GET
    *@PathVariable se usa para extraer las variables de la URL  inscrustradas en la URL /usuarios/{id}
    * */
    @Autowired
    private VigilanciaService vigilanciaService;

    @PostMapping("/createMes")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMes(@RequestBody Vigilancia vigilancia  ){

        vigilanciaService.guardarMes(vigilancia);
    }

    @PostMapping("/calcular-prediccion/{nombreVirus}/{mes}")
    public ResponseEntity<String> calcularPrediccion(@PathVariable String nombreVirus,@PathVariable String mes){

        try{
            vigilanciaService.calcularCasosPredectibles(nombreVirus,mes);
            return  ResponseEntity.ok("prediccion calculada");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
        }
    }

    /* agregar virus a iztapalapa*/
    @PostMapping("/iztapalapa/virus")
    public ResponseEntity<String> agregarVirusIztapalapa(@RequestBody VirusCasos virusCasos){
        vigilanciaService.agregarVirusIztapalapa(virusCasos);
        return ResponseEntity.ok("virus agreado exitosamente");
    }

    /*metodo GET por alcaldia*/

    @GetMapping("/buscar/{alcaldia}")
    public ResponseEntity<List<Vigilancia>> obtenerVigilanciaPorAlcaldia(@PathVariable String alcaldia){
        List<Vigilancia> vigilancia = vigilanciaService.buscarPorNombreAlcaldia(alcaldia);

        return ResponseEntity.ok().body(vigilancia);
    }
    /*insertar mes */
    @PostMapping("/alcaldia/{alcaldia}/virus/{nombreVirus}/agregar-mes")
    public ResponseEntity<String> agregarMes(@PathVariable String alcaldia, @PathVariable String nombreVirus, @RequestBody MesCasos nuevoMes){
        try{
            vigilanciaService.agregarMes(alcaldia,nombreVirus,nuevoMes);
            return ResponseEntity.ok("mes agregado");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR AL INSERTAR MES");
        }

    }

    /*elimninar para iztapalapa*/
    @DeleteMapping("/virus/{nombreVirus}")
    public ResponseEntity<String> eliminarVirusPorNombre(@PathVariable String nombreVirus) {
        try {
            vigilanciaService.deletePorNombreVirus(nombreVirus);
            return ResponseEntity.ok("Virus eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al intentar eliminar el virus: " + e.getMessage());
        }
    }

    /*update casos*/
    @PutMapping("/actualizar-casos-historicos/{alcaldia}/{nombreVirus}/{mes}")
    public ResponseEntity<String> actualizarCasosHistoricos(
            @PathVariable String alcaldia,
            @PathVariable String nombreVirus,
            @PathVariable String mes,
            @RequestParam int nuevosCasosHistoricos) {
        try {
            vigilanciaService.actualizarCasosHistoricos(alcaldia, nombreVirus, mes, nuevosCasosHistoricos);
            return ResponseEntity.ok("Casos históricos actualizados correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar casos históricos.");
        }
    }

    /*Parte bacterias*/
    @PostMapping("/calcular-prediccion/{alcaldia}/{nombreBacterias}/{mes}")
    public ResponseEntity<String> calcularPrediccionBacterias(@PathVariable String alcaldia ,@PathVariable String nombreBacterias,@PathVariable String mes){

        try{
            vigilanciaService.calcularCasosPredectiblesBacterias(alcaldia,nombreBacterias,mes);
            return  ResponseEntity.ok("prediccion calculada");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
        }
    }
/*Insertar mes Bacterias*/
    @PostMapping("/alcaldia/{alcaldia}/bacterias/{nombreBacterias}/agregar-mes")
    public ResponseEntity<String> agregarMesBacterias(@PathVariable String alcaldia, @PathVariable String nombreBacterias, @RequestBody MesCasos nuevoMes){
        try{
            vigilanciaService.agregarMesBacterias(alcaldia,nombreBacterias,nuevoMes);
            return ResponseEntity.ok("mes agregado");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR AL INSERTAR MES");
        }

    }
/*Eliminar bacterias*/
    @DeleteMapping("/virus/{nombreBacterias}")
    public ResponseEntity<String> eliminarBacteriasPorNombre(@PathVariable String nombreBacterias) {
        try {
            vigilanciaService.deletePorNombreVirus(nombreBacterias);
            return ResponseEntity.ok("Bacteria eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al intentar eliminar el virus: " + e.getMessage());
        }
    }

/*Actualizar casos bacterias*/
    @PutMapping("/actualizar-casos-historicos/{alcaldia}/{nombreBacterias}/{mes}")
    public ResponseEntity<String> actualizarCasosHistoricosBacterias(
            @PathVariable String alcaldia,
            @PathVariable String nombreBacterias,
            @PathVariable String mes,
            @RequestParam int nuevosCasosHistoricos) {
        try {
            vigilanciaService.actualizarCasosHistoricosBacterias(alcaldia, nombreBacterias, mes, nuevosCasosHistoricos);
            return ResponseEntity.ok("Casos históricos actualizados correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar casos históricos.");
        }
    }

}
