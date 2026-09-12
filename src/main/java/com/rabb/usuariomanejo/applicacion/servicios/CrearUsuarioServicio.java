 package com.rabb.usuariomanejo.applicacion.servicios;
import com.rabb.usuariomanejo.applicacion.puertos.entrada.CrearUsuarioUsoCaso;
import com.rabb.usuariomanejo.applicacion.puertos.salida.ConseguirUsuarioPorCorreoPuerto;
import com.rabb.usuariomanejo.applicacion.puertos.salida.GuardarUsuarioPuerto;
import com.rabb.usuariomanejo.applicacion.servicios.dto.comandos.CrearUsuarioComando;
import com.rabb.usuariomanejo.applicacion.servicios.mapeador.UsuarioAplicacionMappeador;
import com.rabb.usuariomanejo.dominio.excepciones.UsuarioYaExisteExcepcion;
import com.rabb.usuariomanejo.dominio.modelo.UsuarioModelo;
import com.rabb.usuariomanejo.dominio.ov.UsuarioCorreo;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.util.Set;

@Log
@RequiredArgsConstructor
public final class CrearUsuarioServicio
        implements CrearUsuarioUsoCaso {

    private final GuardarUsuarioPuerto guardarUsuarioPuerto;
    private final ConseguirUsuarioPorCorreoPuerto conseguirUsuarioPorCorreoPuerto;
    private final EnviadorCorreoServicio enviadorCorreoServicio;
    private final Validator validator;

    @Override
    public UsuarioModelo execute(
            final CrearUsuarioComando command) {

        validateCommand(command);

        final UsuarioCorreo correo =
                new UsuarioCorreo(command.correo());

        ensureEmailIsNotTaken(correo);

        final UsuarioModelo usuarioParaGuardar =
                UsuarioAplicacionMappeador
                        .fromCreateCommandToModel(command);

        final UsuarioModelo usuarioGuardado =
                guardarUsuarioPuerto.save(usuarioParaGuardar);

        enviadorCorreoServicio.notificarUsuarioCreado(
                usuarioGuardado,
                command.password()
        );

        return usuarioGuardado;
    }

    private void validateCommand(
            final CrearUsuarioComando command) {

        final Set<ConstraintViolation<CrearUsuarioComando>>
                violations = validator.validate(command);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(
                    violations
            );
        }
    }

    private void ensureEmailIsNotTaken(
            final UsuarioCorreo correo) {

        conseguirUsuarioPorCorreoPuerto
                .getByEmail(correo)
                .ifPresent(
                        ignored -> {
                            throw UsuarioYaExisteExcepcion
                                    .becauseCorreoYaExiste(
                                            correo.value()
                                    );
                        }
                );
    }
}

