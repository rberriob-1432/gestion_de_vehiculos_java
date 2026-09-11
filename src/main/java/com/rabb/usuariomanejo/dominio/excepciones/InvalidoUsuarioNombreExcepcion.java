package com.rabb.usuariomanejo.dominio.excepciones;

public final class InvalidoUsuarioNombreExcepcion extends DominioExcepcion {

    private static final String MENSAJE_VACIO = "El nombre del usuario no puede estar vacío";
    private static final String MENSAJE_MUY_CORTO = "El nombre del usuario debe tener al menos %d caracteres.";

    private InvalidoUsuarioNombreExcepcion(final String message) {
        super(message);
    }

    public static InvalidoUsuarioNombreExcepcion becauseValueIsEmpty() {
        return new InvalidoUsuarioNombreExcepcion(MENSAJE_VACIO);
    }

    public static InvalidoUsuarioNombreExcepcion becauseLengthIsTooShort(final int minimumLength) {
        return new InvalidoUsuarioNombreExcepcion(String.format(MENSAJE_MUY_CORTO, minimumLength));
    }
}