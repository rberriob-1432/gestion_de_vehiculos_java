package com.rabb.usuariomanejo.dominio.excepciones;
public final class InvalidoUsuarioContraseñaExcepcion extends DominioExcepcion {

    private static final String MENSAJE_VACIO = "La contraseña no puede estar vacía.";
    private static final String MENSAJE_MUY_CORTO =
            "La contraseña debe tener al menos %d caracteres.";

    private InvalidoUsuarioContraseñaExcepcion(final String message) {
        super(message);
    }

    public static InvalidoUsuarioContraseñaExcepcion becauseValueIsEmpty() {
        return new InvalidoUsuarioContraseñaExcepcion(MENSAJE_VACIO);
    }

    public static InvalidoUsuarioContraseñaExcepcion becauseLengthIsTooShort(final int minimumLength) {
        return new InvalidoUsuarioContraseñaExcepcion(String.format(MENSAJE_MUY_CORTO, minimumLength));
    }
}