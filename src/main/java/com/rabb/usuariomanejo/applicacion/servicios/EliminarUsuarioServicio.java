package com.rabb.usuariomanejo.applicacion.servicios;
import com.rabb.usuariomanejo.applicacion.puertos.entrada.EliminarUsuarioUsoCaso;
import com.rabb.usuariomanejo.applicacion.puertos.salida.EliminarUsuarioPuerto;
import com.rabb.usuariomanejo.applicacion.puertos.salida.ConseguirUsuarioPorIdPuerto;
import com.rabb.usuariomanejo.applicacion.servicios.dto.comandos.EliminarUsuarioComando;
import com.rabb.usuariomanejo.applicacion.servicios.mapeador.UsuarioAplicacionMappeador;
import com.rabb.usuariomanejo.dominio.excepciones.UsuarioNoEncontradoExcepcion;
import com.rabb.usuariomanejo.dominio.ov.UsuarioId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class EliminarUsuarioServicio
        implements EliminarUsuarioUsoCaso {

    private final EliminarUsuarioPuerto eliminarUsuarioPuerto;
    private final ConseguirUsuarioPorIdPuerto conseguirUsuarioPorIdPuerto;
    private final Validator validator;

    @Override
    public void execute(final EliminarUsuarioComando command) {

        validateCommand(command);

        final UsuarioId usuarioId =
                UsuarioAplicacionMappeador
                        .fromDeleteCommandToUsuarioId(command);

        ensureUsuarioExists(usuarioId);

        eliminarUsuarioPuerto.delete(usuarioId);
    }

    private void validateCommand(
            final EliminarUsuarioComando command) {

        final Set<ConstraintViolation<EliminarUsuarioComando>>
                violations = validator.validate(command);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private void ensureUsuarioExists(
            final UsuarioId usuarioId) {

        conseguirUsuarioPorIdPuerto
                .getById(usuarioId)
                .orElseThrow(
                        () -> UsuarioNoEncontradoExcepcion
                                .becauseIdWasNotFound(
                                        usuarioId.value()
                                )
                );
    }
}

