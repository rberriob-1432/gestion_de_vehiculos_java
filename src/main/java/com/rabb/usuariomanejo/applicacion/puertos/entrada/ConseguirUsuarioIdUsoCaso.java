package com.rabb.usuariomanejo.applicacion.puertos.entrada;
import com.rabb.usuariomanejo.applicacion.servicios.dto.query.ConseguirUsuarioPorIdQuery;
import com.rabb.usuariomanejo.dominio.modelo.UsuarioModelo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface ConseguirUsuarioIdUsoCaso {
    UsuarioModelo execute(@NotNull @Valid ConseguirUsuarioPorIdQuery query);
}