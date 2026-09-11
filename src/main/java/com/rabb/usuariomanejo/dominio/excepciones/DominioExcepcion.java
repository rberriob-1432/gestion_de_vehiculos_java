package com.rabb.usuariomanejo.dominio.excepciones;
public abstract class DominioExcepcion extends RuntimeException {

    protected DominioExcepcion(final String message) {
        super(message);
    }

    protected DominioExcepcion(final String message, final Throwable cause) {
        super(message, cause);
    }
}