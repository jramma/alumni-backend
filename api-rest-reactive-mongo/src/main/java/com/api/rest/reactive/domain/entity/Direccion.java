package com.api.rest.reactive.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document("direccion")
public class Direccion {
    @Id
    private String id;
    private String direccion;
    private int postalCode;
    private String ciudad;
    private int piso;
    private String puerta;
}
