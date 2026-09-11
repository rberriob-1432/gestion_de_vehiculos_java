package com.rabb.usuariomanejo.dominio.ov;

import com.rabb.usuariomanejo.dominio.excepciones.InvalidoUsuarioIdExcepcion;
import java.util.Objects;

public record UsuarioId(String value) {

    public UsuarioId {
        final String normalizedValue = Objects.requireNonNull(value, "UsuarioId no puede ser nulo").trim();
        validateNotEmpty(normalizedValue);
        value = normalizedValue;
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidoUsuarioIdExcepcion.becauseValueIsEmpty();
        }
    }

    @Override
    public String toString() {
        return value;
    }
}