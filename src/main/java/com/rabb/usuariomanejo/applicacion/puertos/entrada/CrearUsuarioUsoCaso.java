package com.rabb.usuariomanejo.applicacion.puertos.entrada;
import com.rabb.usuariomanejo.applicacion.servicios.dto.comandos.CrearUsuarioComando;
import com.rabb.usuariomanejo.dominio.modelo.UsuarioModelo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
public interface CrearUsuarioUsoCaso {
    UsuarioModelo execute(@NotNull @Valid CrearUsuarioComando command);
}