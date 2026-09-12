package com.rabb.usuariomanejo.applicacion.servicios;

import com.rabb.usuariomanejo.applicacion.puertos.entrada.InciarSesionUsoCaso;
import com.rabb.usuariomanejo.applicacion.puertos.salida.ConseguirUsuarioPorCorreoPuerto;
import com.rabb.usuariomanejo.applicacion.servicios.dto.comandos.InciarSesionComando;
import com.rabb.usuariomanejo.dominio.enums.UsuarioEstatus;
import com.rabb.usuariomanejo.dominio.excepciones.InvalidasCredencialesExcepcion;
import com.rabb.usuariomanejo.dominio.modelo.UsuarioModelo;
import com.rabb.usuariomanejo.dominio.ov.UsuarioCorreo;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class IniciarSesionServicio
        implements InciarSesionUsoCaso {

    private final ConseguirUsuarioPorCorreoPuerto
            conseguirUsuarioPorCorreoPuerto;

    private final Validator validator;

    @Override
    public UsuarioModelo execute(
            final InciarSesionComando comando) {

        validateCommand(comando);

        final UsuarioCorreo correo =
                new UsuarioCorreo(comando.correo());

        final UsuarioModelo usuario =
                buscarUsuarioOFallarConCredencialesInvalidas(
                        correo
                );

        verificarContraseñaOFallar(
                comando.contraseña(),
                usuario
        );

        asegurarUsuarioActivoOFallar(usuario);

        return usuario;
    }

    private void validateCommand(
            final InciarSesionComando comando) {

        final Set<ConstraintViolation<InciarSesionComando>>
                violations = validator.validate(comando);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private UsuarioModelo
    buscarUsuarioOFallarConCredencialesInvalidas(
            final UsuarioCorreo correo) {

        return conseguirUsuarioPorCorreoPuerto
                .getByEmail(correo)
                .orElseThrow(
                        InvalidasCredencialesExcepcion::
                                becauseCredentialsAreInvalid
                );
    }

    private static void verificarContraseñaOFallar(
            final String contraseñaPlana,
            final UsuarioModelo usuario) {

        if (!usuario.getContraseña()
                .verifyPlain(contraseñaPlana)) {

            throw InvalidasCredencialesExcepcion
                    .becauseCredentialsAreInvalid();
        }
    }

    private static void asegurarUsuarioActivoOFallar(
            final UsuarioModelo usuario) {

        if (usuario.getEstatus()
                != UsuarioEstatus.ACTIVO) {

            throw InvalidasCredencialesExcepcion
                    .becauseUserIsNotActive();
        }
    }
}

