package com.api.rest.reactive.functional;


import com.api.rest.reactive.domain.entity.Alumno;
import com.api.rest.reactive.domain.entity.Direccion;
import com.api.rest.reactive.service.AlumniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.*;

@Component
public class Handler {
    @Autowired
    private AlumniService service;

    private Mono<ServerResponse> response404 = ServerResponse.notFound().build();
    private Mono<ServerResponse> response406 = ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).build();

    //listar
    public Mono<ServerResponse> findAllAlumnos(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAllAlumnos(), Alumno.class);
    }

    public Mono<ServerResponse> findAlumno(ServerRequest request) {
        Mono<String> requestBodyMono = request.bodyToMono(String.class);
        return requestBodyMono.flatMap(body -> service.findAlumno(body)
                .flatMap(alumno -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(alumno)))
                .switchIfEmpty(response404));
    }

    public Mono<ServerResponse> postAlumno(ServerRequest request) {
        Mono<Alumno> alumnoMono = request.bodyToMono(Alumno.class);

        return alumnoMono.flatMap(alumno -> service.guardarAlumno(alumno)
                        .flatMap(alumnoSaved -> ServerResponse.accepted()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(fromValue(alumnoSaved))))
                .switchIfEmpty(response406);
    }

    public Mono<ServerResponse> editarAlumno(ServerRequest request) {
        Mono<Alumno> alumnoMono = request.bodyToMono(Alumno.class);
        return alumnoMono.flatMap(alumno -> {
            service.editarAlumno(alumno);
            return ServerResponse.accepted()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(fromValue(alumno))
                    .switchIfEmpty(response406);
        });

    }

    public Mono<ServerResponse> deleteAlumno(ServerRequest request) {
        Mono<String> requestBodyMono = request.bodyToMono(String.class);
        return requestBodyMono.flatMap(body -> service.eliminarAlumno(body)
                .flatMap(alumno -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(alumno)))
                .switchIfEmpty(response404));

    }
    public Mono<ServerResponse> postDireccion(ServerRequest request) {
        Mono<Direccion> alumnoMono = request.bodyToMono(Direccion.class);
        return alumnoMono.flatMap(direccion -> service.postDireccion(direccion)
                        .flatMap(direccionS -> ServerResponse.accepted()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(fromValue(direccionS))))
                .switchIfEmpty(response406);
    }

}
