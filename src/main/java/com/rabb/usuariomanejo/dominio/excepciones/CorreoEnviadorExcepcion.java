package main.java.com.rabb.usuariomanejo.dominio.excepciones;
public final class CorreoEnviadorExcepcion extends DominioExcepcion {

    private static final String MENSAJE_POR_DEFECTO = "La notificación por correo no pudo ser enviada.";
    private static final String MENSAJE_CON_DETALLE =
            "No se pudo enviar el correo a '%s'. Error SMTP: %s";

    public CorreoEnviadorExcepcion(final String message) {
        super(message);
    }

    public CorreoEnviadorExcepcion(final String message, final Throwable cause) {
        super(message, cause);
    }

    public static CorreoEnviadorExcepcion becauseSmtpFailed(
            final String destinationEmail, final String smtpError) {
        return new CorreoEnviadorExcepcion(
                String.format(MENSAJE_CON_DETALLE, destinationEmail, smtpError));
    }

    public static CorreoEnviadorExcepcion becauseSendFailed(final Throwable cause) {
        return new CorreoEnviadorExcepcion(MENSAJE_POR_DEFECTO, cause);
    }
}