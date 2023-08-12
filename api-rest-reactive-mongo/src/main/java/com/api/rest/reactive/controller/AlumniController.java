package com.api.rest.reactive.controller;

import com.api.rest.reactive.domain.entity.Alumno;
import com.api.rest.reactive.service.AlumniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/alumni/v1")
public class AlumniController {

    @Autowired
    private AlumniService alumniService;
    @GetMapping("/alumnos")
    public Flux<Alumno> listarAlumnos(){
        return alumniService.findAllAlumnos();
    }
    @GetMapping("/alumno")
    public Mono<ResponseEntity<Alumno>> obtenerAlumno(@RequestParam String nombreOApellido) {
        return alumniService.findAlumno(nombreOApellido);
    }

}
