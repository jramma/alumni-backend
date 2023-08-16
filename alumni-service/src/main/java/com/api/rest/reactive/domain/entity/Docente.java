package com.api.rest.reactive.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("docente")
public class Docente {
    @Id
    private String id;
    private String nombre;
    private Direccion direccion;
    private String especialidad;

}
