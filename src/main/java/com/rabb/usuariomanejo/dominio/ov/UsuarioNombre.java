package com.rabb.usuariomanejo.dominio.excepciones;
import main.java.com.rabb.usuariomanejo.dominio.excepciones.InvalidoUsuarioNombreExcepcion;
import java.util.Objects;

public record UsuarioNombre(String value) {

    private static final int LONGITUD_MINIMA = 3;

    public UsuarioNombre {
        final String normalizedValue = Objects.requireNonNull(value, "UsuarioNombre no puede ser nulo").trim();
        validateNotEmpty(normalizedValue);
        validateMinimumLength(normalizedValue);
        value = normalizedValue;
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidoUsuarioNombreExcepcion.becauseValueIsEmpty();
        }
    }

    private static void validateMinimumLength(final String normalizedValue) {
        if (normalizedValue.length() < LONGITUD_MINIMA) {
            throw InvalidoUsuarioNombreExcepcion.becauseLengthIsTooShort(LONGITUD_MINIMA);
        }
    }

    @Override
    public String toString() {
        return value;
    }
}