package com.rabb.usuariomanejo.dominio.excepciones;

public final class UsuarioNoEncontradoExcepcion extends DominioExcepcion {

    private static final String MENSAJE_POR_ID = "El usuario con id %s no fue encontrado.";

    private UsuarioNoEncontradoExcepcion(final String message) {
        super(message);
    }

    public static UsuarioNoEncontradoExcepcion becauseIdWasNotFound(final String userId) {
        return new UsuarioNoEncontradoExcepcion(String.format(MENSAJE_POR_ID, userId));
    }
}