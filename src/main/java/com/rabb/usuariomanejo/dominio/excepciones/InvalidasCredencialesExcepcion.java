package com.rabb.usuariomanejo.dominio.excepciones;
public final class InvalidasCredencialesExcepcion extends DominioExcepcion {

    private static final String MENSAJE_INVALIDA_CREDENCIALES = "Correo o contraseña incorrectos.";
    private static final String MENSAJE_USUARIO_DESACTIVADO =
            "Tu cuenta no está activa. Contacta al administrador.";

    private InvalidasCredencialesExcepcion(final String message) {
        super(message);
    }

    public static InvalidasCredencialesExcepcion becauseCredentialsAreInvalid() {
        return new InvalidasCredencialesExcepcion(MENSAJE_INVALIDA_CREDENCIALES);
    }

    public static InvalidasCredencialesExcepcion becauseUserIsNotActive() {
        return new InvalidasCredencialesExcepcion(MENSAJE_USUARIO_DESACTIVADO);
    }
}