package com.api.rest.reactive.repository;

import com.api.rest.reactive.domain.entity.Estudio;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EstudioRepo extends MongoRepository<Estudio, String> {
}
