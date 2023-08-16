package com.api.rest.reactive.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@Document("cuestionario")
public class Cuestionario {
    @Id
    private String id;
    private String titulo;

    private List<String> preguntas;

    private Docente autor;

    private Boolean esBorrador;






}
