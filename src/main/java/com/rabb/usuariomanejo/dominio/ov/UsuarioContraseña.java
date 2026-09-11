package com.rabb.usuariomanejo.dominio.ov;
import at.favre.lib.crypto.bcrypt.BCrypt;
import com.rabb.usuariomanejo.dominio.excepciones.InvalidoUsuarioContraseñaExcepcion;
import java.util.Objects;
public final class UsuarioContraseña {

    private static final int CANTIDAD_MINIMA = 8;
    private static final int BCRYPT_COSTO = 12;

    private final String value;

    private UsuarioContraseña(final String value) {
        this.value = value;
    }

    /**
     * Crea un UsuarioContraseña desde texto plano: valida y aplica hash BCrypt. Usar cuando el usuario
     * crea o cambia su contraseña.
     */
    public static UsuarioContraseña fromPlainText(final String plainText) {
        final String normalizedValue =
                Objects.requireNonNull(plainText, "Password cannot be null").trim();
        validateNotEmpty(normalizedValue);
        validateMinimumLength(normalizedValue);
        final String hash =
                BCrypt.withDefaults().hashToString(BCRYPT_COSTO, normalizedValue.toCharArray());
        return new UsuarioContraseña(hash);
    }

    /**
     * Crea un UsuarioContraseña desde un hash ya almacenado en base de datos. No re-valida ni re-hashea.
     */
    public static UsuarioContraseña fromHash(final String hash) {
        Objects.requireNonNull(hash, "Password hash cannot be null");
        return new UsuarioContraseña(hash);
    }


    /** Verifica un texto plano contra el hash BCrypt almacenado. */
    public boolean verifyPlain(final String plainText) {
        final String normalizedPlain =
                Objects.requireNonNull(plainText, "Plain password cannot be null").trim();
        final BCrypt.Result result = BCrypt.verifyer().verify(normalizedPlain.toCharArray(), value);
        return result.verified;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) return true;
        if (!(other instanceof UsuarioContraseña userPassword)) return false; // NOSONAR: rama instanceof no testeable sin warnings
        return Objects.equals(value, userPassword.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidoUsuarioContraseñaExcepcion.becauseValueIsEmpty();
        }
    }

    private static void validateMinimumLength(final String normalizedValue) {
        if (normalizedValue.length() < CANTIDAD_MINIMA) {
            throw InvalidoUsuarioContraseñaExcepcion.becauseLengthIsTooShort(CANTIDAD_MINIMA);
        }
    }

}