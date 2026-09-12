package com.rabb.usuariomanejo.applicacion.servicios;
import com.rabb.usuariomanejo.applicacion.puertos.salida.EnviadorCorreoPuerto;
import com.rabb.usuariomanejo.dominio.excepciones.CorreoEnviadorExcepcion;
import com.rabb.usuariomanejo.dominio.modelo.CorreoModeloEnvio;
import com.rabb.usuariomanejo.dominio.modelo.UsuarioModelo;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;

@Log
@RequiredArgsConstructor
public final class EnviadorCorreoServicio {

    private static final String ASUNTO_USUARIO_CREADO =
            "Tu cuenta ha sido creada — Gestión de Usuarios";

    private static final String ASUNTO_USUARIO_ACTUALIZADO =
            "Tu cuenta ha sido actualizada — Gestión de Usuarios";

    private static final String TOKEN_NOMBRE = "name";
    private static final String TOKEN_CORREO = "email";
    private static final String TOKEN_CONTRASENA = "password";
    private static final String TOKEN_ROL = "role";
    private static final String TOKEN_ESTATUS = "status";

    private final EnviadorCorreoPuerto enviadorCorreoPuerto;

    public void notificarUsuarioCreado(
            final UsuarioModelo usuario,
            final String contraseñaPlana) {

        final String plantilla =
                cargarPlantilla("usuario-creado.html");

        final String cuerpo =
                renderizarPlantilla(
                        plantilla,
                        Map.of(
                                TOKEN_NOMBRE,
                                usuario.getNombre().value(),

                                TOKEN_CORREO,
                                usuario.getCorreo().value(),

                                TOKEN_CONTRASENA,
                                contraseñaPlana,

                                TOKEN_ROL,
                                usuario.getRol().name()
                        )
                );

        final CorreoModeloEnvio destino =
                construirDestino(
                        usuario,
                        ASUNTO_USUARIO_CREADO,
                        cuerpo
                );

        enviarORegistrar(destino);
    }

    public void notificarUsuarioActualizado(
            final UsuarioModelo usuario) {

        final String plantilla =
                cargarPlantilla("usuario-actualizado.html");

        final String cuerpo =
                renderizarPlantilla(
                        plantilla,
                        Map.of(
                                TOKEN_NOMBRE,
                                usuario.getNombre().value(),

                                TOKEN_CORREO,
                                usuario.getCorreo().value(),

                                TOKEN_ROL,
                                usuario.getRol().name(),

                                TOKEN_ESTATUS,
                                usuario.getEstatus().name()
                        )
                );

        final CorreoModeloEnvio destino =
                construirDestino(
                        usuario,
                        ASUNTO_USUARIO_ACTUALIZADO,
                        cuerpo
                );

        enviarORegistrar(destino);
    }

    private static CorreoModeloEnvio construirDestino(
            final UsuarioModelo usuario,
            final String asunto,
            final String cuerpo) {

        return new CorreoModeloEnvio(
                usuario.getCorreo().value(),
                usuario.getNombre().value(),
                asunto,
                cuerpo
        );
    }

    private String cargarPlantilla(
            final String nombrePlantilla) {

        final String ruta =
                "/templates/" + nombrePlantilla;

        try (InputStream inputStream =
                     abrirRecurso(ruta)) {

            if (Objects.isNull(inputStream)) {
                throw CorreoEnviadorExcepcion
                        .becauseSendFailed(
                                new IllegalStateException(
                                        "Plantilla no encontrada: "
                                                + ruta
                                )
                        );
            }

            return new String(
                    inputStream.readAllBytes(),
                    StandardCharsets.UTF_8
            );

        } catch (final IOException excepcion) {

            throw CorreoEnviadorExcepcion
                    .becauseSendFailed(excepcion);
        }
    }

    InputStream abrirRecurso(final String ruta) {
        return getClass()
                .getResourceAsStream(ruta);
    }

    private String renderizarPlantilla(
            final String plantilla,
            final Map<String, String> valores) {

        String resultado = plantilla;

        for (final Map.Entry<String, String> entrada
                : valores.entrySet()) {

            final String token =
                    "{{" + entrada.getKey() + "}}";

            resultado = resultado.replace(
                    token,
                    entrada.getValue()
            );
        }

        return resultado;
    }

    private void enviarORegistrar(
            final CorreoModeloEnvio destino) {

        try {

            enviadorCorreoPuerto.enviar(destino);

        } catch (final CorreoEnviadorExcepcion excepcion) {

            log.log(
                    Level.WARNING,
                    "[EnviadorCorreoServicio] "
                            + "No se pudo enviar correo a: {0}. "
                            + "Causa: {1}",
                    new Object[]{
                            destino.getDestinocorreo(),
                            excepcion.getMessage()
                    }
            );

            throw excepcion;
        }
    }
}