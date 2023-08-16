package com.api.rest.reactive.repository;

import com.api.rest.reactive.domain.entity.Direccion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface DireccionRepo extends ReactiveMongoRepository<Direccion,String> {

    Mono<Direccion> findByDireccionAndPostalCodeAndCiudadAndPisoAndPuerta(String direccion, int postalCode, String ciudad, int piso, String puerta);

}
