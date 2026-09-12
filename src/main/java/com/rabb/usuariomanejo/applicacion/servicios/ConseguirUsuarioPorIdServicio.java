 package com.rabb.usuariomanejo.applicacion.servicios;
import com.rabb.usuariomanejo.applicacion.puertos.entrada.ConseguirUsuarioPorIdCasoUso;
import com.rabb.usuariomanejo.applicacion.puertos.salida.ConseguirUsuarioPorIdPuerto;
import com.rabb.usuariomanejo.applicacion.servicios.dto.query.ConseguirUsuarioPorIdQuery;
import com.rabb.usuariomanejo.applicacion.servicios.mapeador.UsuarioAplicacionMappeador;
import com.rabb.usuariomanejo.dominio.excepciones.UsuarioNoEncontradoExcepcion;
import com.rabb.usuariomanejo.dominio.modelo.UsuarioModelo;
import com.rabb.usuariomanejo.dominio.ov.UsuarioId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class ConseguirUsuarioPorIdServicio
        implements ConseguirUsuarioPorIdCasoUso {

    private final ConseguirUsuarioPorIdPuerto
            conseguirUsuarioPorIdPuerto;

    private final Validator validator;

    @Override
    public UsuarioModelo execute(
            final ConseguirUsuarioPorIdQuery query) {

        validateQuery(query);

        final UsuarioId usuarioId =
                UsuarioAplicacionMappeador
                        .fromConseguirUsuarioPorIdQueryToUsuarioId(query);

        return conseguirUsuarioPorIdPuerto
                .getById(usuarioId)
                .orElseThrow(
                        () -> UsuarioNoEncontradoExcepcion
                                .becauseIdWasNotFound(
                                        usuarioId.value()
                                )
                );
    }

    private void validateQuery(
            final ConseguirUsuarioPorIdQuery query) {

        final Set<ConstraintViolation<ConseguirUsuarioPorIdQuery>>
                violations = validator.validate(query);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}