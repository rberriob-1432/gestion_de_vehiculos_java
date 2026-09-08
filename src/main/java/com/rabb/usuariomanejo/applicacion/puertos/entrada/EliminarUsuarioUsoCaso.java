package com.rabb.usuariomanejo.applicacion.puertos.entrada;
import com.rabb.usuariomanejo.applicacion.servicios.dto.comandos.EliminarUsuarioComando;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface EliminarUsuarioUsoCaso {
    void execute(@NotNull @Valid EliminarUsuarioComando command);
}