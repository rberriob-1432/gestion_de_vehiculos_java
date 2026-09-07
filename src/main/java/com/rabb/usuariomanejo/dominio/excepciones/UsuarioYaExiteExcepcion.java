package main.java.com.rabb.usuariomanejo.dominio.excepciones;

public final class UsuarioYaExiteExcepcion extends DominioExcepcion {

    private static final String MENSAJE_CORREO_EXISTE = "Ya existe un usuario con el correo: %s";

    private UsuarioYaExiteExcepcion(final String message) {
        super(message);
    }

    public static UsuarioYaExiteExcepcion becauseEmailAlreadyExists(final String email) {
        return new UsuarioYaExiteExcepcion(String.format(MENSAJE_CORREO_EXISTE, email));
    }
}