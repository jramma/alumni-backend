package com.api.rest.reactive.domain.dto;


import com.api.rest.reactive.domain.entity.Alumno;
import lombok.Data;

import java.util.ArrayList;
@Data
public class ListadoAlumnosPorEstudio {
    private String tituloEstudio;
    private ArrayList<Alumno> alumnoList;
    private int totalAlumnos;

}
