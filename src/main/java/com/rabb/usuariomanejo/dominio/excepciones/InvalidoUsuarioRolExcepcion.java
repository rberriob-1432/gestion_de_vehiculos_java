package com.rabb.usuariomanejo.dominio.excepciones;

public final class InvalidoUsuarioRolExcepcion extends DominioExcepcion {

    private static final String MENSAJE_INVALIDO = "El role del usuario es invalido: %s";

    private InvalidoUsuarioRolExcepcion(final String message) {
        super(message);
    }

    public static InvalidoUsuarioRolExcepcion becauseValueIsInvalid(final String role) {
        return new InvalidoUsuarioRolExcepcion(String.format(MENSAJE_INVALIDO, role));
    }
}
