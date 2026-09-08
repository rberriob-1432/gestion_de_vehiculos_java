package com.rabb.usuariomanejo.dominio.modelo;
import java.util.Objects;
import lombok.Value;

@Value
public class CorreoModeloEnvio {

    String destinocorreo;
    String nombredestinatario;
    String asunto;
    String cuerpo;

    public CorreoModeloEnvio(
            final String destinocorreo,
            final String nombredestinatario,
            final String asunto,
            final String cuerpo) {
        this.destinocorreo =
                validateNotBlank(destinocorreo, "El email del destinatario es requerido.");
        this.nombredestinatario =
                validateNotBlank(nombredestinatario, "El nombre del destinatario es requerido.");
        this.asunto = validateNotBlank(asunto, "El asunto es requerido.");
        this.cuerpo = validateNotBlank(cuerpo, "El cuerpo del mensaje es requerido.");
    }

    private static String validateNotBlank(final String value, final String errorMessage) {
        Objects.requireNonNull(value, errorMessage);
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
        return value;
    }
}