package com.rabb.usuariomanejo.dominio.ov;
import com.rabb.usuariomanejo.dominio.excepciones.InvalidoUsuarioCorreoExcepcion;
import java.util.Objects;
import java.util.regex.Pattern;

public record UsuarioCorreo(String value) {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$");

    public UsuarioCorreo {
        final String normalizedValue =
                Objects.requireNonNull(value, "UsuarioCorreo no puede ser nulo").trim().toLowerCase();
        validateNotEmpty(normalizedValue);
        validateFormat(normalizedValue);
        value = normalizedValue;
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidoUsuarioCorreoExcepcion.becauseValueIsEmpty();
        }
    }

    private static void validateFormat(final String normalizedValue) {
        if (!EMAIL_PATTERN.matcher(normalizedValue).matches()) {
            throw InvalidoUsuarioCorreoExcepcion.becauseFormatIsInvalid(normalizedValue);
        }
    }

    @Override
    public String toString() {
        return value;
    }
}