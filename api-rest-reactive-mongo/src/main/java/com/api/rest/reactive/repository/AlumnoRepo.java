package com.api.rest.reactive.repository;

import com.api.rest.reactive.domain.entity.Alumno;
import org.springframework.data.mongodb.repository.MongoRepository;
import reactor.core.publisher.Mono;

public interface AlumnoRepo extends MongoRepository<Alumno,String> {
    Mono<Alumno> findFirstByMail(String mail);

    Mono<Alumno> findAllByNombreOrApellido(String nombreOrApellido);


}
