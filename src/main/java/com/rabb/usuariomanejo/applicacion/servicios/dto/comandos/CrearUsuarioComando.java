package com.rabb.usuariomanejo.applicacion.servicios.dto.comandos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CrearUsuarioComando(
        @NotBlank(message = "id no puede estar en blanco") String id,
        @NotBlank(message = "nombre no puede estar en blanco")
        @Size(min = 3, message = "nombre debe tener al menos 3 caracteres")
        String nombre,
        @NotBlank(message = "correo no puede estar en blanco")
        @Email(message = "correo debe tener un formato válido")
        String correo,
        @NotBlank(message = "password no puede estar en blanco")
        @Size(min = 8, message = "password debe tener al menos 8 caracteres")
        String password,
        @NotBlank(message = "role no puede estar en blanco") String role)
{

}