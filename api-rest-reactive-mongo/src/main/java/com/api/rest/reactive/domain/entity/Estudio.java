package com.api.rest.reactive.domain.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

public class Estudio {
    @Id
    private String id;
    private Alumno alumno;
    private Cuestionario cuestionario;
    private List<String> respuestas;
    private Date date;





}
