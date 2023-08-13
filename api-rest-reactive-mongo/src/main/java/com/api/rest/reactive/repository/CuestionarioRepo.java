package com.api.rest.reactive.repository;

import com.api.rest.reactive.domain.entity.Cuestionario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CuestionarioRepo extends ReactiveMongoRepository<Cuestionario,String> {
}
