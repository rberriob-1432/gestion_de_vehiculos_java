package com.rabb.usuariomanejo.dominio.eventos;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.Getter;

@Getter
public abstract class EventoDominio {

    private final String eventoNombre;
    private final LocalDateTime ocurrioEn;

    protected EventoDominio(final String eventoNombre) {
        this.eventoNombre = eventoNombre;
        this.ocurrioEn = LocalDateTime.now();
    }

    public abstract Map<String, String> payload();
}