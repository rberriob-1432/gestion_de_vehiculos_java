package com.rabb.usuariomanejo.dominio.excepciones;
public final class InvalidoUsuarioCorreoExcepcion extends DominioExcepcion {

    private static final String MENSAJE_VACIO = "el correo electrónico no puede estar vacío.";
    private static final String MENSAJE_FORMATO_INVALIDO = "el formato del correo electrónico es inválido: '%s'.";

    private InvalidoUsuarioCorreoExcepcion(final String message) {
        super(message);
    }

    public static InvalidoUsuarioCorreoExcepcion becauseValueIsEmpty() {
        return new InvalidoUsuarioCorreoExcepcion(MENSAJE_VACIO);
    }

    public static InvalidoUsuarioCorreoExcepcion becauseFormatIsInvalid(final String email) {
        return new InvalidoUsuarioCorreoExcepcion(String.format(MENSAJE_FORMATO_INVALIDO, email));
    }
}