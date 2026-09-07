package main.java.com.rabb.usuariomanejo.dominio.excepciones;
public final class InvalidoUsuarioEstatusExcepcion extends DominioExcepcion {

    private static final String MENSAJE_INVALIDO = "El estatus del usuario es invalido: %s";

    private InvalidoUsuarioEstatusExcepcion(final String message) {
        super(message);
    }

    public static InvalidoUsuarioEstatusExcepcion becauseValueIsInvalid(final String status) {
        return new InvalidoUsuarioEstatusExcepcion(String.format(MENSAJE_INVALIDO, status));
    }
}
