package com.rabb.usuariomanejo.applicacion.puertos.entrada;
import com.rabb.usuariomanejo.applicacion.servicios.dto.comandos.InciarSesionComando;
import com.rabb.usuariomanejo.dominio.modelo.UsuarioModelo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface InciarSesionUsoCaso {
    UsuarioModelo execute(@NotNull @Valid InciarSesionComando commando);
}