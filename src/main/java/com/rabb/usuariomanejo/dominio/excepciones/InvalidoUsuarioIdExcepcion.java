package com.rabb.usuariomanejo.dominio.excepciones;


public final class InvalidoUsuarioIdExcepcion extends DominioExcepcion {

    private static final String MENSAJE_VACIO = "El id del usuario no puede estar vacío";

    private InvalidoUsuarioIdExcepcion(final String message) {
        super(message);
    }

    public static InvalidoUsuarioIdExcepcion becauseValueIsEmpty() {
        return new InvalidoUsuarioIdExcepcion(MENSAJE_VACIO);
    }
}