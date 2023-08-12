package com.api.rest.reactive.repository;

import com.api.rest.reactive.domain.entity.Cuestionario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CuestionarioRepo extends MongoRepository<Cuestionario,String> {
}
