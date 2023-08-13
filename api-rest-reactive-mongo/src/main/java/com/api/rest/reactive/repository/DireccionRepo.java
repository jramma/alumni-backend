package com.api.rest.reactive.repository;

import com.api.rest.reactive.domain.entity.Direccion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DireccionRepo extends ReactiveMongoRepository<Direccion,String> {
}
