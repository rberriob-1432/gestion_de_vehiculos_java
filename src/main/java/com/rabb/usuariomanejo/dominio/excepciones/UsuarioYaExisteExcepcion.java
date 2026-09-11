package com.rabb.usuariomanejo.dominio.excepciones;

public final class UsuarioYaExisteExcepcion extends DominioExcepcion {

    private static final String MENSAJE_CORREO_EXISTE = "Ya existe un usuario con el correo: %s";

    private UsuarioYaExisteExcepcion(final String message) {
        super(message);
    }

    public static UsuarioYaExisteExcepcion becauseEmailAlreadyExists(final String email) {
        return new UsuarioYaExisteExcepcion(String.format(MENSAJE_CORREO_EXISTE, email));
    }
}