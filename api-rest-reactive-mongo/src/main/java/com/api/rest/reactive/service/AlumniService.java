package com.api.rest.reactive.service;

import com.api.rest.reactive.domain.entity.Alumno;
import com.api.rest.reactive.domain.entity.Direccion;
import com.api.rest.reactive.repository.*;
import org.springframework.beans.BeanUtils;
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

    public Mono<ResponseEntity<Alumno>> guardarAlumno(Alumno alumno) {
        Direccion direccion1 = alumno.getDireccion();
        return direccionRepo.findByDireccionAndPostalCodeAndCiudadAndPisoAndPuerta(
                        direccion1.getDireccion(), direccion1.getPostalCode(), direccion1.getCiudad(),
                        direccion1.getPiso(), direccion1.getPuerta())
                .flatMap(direccionEncontrada -> {
                    alumno.setDireccion(direccionEncontrada);
                    return alumnoRepo.insert(alumno);
                })
                .map(alumnoG -> new ResponseEntity<>(alumnoG, HttpStatus.ACCEPTED))
                .defaultIfEmpty(new ResponseEntity<>(alumno, HttpStatus.NOT_ACCEPTABLE));
    }


    public Mono<ResponseEntity<Alumno>> editarAlumno(Alumno alumno) {

        // OJO SI ALUMNO TIENE PROPIEDADES VACIAS LAS COPIARA, HACER EL FORMULARIO OBLIGATORIO
        return alumnoRepo.findFirstByMail(alumno.getMail())
                .flatMap(alumnoActualizado -> {
                    BeanUtils.copyProperties(alumno, alumnoActualizado, "id", "mail");
                    return alumnoRepo.save(alumnoActualizado)
                            .map(alumno1 -> new ResponseEntity<>(alumno1, HttpStatus.ACCEPTED));
                })
                .defaultIfEmpty(new ResponseEntity<>(alumno, HttpStatus.NOT_FOUND));
    }

    public Mono<Void> eliminarAlumno(String mailAlumno) {
        Alumno alumno = alumnoRepo.findByMail(mailAlumno);
        return alumnoRepo.deleteById(alumno.getId());
    }

    public Mono<ResponseEntity<Direccion>> postDireccion(Direccion direccion) {
        return direccionRepo.insert(direccion)
                .map(direccionS -> new ResponseEntity<>(direccionS, HttpStatus.ACCEPTED))
                .defaultIfEmpty(new ResponseEntity<>(direccion, HttpStatus.NOT_ACCEPTABLE));
    }
}
