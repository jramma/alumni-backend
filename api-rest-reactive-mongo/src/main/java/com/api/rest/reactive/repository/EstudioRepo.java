package com.api.rest.reactive.repository;

import com.api.rest.reactive.domain.entity.Estudio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EstudioRepo extends ReactiveMongoRepository<Estudio, String> {
}
