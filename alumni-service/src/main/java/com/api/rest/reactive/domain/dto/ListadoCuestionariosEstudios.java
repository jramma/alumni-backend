package com.api.rest.reactive.domain.dto;


import com.api.rest.reactive.domain.entity.Cuestionario;
import com.api.rest.reactive.domain.entity.Estudio;
import lombok.Data;

import java.util.ArrayList;
@Data
public class ListadoCuestionariosEstudios {
    private ArrayList<Estudio> estudioArrayList;
    private ArrayList<Cuestionario>cuestionarios;
    private int totalEstudio;
    private int totalCuestionario;




}
