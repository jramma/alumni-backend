package com.api.rest.reactive.controller;

import com.api.rest.reactive.domain.entity.Alumno;
import com.api.rest.reactive.domain.entity.Direccion;
import com.api.rest.reactive.service.AlumniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/alumni/v1")
public class AlumniController {

    @Autowired
    private AlumniService alumniService;


    // GESTION DE ALUMNOS -- CRUD BASIC ----------------------------------------------------------------
    @GetMapping("/alumni")
    public Flux<Alumno> listarAlumnos(){
        return alumniService.findAllAlumnos();
    }
    @GetMapping("/alumno")
    public Mono<ResponseEntity<Alumno>> obtenerAlumno(@RequestParam String nombreOApellido) {
        return alumniService.findAlumno(nombreOApellido);
    }

    @PostMapping("/alumni")
    public Mono<ResponseEntity<Alumno>> guardarAlumno(@RequestBody Alumno alumno){

    return alumniService.guardarAlumno(alumno);
    }
    @PutMapping("/alumni")
    public Mono<ResponseEntity<Alumno>> editarAlumno(@RequestBody Alumno alumno){

        return alumniService.editarAlumno(alumno);
    }

    @DeleteMapping("/alumni")
    public Mono<Void> eliminarAlumno(@RequestBody String mailAlumno){
    return alumniService.eliminarAlumno(mailAlumno);
    }


    // MANEJO DE DIRECCIONES ----------------------------------------------------------------
    @PostMapping("/postDireccion")
    public Mono<ResponseEntity<Direccion>> postDireccion(@RequestBody Direccion direccion){
        return alumniService.postDireccion(direccion);
    }

    // MANEJO DE PROFESORES ----------------------------------------------------------------
    // CRUD ESTUDIOS ----------------------------------------------------------------
    // CRUD CUESTIONARIO ----------------------------------------------------------------

}
