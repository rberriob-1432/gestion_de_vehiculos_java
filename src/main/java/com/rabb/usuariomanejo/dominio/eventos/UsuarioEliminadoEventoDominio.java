package com.rabb.usuariomanejo.dominio.eventos;
import java.util.Map;
import com.rabb.usuariomanejo.dominio.ov.UsuarioId;
import lombok.Getter;

@Getter
public final class UsuarioEliminadoEventoDominio extends EventoDominio {

    private static final String NOMBRE_EVENTO = "usuario.deleted";

    private final UsuarioId usuarioId;

    public UsuarioEliminadoEventoDominio(final UsuarioId usuarioId) {
        super(NOMBRE_EVENTO);
        this.usuarioId = usuarioId;
    }

    @Override
    public Map<String, String> payload() {
        return Map.of("id", usuarioId.value());
    }
}