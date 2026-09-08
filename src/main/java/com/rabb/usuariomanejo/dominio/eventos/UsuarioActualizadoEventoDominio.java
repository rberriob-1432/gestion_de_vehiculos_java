package com.rabb.usuariomanejo.dominio.eventos;
import java.util.Map;

import com.rabb.usuariomanejo.dominio.modelo.UsuarioModelo;
import lombok.Getter;

@Getter
public final class UsuarioActualizadoEventoDominio extends EventoDominio {

    private static final String EVENT_NAME = "usuario.updated";

    private final UsuarioModelo usuario;

    public UsuarioActualizadoEventoDominio(final UsuarioModelo usuario) {
        super(EVENT_NAME);
        this.usuario = usuario;
    }

    @Override
    public Map<String, String> payload() {
        return Map.of(
                "id", usuario.getId().value(),
                "name", usuario.getNombre().value(),
                "email", usuario.getCorreo().value(),
                "role", usuario.getRol().name(),
                "status", usuario.getEstatus().name());
    }
}