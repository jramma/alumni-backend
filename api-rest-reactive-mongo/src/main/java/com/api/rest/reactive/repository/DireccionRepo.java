package com.api.rest.reactive.repository;

import com.api.rest.reactive.domain.entity.Direccion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DireccionRepo extends MongoRepository<Direccion,String> {
}
