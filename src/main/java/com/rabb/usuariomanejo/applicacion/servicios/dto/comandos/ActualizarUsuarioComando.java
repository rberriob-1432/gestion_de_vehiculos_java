package com.rabb.usuariomanejo.applicacion.servicios.dto.comandos;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ActualizarUsuarioComando(
        @NotBlank(message = "id no debe estar en blanco") String id,
        @NotBlank(message = "nombre no debe estar en blanco")
        @Size(min = 3, message = "nombre debe tener al menos 3 caracteres")
        String nombre,
        @NotBlank(message = "email no debe estar en blanco")
        @Email(message = "email debe tener un formato válido")
        String email,
        String password,
        @NotBlank(message = "role no debe estar en blanco") String role,
        @NotBlank(message = "status no debe estar en blanco") String status)
{

}