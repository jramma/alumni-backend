package com.api.rest.reactive.service;

import com.api.rest.reactive.domain.entity.Alumno;
import com.api.rest.reactive.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return alumnoRepo.findAll();
    }

    public Mono<ResponseEntity<Alumno>> findAlumno(String nombreOApellido) {
        return alumnoRepo.findAllByNombreOrApellido(nombreOApellido)
                .map(alumno -> ResponseEntity.ok(alumno))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    public Mono<ResponseEntity<Alumno>> guardarContacto(Alumno alumno) {
        return alumnoRepo.insert(alumno)
                .map(alumnoG -> new ResponseEntity<>(alumnoG, HttpStatus.ACCEPTED))
                .defaultIfEmpty(new ResponseEntity<>(alumno, HttpStatus.NOT_ACCEPTABLE));

    }

    public Mono<ResponseEntity<Alumno>> editarContacto(Alumno alumno) {
    return alumnoRepo.findFirstByMail(alumno.getMail())
            .flatMap(alumnoActualizado ->{
                alumnoActualizado.setApellido(alumno.getApellido());
                alumnoActualizado.setEdad(alumno.getEdad());
                alumnoActualizado.setMadre(alumno.getMadre());
                alumnoActualizado.setPadre(alumno.getPadre());
                alumnoActualizado.setApellido(alumno.getApellido());
                alumnoActualizado.setMediaGlobal(alumno.getMediaGlobal());
    // faltan futures **************************                                    ***************************
               return alumnoRepo.save(alumnoActualizado)
                       .map(alumno1-> new ResponseEntity<>(alumno1, HttpStatus.ACCEPTED));
            }).defaultIfEmpty(new ResponseEntity<>(alumno, HttpStatus.NOT_FOUND));
    }
}
