package com.msvc.vigilanciaEp.repository;


import com.msvc.vigilanciaEp.model.Vigilancia;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface VigilanciaRepository extends MongoRepository<Vigilancia,String > {

    List<Vigilancia> findByAlcaldia(String alcaldia);


}
