package com.api.rest.reactive.functional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class Router {

    @Bean
    public RouterFunction<ServerResponse> route(Handler handler) {

        return RouterFunctions
                .route(GET("/functional/AllAlumni"), handler::findAllAlumnos)
                .andRoute(GET("/functional/GetAlumni"), handler::findAlumno)
                .andRoute(POST("/functional/PostAlumni"), handler::postAlumno)
                .andRoute(PUT("/functional/PutAlumni"), handler::editarAlumno)
                .andRoute(DELETE("/functional/DelAlumni"), handler::deleteAlumno)
                .andRoute(POST("/functional/postDirect"), handler::postDireccion);
    }


}
