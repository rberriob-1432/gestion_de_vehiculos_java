package com.rabb.usuariomanejo.applicacion.servicios.dto.query;
import jakarta.validation.constraints.NotBlank;
public record ConseguirUsuarioPorIdQuery(@NotBlank(message = "id no debe estar en blanco") String id)
{

}