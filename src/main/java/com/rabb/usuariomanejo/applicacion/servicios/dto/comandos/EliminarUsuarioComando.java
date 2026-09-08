package com.rabb.usuariomanejo.applicacion.servicios.dto.comandos;
import jakarta.validation.constraints.NotBlank;

public record EliminarUsuarioComando(
        @NotBlank(message = "id no puede estar en blanco") String id
) {

}