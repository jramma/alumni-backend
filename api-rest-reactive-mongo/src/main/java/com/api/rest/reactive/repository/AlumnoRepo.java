package com.api.rest.reactive.repository;

import com.api.rest.reactive.domain.entity.Alumno;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface AlumnoRepo extends ReactiveMongoRepository<Alumno,String> {
    Mono<Alumno> findFirstByMail(String mail);

    Mono<Alumno> findAllByNombreOrApellido(String nombreOrApellido);
    Alumno findByMail(String mail);

}
