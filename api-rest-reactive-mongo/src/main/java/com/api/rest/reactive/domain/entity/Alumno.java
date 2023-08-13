package com.api.rest.reactive.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Data
@Document("alumno")
public class Alumno {
    @Id
    private String id;
    private String nombre;
    private String apellido;
    private String mail;
    private int edad;
    private Direccion direccion;
    private String padre;
    private String madre;
    private double mediaGlobal;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alumno alumno)) return false;
        return edad == alumno.edad && Double.compare(mediaGlobal, alumno.mediaGlobal) == 0 && Objects.equals(id, alumno.id) && Objects.equals(nombre, alumno.nombre) && Objects.equals(apellido, alumno.apellido) && Objects.equals(mail, alumno.mail) && Objects.equals(direccion, alumno.direccion) && Objects.equals(padre, alumno.padre) && Objects.equals(madre, alumno.madre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, mail, edad, direccion, padre, madre, mediaGlobal);
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", mail='" + mail + '\'' +
                ", edad=" + edad +
                ", direccion=" + direccion +
                ", padre='" + padre + '\'' +
                ", madre='" + madre + '\'' +
                ", mediaGlobal=" + mediaGlobal +
                '}';
    }


}
