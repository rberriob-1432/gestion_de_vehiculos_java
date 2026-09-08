package com.rabb.usuariomanejo.applicacion.servicios.dto.comandos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record InciarSesionComando(
        @NotBlank(message = "correo no puede estar en blanco")
        @Email(message = "correo debe tener un formato válido")
        String correo,
        @NotBlank(message = "contraseña no puede estar en blanco")
        @Size(min = 8, message = "contraseña debe tener al menos 8 caracteres")
        String contraseña)
{

}