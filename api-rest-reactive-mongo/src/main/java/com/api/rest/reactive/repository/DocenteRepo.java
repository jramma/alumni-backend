package com.api.rest.reactive.repository;

import com.api.rest.reactive.domain.entity.Docente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DocenteRepo extends ReactiveMongoRepository<Docente,String> {
}
