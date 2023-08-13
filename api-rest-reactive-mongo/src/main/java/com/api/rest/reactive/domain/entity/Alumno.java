package com.api.rest.reactive.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Objects;

@Data
@Document("alumno")
public class Alumno {
    @Id
    private String id;
    private String nombre;
    private String apellido;
    private String mail;
    private int edad;
    private Direccion direccion;
    private String padre;
    private String madre;
    private double mediaGlobal;
    // para la IA
    private String observaciones;
    private int posibilidadDeBulling;
    private int victimaBulling;
    private int victimaSuicidio;
    private int trastornoAliment;
    private int victimaHomofobia;
    private int marginacion;
    private int narcicismo;
    private int ego;
    private int introvertido;
    private int tda;
    private int pas;
    private int ansiedad;
    private int autista;
    private int estres;
    private int esquizofrenia;

}
