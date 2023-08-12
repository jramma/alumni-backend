package com.api.rest.reactive.service;

import com.api.rest.reactive.domain.entity.Alumno;
import com.api.rest.reactive.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AlumniService {
    @Autowired
    private AlumnoRepo alumnoRepo;
    @Autowired
    private CuestionarioRepo cuestionarioRepo;
    @Autowired
    private DireccionRepo direccionRepo;
    @Autowired
    private DocenteRepo docenteRepo;
    @Autowired
    private EstudioRepo estudioRepo;


    public Flux<Alumno> findAllAlumnos() {
        // return (Flux<Alumno>) alumnoRepo.findAll();
        return Flux.just((Alumno) alumnoRepo.findAll());
    }

    public Mono<ResponseEntity<Alumno>> findAlumno(String nombreOApellido) {
        return alumnoRepo.findAllByNombreOrApellido(nombreOApellido)
                .map(alumno -> ResponseEntity.ok(alumno))
                .defaultIfEmpty(ResponseEntity.notFound().build());


    }
}
